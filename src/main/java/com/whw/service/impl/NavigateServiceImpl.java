package com.whw.service.impl;

import com.whw.model.*;
import com.whw.service.LoadService;
import com.whw.service.NavigateService;
import com.whw.util.AStarUtil;
import com.whw.util.DijkstraUtil;
import com.whw.util.NavigationUtil;
import com.whw.util.YenKspUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 导航相关
 * 【注：编程时注意：每次迭代都不要修改上次迭代结果，将新的结果存放在新的变量中】
 */
@Service
@Transactional
public class NavigateServiceImpl implements NavigateService {

    @Resource
    LoadService loadService;

    // 集群内迭代次数
    public static final int Const = 20;

    // 趟迭代最大次数
    public static final int maxTry = 100;

    // 结果满足阈值
    public static final double threshold = 0.01;

    // 最少迭代次数
    public static final int baseLineIteration = 10;

    // 满足效用是理想效用的a倍
    public static final double a = 1.6;

    // 集群大小的迭代最高点
    public static final int averageT = 10;

    // 严丽平算法中概率计算的 kece
    public static final double y = 0.2;

    // 满足均衡中维持原有行动的概率（[0.5,1]）
    public static final double mu = 0.6;

    // 迭代次数 averageTClusterSize 时集群达到最高点
    public static final int averageTClusterSize = 40;
    // 求解集群大小时的修正系数
    public static final int correctionFactor = 50;


    /**
     * 得到 startNodeNum 到 endNodeNum 之间的 K 条最短路径（过必将点，无环路）
     *
     * @param startNodeId：起点编号
     * @param endNodeId：终点编号
     * @return
     */
    public List<NavPath> getKRoads(String startNodeId, String endNodeId) {
        // 根据数据库中的数据创建图
        MyGraph g = loadService.createGraph_fromDb();
        return getKRoads(g, startNodeId, endNodeId);
    }


    /**
     * 得到多车辆路径规划结果
     * 分集群的路径规划
     *
     * @param cars
     * @return
     */
    @Override
    public List<NavigationResult> getMultiAgentNavResult_cluster(List<Car> cars) {

        if (cars == null || cars.size() == 0) {
            return null;
        }

        List<NavigationResult> resList = new ArrayList<>();
        MyGraph g = loadService.createGraph_fromDb();

        // 保存最新的迭代可用结果
        NavigationResult result = new NavigationResult();
        double systemExpectedQ = 0;

        // 1,贪心策略选择初始路径
        for (Car car : cars) {
            // 设置候选路径集
            List<NavPath> candidatePaths = getKRoads(g, car.getStartNodeId(), car.getEndNodeId());
            car.setCadidatePaths(candidatePaths);
            if (candidatePaths != null && candidatePaths.size() != 0) {
                // 设置车辆试探性最佳路径
                car.setBeatPathIndex(0);
                // 设置车辆理想效用
                double carExpectedQ = (-1) * car.getCadidatePaths().get(0).weight / 500;
                car.setExpectedUtility(carExpectedQ);
                systemExpectedQ += carExpectedQ;
                // 设置每辆车的满足效用
                car.setSatisfyUtility(carExpectedQ * a);
            }
        }
        result.expectedQ = systemExpectedQ;
        result.cars = cars;
        result.Q = getQ(g, cars);
        result.satisfactionNum = updateSatisfactionNum(result.cars);
        result.clusterSize = cars.size();
        resList.add(new NavigationResult(result));

        System.out.println("t0:" + result);

        // 2,分趟迭代
        for (int i = 1; i <= maxTry; i++) {
            // 得到趟迭代中使得系统收益最大的路径选择结果&最大的系统收益
            NavigationResult tripRes = tripIteration(i, g, resList);
            System.out.println("t" + i + ":" + tripRes);
            if (i > baseLineIteration && Math.abs(tripRes.Q / result.Q - 1) < threshold) {
                // 终止算法
                resList.add(new NavigationResult(tripRes));
                return resList;
            } else {
                result = tripRes;
                resList.add(new NavigationResult(result));
            }
        }

        return resList;

    }

    /**
     * 不分集群的路径规划
     *
     * @param cars
     * @return
     */
    public List<NavigationResult> getMultiAgentNavResult_noCluster(List<Car> cars) {

        if (cars == null || cars.size() == 0) {
            return null;
        }

        List<NavigationResult> resList = new ArrayList<>();
        MyGraph g = loadService.createGraph_fromDb();

        // 保存最新的迭代可用结果
        NavigationResult result = new NavigationResult();
        double systemExpectedQ = 0;

        // 保存上次迭代的系统收益Q值
        double preQ = 0;

        // 1,随机选择初始策略
        Random random = new Random();
        for (Car car : cars) {
            // 设置候选路径集
            List<NavPath> candidatePaths = getKRoads(g, car.getStartNodeId(), car.getEndNodeId());
            car.setCadidatePaths(candidatePaths);
            if (candidatePaths != null && candidatePaths.size() != 0) {
                // 设置车辆试探性最佳路径（随机选择）
                car.setBeatPathIndex(random.nextInt(candidatePaths.size()));
                // 设置车辆理想效用
                double carExpectedQ = (-1) * car.getCadidatePaths().get(0).weight / 500;
                car.setExpectedUtility(carExpectedQ);
                systemExpectedQ += carExpectedQ;
                // 设置每辆车的满足效用
                car.setSatisfyUtility(carExpectedQ * a);
            }
        }
        result.expectedQ = systemExpectedQ;
        result.cars = cars;
        result.Q = getQ(g, cars);
        result.satisfactionNum = updateSatisfactionNum(result.cars);
        result.clusterSize = cars.size();
        resList.add(new NavigationResult(result));

        System.out.println("t0:" + result);

        // 所有车同时改变策略
        for (int i = 1; i <= maxTry; i++) {
            for (Car car : cars) {
                // 更新策略
                double[] prob = getSelectionProb_ylp(g, car, resList);
                System.out.println("更新完策略的车辆：" + car.toString());
                // 选择路径
                int selectedIndex = NavigationUtil.getSelectedOne_byRoulette(prob);
                // 更新car的最佳路径
                result.cars.get(result.cars.indexOf(car)).setBeatPathIndex(selectedIndex);
            }
            // 更新每个车的Q，并且重新计算系统收益
            double curQ = getQ(g, result.cars);
            result.Q = curQ;
            result.satisfactionNum = updateSatisfactionNum(result.cars);
            // 将此次迭代结果添加到resList中
            resList.add(new NavigationResult(result));
        }
        return resList;

    }

    /**
     * 得到此次选路策略中达到满足的车辆数
     *
     * @param cars
     * @return
     */
    private int updateSatisfactionNum(List<Car> cars) {
        if (cars == null || cars.size() == 0) {
            return 0;
        }
        int sum = 0;
        for (Car car : cars) {
            if (car.utility >= car.satisfyUtility) {
                sum++;
            }
        }
        return sum;
    }


    /**
     * 趟迭代
     * 返回本趟迭代的结果
     *
     * @param t:迭代趟数
     * @param g:图
     * @param preAll：之前趟迭代的所有结果
     * @return
     */
    public NavigationResult tripIteration(int t, MyGraph g, List<NavigationResult> preAll) {
        if (preAll == null || preAll.size() == 0) {
            return null;
        }
        // 最近一趟的迭代结果
        NavigationResult pre = preAll.get(preAll.size() - 1);
        /*
        * 保存本趟迭代的最好结果（这样保证了上趟迭代结果不会被改变）
        * 趟和趟之间的结果互不影响
         */
        NavigationResult res = new NavigationResult();
        res.cars = new ArrayList<>(pre.cars);
        res.Q = pre.Q;
        res.expectedQ = pre.expectedQ;
        // 1,将所有车辆分成若干个集群
        List<MyCluster> clusters = dividIntoGroups(t, res);
        System.out.println("分组：" + clusters);
        // 2,对于每个集群进行如下操作
        for (MyCluster cluster : clusters) {
            // 集群内迭代
            res = clusterIteration(g, preAll, cluster.cars);
        }
        res.clusterSize = clusters.get(0).clusterSize;
        res.satisfactionNum = updateSatisfactionNum(res.cars);
        return res;
    }

    /**
     * 将所有的车辆分成几个集群
     *
     * @param t：第t趟迭代
     * @param res
     * @return
     */
    public List<MyCluster> dividIntoGroups(int t, NavigationResult res) {
        List<MyCluster> result = new ArrayList<>();
        // 将车辆按照（当前效用-期望效用）值的大小进行排序
        Collections.sort(res.cars);
        // 得到集群大小
        int size = getClusterSize(t, res.Q, res.expectedQ);
        System.out.println("t" + t + "分组大小：" + size);
        // 共分 num 个组
        int num = res.cars.size() / size + (res.cars.size() % size > 0 ? 1 : 0);
        for (int i = 0; i < num; i++) {
            List<Car> cluster = new ArrayList<>();
            int left = size * i;
            int right = size * i + (size - 1);
            int j = left;
            while (j <= right && j < res.cars.size()) {
                cluster.add(res.cars.get(j));
                j++;
            }
            result.add(new MyCluster(cluster, size));
        }
        return result;
    }

    public static class MyCluster {
        // 组内车辆集合
        public List<Car> cars;
        // 分组大小
        public int clusterSize;

        public MyCluster(List<Car> cars, int clusterSize) {
            this.cars = cars;
            this.clusterSize = clusterSize;
        }

        @Override
        public String toString() {
            return "MyCluster{" +
                    "cars=" + cars +
                    ", clusterSize=" + clusterSize +
                    '}';
        }
    }

    /**
     * TODO: 得到集群大小
     *
     * @param Q：上次迭代系统收益
     * @param expectedQ：期望系统收益
     * @return
     */
    private int getClusterSize(int t, double Q, double expectedQ) {
        if (t == averageT) {
            return averageTClusterSize;
        } else if (t < averageT) {
            // 迭代初期：四舍五入
            int size = (int) Math.ceil((expectedQ / (Q * (averageT - t))) * correctionFactor);
            return size;
        } else {
            // 迭代后期
            int size = (int) Math.ceil((expectedQ / (Q * (t - averageT))) * correctionFactor);
            return size;
        }

//        Random d = new Random();
//
//        return d.nextInt(20)+1;
    }


    /**
     * 集群内迭代：返回集群内迭代的最好结果
     * 【在整个迭代过程中pre未被改变】
     *
     * @param g:
     * @param preAll：之前趟迭代的所有正式结果
     * @param cluster：一个集群
     * @return
     */
    public NavigationResult clusterIteration(MyGraph g, List<NavigationResult> preAll, List<Car> cluster) {
        if (preAll == null || cluster == null || cluster.size() == 0) {
            return null;
        }
        // 保存返回结果(这样保证了不修改传入的参数)
        NavigationResult res = new NavigationResult();
        NavigationResult pre = preAll.get(preAll.size() - 1);
        res.cars = new ArrayList<>(pre.cars);
        res.Q = pre.Q;
        res.expectedQ = pre.expectedQ;
        // 临时保存每个车的策略(小范围的历史策略)
        List<Car> temp = new ArrayList<>(pre.cars);
        // 保存集群内迭代的历史策略
        List<NavigationResult> historyStrategy = new ArrayList<>();
        // 将上次迭代的最新结果传入
        historyStrategy.add(new NavigationResult((res)));
        for (int i = 0; i < Const; i++) {
            for (Car car : cluster) {
                // 更新策略
//                double[] prob = getSelectionProb_traditional(g, car, temp);

                double[] prob = getSelectionProb_satisfaction(g, car, historyStrategy);
                // 选择路径
                int selectedIndex = NavigationUtil.getSelectedOne_byRoulette(prob);
                // 更新temp
                temp.get(temp.indexOf(car)).setBeatPathIndex(selectedIndex);
            }
            // 更新每个车的Q，并且重新计算系统收益
            double curQ = getQ(g, temp);
            if (curQ > res.Q) {
                // 将各个车的策略更新到res中
                res.cars = new ArrayList<>(temp);
                res.Q = curQ;
            }
            historyStrategy.add(new NavigationResult(res));
        }
        return res;
    }

    /**
     * 根据上一次选路策略得到选路概率:对于车辆car的所有候选路径，
     * 其被选中的概率=（如果上次迭代选择该路径可获得的效用）/（所有候选路径获得的效用和）
     * 效用/效用和
     *
     * @param car
     * @param pre:上轮迭代所有车辆的选路结果
     * @return
     */
    public double[] getSelectionProb_traditional(MyGraph g, Car car, List<Car> pre) {
        if (car == null) {
            return null;
        }
        if (car.cadidatePaths == null || car.cadidatePaths.size() == 0) {
            return null;
        }
        List<Car> temp = new ArrayList<>(pre);
        // 当前关注的车辆在List中的下标
        int index = temp.indexOf(car);
        int size = car.cadidatePaths.size();
        // 保存该车辆的所有候选路径的效用
        double[] utility = new double[size];
        double[] prob = new double[size];
        // 对于每个候选路径得到的效用值的和
        double sum = 0;
        for (int i = 0; i < size; i++) {
            // 遍历车辆的候选路径
            temp.get(index).setBeatPathIndex(i);
            // 在此选路情况下计算各车选路效用
            updateCarUtility(g, temp);
            utility[i] = temp.get(index).getUtility();
            sum += utility[i];
        }
        for (int i = 0; i < size; i++) {
            prob[i] = utility[i] / sum;
        }
        return prob;
    }

    /**
     * 以满足均衡为目标调整策略
     *
     * @param car:关注的car
     * @param preAll:之前迭代的历史策略选择
     * @return
     */
    public double[] getSelectionProb_satisfaction(MyGraph g, Car car, List<NavigationResult> preAll) {
        if (car == null) {
            return null;
        }
        if (car.cadidatePaths == null || car.cadidatePaths.size() == 0) {
            return null;
        }
        if (preAll == null || preAll.size() == 0) {
            return null;
        }
        // 上趟迭代结果
        NavigationResult pre = preAll.get(preAll.size() - 1);
        // 上次迭代该车辆选路策略
        Car preCar = pre.cars.get(pre.cars.indexOf(car));
        // 上次迭代中该车辆最佳路径
        int preBestIndex = preCar.beatPathIndex;
        // 上次迭代中该车辆获得效用
        double preUtility = preCar.utility;
        // 上次迭代该车辆是否达到满足均衡
        boolean satisfactionFlag = preCar.utility > preCar.satisfyUtility;
        // 候选路径集大小
        int size = car.cadidatePaths.size();
        // 每个可选路径的效用
        double[] possibleUtility = new double[size];
        // 对于每个候选路径得到的效用值的和
        double sum = 0;
        double max = 0;
        for (int i = 0; i < size; i++) {
            // 遍历车辆的候选路径
            preCar.setBeatPathIndex(i);
            // 在此选路情况下计算各车选路效用
            updateCarUtility(g, pre.cars);
            possibleUtility[i] = preCar.getUtility();
            sum += possibleUtility[i];
            if (possibleUtility[i] > max) {
                max = possibleUtility[i];
            }
        }
        // 计算上次迭代中每个候选路径的后悔值
        double[] regretUtility = new double[size];
        // 计算上次迭代中总后悔值
        double sumRegretUtility = 0;
        for (int i = 0; i < size; i++) {
            regretUtility[i] = max - possibleUtility[i];
            sumRegretUtility += regretUtility[i];
        }
        if (satisfactionFlag) {
            // 满足
            double[] prob = new double[size];
            // 归一化因子
            double K = (1 - mu) / sum;
            for (int i = 0; i < size; i++) {
                if (i == preBestIndex) {
                    prob[i] = mu;
                } else {
                    prob[i] = possibleUtility[i] * K;
                }
            }
            return prob;

        } else {
            // 不满足
            // 不悔概率
            double noRegretProb = 1 - Math.abs(regretUtility[car.getBeatPathIndex()] / sumRegretUtility);
            // 归一化因子
            double K = (1 - noRegretProb) / sum;
            double[] prob = new double[size];
            for (int i = 0; i < size; i++) {
                if (i == preBestIndex) {
                    prob[i] = noRegretProb;
                } else {
                    prob[i] = possibleUtility[i] * K;
                }
            }
            return prob;
        }
    }

    /**
     * 严丽平方法
     *
     * @param car：当前关注的车辆
     * @param pre:当前车辆的历史路径选择
     * @return
     */
    public double[] getSelectionProb_ylp(MyGraph g, Car car, List<NavigationResult> pre) {
        if (car == null) {
            return null;
        }
        if (car.cadidatePaths == null || car.cadidatePaths.size() == 0) {
            return null;
        }
        if (pre == null || pre.size() == 0) {
            return null;
        }
        int size = car.cadidatePaths.size();
        // 1,得到估测效用最大的路径
        // 保存车辆可选路径的估测效用
        double[] sumUility = new double[size];
        // 保存可选路径集中对应路径被选中的次数
        int[] selectedNums = new int[size];
        // 历史路径选择中获得的效用总值
        double historySumUtility = 0;
        // 遍历car的历史路径选择
        for (NavigationResult navR : pre) {
            // 得到对应车辆的历史路径选择
            int index = navR.cars.indexOf(car);
            int bestIndex = navR.cars.get(index).beatPathIndex;
            selectedNums[bestIndex]++;
            sumUility[bestIndex] += navR.cars.get(index).utility;
            historySumUtility += navR.cars.get(index).utility;
        }
        // 路径的估测效用
        double[] estimatedUtility = new double[size];
        // 估测效用最大的路径index
        int maxIndex = 0;
        double maxEstimatedUtil = estimatedUtility[0];
        // 得到每个路径的估测效用
        for (int i = 0; i < size; i++) {
            if (selectedNums[i] == 0) {
                estimatedUtility[i] = 0;
            } else {
                estimatedUtility[i] = sumUility[i] / selectedNums[i];
            }
            if (estimatedUtility[i] > maxEstimatedUtil) {
                maxIndex = i;
                maxEstimatedUtil = estimatedUtility[i];
            }
        }
        // 2,最佳响应路径的选择概率
        // 当前关心的车辆在List中的下标
        int carIndex = pre.get(pre.size() - 1).cars.indexOf(car);
        // 上次路径选择中的u值
        double preU = pre.get(pre.size() - 1).cars.get(carIndex).u;
        // 上次路径选择获得的效用
        double preUtility = pre.get(pre.size() - 1).cars.get(carIndex).getUtility();
        // 历史路径选择的平均效用
        double averageUtility = historySumUtility / pre.size();
        // 上次路径选择效用的后悔值
        double regret = preUtility - averageUtility;
        // 此次用于计算概率的u值
        double curU = preU + (regret - preU) / pre.size();
        /**
         * 更新u值（u的初始值为1）
         */
        car.u = curU;
        double sumBestProb = 0;
        // 最佳路径选择的概率
        for (int i = 0; i < size; i++) {
            sumBestProb += Math.exp(estimatedUtility[i] / curU);
        }
        // 最佳响应路径的选择概率
        double bestProb = y * (Math.exp(estimatedUtility[maxIndex] / curU) / sumBestProb) + (1 - y);
        // 3，更新所有车辆的路径选择概率
        // 路径选择概率
        double[] prob = new double[size];
        for (int i = 0; i < size; i++) {
            if (i == maxIndex) {
                prob[i] = bestProb;
            } else {
                prob[i] = (1 - bestProb) / (size - 1);
            }
        }
        return prob;
    }

    /**
     * 将agent根据其效用值分成若干个集群
     *
     * @param cars：所有的车辆
     * @return
     */
    /*public List<MyCluster> dividIntoGroups(List<Car> cars) {
        if (cars == null || cars.size() == 0) {
            return null;
        }
        List<MyCluster> result = new ArrayList<>();
        double sum = 0;
        for (int i = 0; i < cars.size(); i++) {
            sum += cars.get(i);
        }
//        while (cars.size()>1) {
        while (!cars.isEmpty()) {
            // 此次分集群临界点
//            int denominator = (cars.size())*(cars.size()-1)/2;
            int denominator = cars.size();
            double criticalP = sum / denominator;
            // 找出临界点以上的agent
            MyCluster cluster = new MyCluster();
            cluster.criticalPoint = criticalP;
            List<Double> members = new ArrayList<>();
            cluster.members = members;

            Iterator<Double> it = cars.iterator();
            while (it.hasNext()) {
                Double agent = it.next();
                if (agent >= criticalP) {
                    members.add(agent);
                    sum -= agent;
                    it.remove();
                }
            }
            result.add(cluster);
        }
//        if (cars != null && cars.size() != 0) {
//            // 将agents中剩余元素组成一个集群
//            MyCluster cluster = new MyCluster();
//            cluster.members = cars;
//            result.add(cluster);
//        }
        return result;
    }*/


    /**
     * 1,更新各个car的效用 & 2，返回系统收益
     * 【将各车选路结果进行一个组合】
     *
     * @param cars
     * @return
     */
    @Override
    public double getQ(MyGraph g, List<Car> cars) {
        double q = 0;
        // 1，计算各车的效用
        updateCarUtility(g, cars);
        // 2，将各车的效用累加
        for (Car car : cars) {
            q += car.getUtility();
        }
        return q;
    }

    /**
     * 根据各车的路径选择，计算各车的效用：取时间的负值作为车辆效用
     * 效用是和流量相关的
     *
     * @param cars
     */
    private void updateCarUtility(MyGraph g, List<Car> cars) {
        // 计算路段流量（每次都将上次的计算结果清空，重新开始计算路段流量）
        updateEdgeFlow(g, cars);
        // 更新每个路段的行驶时间、及车辆所选路径的总行驶时间
        updateCarSumTime(g, cars);

        // TODO:计算各车等待机械时间

        // 计算各车的效用
        for (Car car : cars) {
            // 各车所选路径总行驶时间
            car.setUtility((-1) * car.getSumTime());
        }
    }


    /**
     * 更新每个车辆所选路径的总行驶时间
     *
     * @param g
     * @param cars
     */
    private void updateCarSumTime(MyGraph g, List<Car> cars) {
        // 更新当前路段流量下的路段行驶时间
        for (int i = 0; i < g.edges.length; i++) {
            Edge edge = g.edges[i];
            edge.setCurRunTime(NavigationUtil.getEdgeRunTime
                    (edge.getInitialTime(), edge.getInitalCarNumbers(), edge.getCurCarNumbers()));
        }
        // 计算各车选路的总行驶时间
        for (Car car : cars) {
            // 各车所选路径总行驶时间
            double sumTime = 0;
            List<Node> bestPathNodes = car.getCadidatePaths().get(car.getBeatPathIndex()).nodeList;
            for (int i = 0; i <= bestPathNodes.size() - 2; i++) {
                // 更新g中相关路段流量
                int edgeIndex = NavigationUtil.getEdgeIndex(g,
                        NavigationUtil.getIndex(g, bestPathNodes.get(i).getNodeId()),
                        NavigationUtil.getIndex(g, bestPathNodes.get(i + 1).getNodeId()));
                sumTime += g.edges[edgeIndex].getCurRunTime();
            }
            car.setSumTime(sumTime);
        }
    }

    /**
     * 更新路段流量
     *
     * @param g
     * @param cars
     */
    public void updateEdgeFlow(MyGraph g, List<Car> cars) {
        // 将各路段流量置为0
        for (int i = 0; i < g.edges.length; i++) {
            g.edges[i].setCurCarNumbers(0);
        }
        // 根据车辆选路计算路段流量
        for (Car car : cars) {
            // 每个车所选路径
            List<Node> bestPathNodes = car.getCadidatePaths().get(car.getBeatPathIndex()).nodeList;
            for (int i = 0; i <= bestPathNodes.size() - 2; i++) {
                // 更新g中相关路段流量
                int edgeIndex = NavigationUtil.getEdgeIndex(g,
                        NavigationUtil.getIndex(g, bestPathNodes.get(i).getNodeId()),
                        NavigationUtil.getIndex(g, bestPathNodes.get(i + 1).getNodeId()));
                int edgeIndex2 = NavigationUtil.getEdgeIndex(g,
                        NavigationUtil.getIndex(g, bestPathNodes.get(i + 1).getNodeId()),
                        NavigationUtil.getIndex(g, bestPathNodes.get(i).getNodeId()));
                g.edges[edgeIndex].addCurCarNumbers();
                g.edges[edgeIndex2].addCurCarNumbers();

            }
        }
    }


    @Override
    public List<NavPath> getKRoads(MyGraph g, String startNodeId, String endNodeId) {
        int startNodeIndex = NavigationUtil.getIndex(g, startNodeId);
        int endNodeIndex = NavigationUtil.getIndex(g, endNodeId);
        List<MyPath> temp = YenKspUtil.getKsp(g, startNodeIndex, endNodeIndex);
        List<NavPath> result = new ArrayList<>();
        if (temp != null && temp.size() != 0) {
            for (int i = 0; i < temp.size(); i++) {
                result.add(NavigationUtil.myPathToNavPath(g, temp.get(i)));
            }
        }
        return result;
    }

    /**
     * 得到 startNodeNum 到 endNodeNum 之间的 K 条最短路径（过必将点，无环路）
     *
     * @param startNodeId：起点编号
     * @param endNodeId：终点编号
     * @param mustPassPoints：毕经点编号集合
     * @return
     */
    public List<NavPath> getKRoads(String startNodeId, String endNodeId, List<Node> mustPassPoints) {
        return null;
    }


    /**
     * 得到两点间最短路径
     *
     * @param startNodeId：起始节点编号
     * @param endNodeId：终止节点编号
     * @return
     */
    @Override
    public NavPath getShortestRoad_AStar(String startNodeId, String endNodeId) {
        // 根据数据库中的数据创建图
        MyGraph g = loadService.createGraph_fromDb();
        int startNodeIndex = NavigationUtil.getIndex(g, startNodeId);
        int endNodeIndex = NavigationUtil.getIndex(g, endNodeId);
        NavPath result = AStarUtil.getShortestRoad_AStar(g, startNodeIndex, endNodeIndex);
        return result;
    }

    @Override
    public NavPath getShortestRoad_Dijkstra(String startNodeId, String endNodeId) {
        MyGraph g = loadService.createGraph_fromDb();
        int startNodeIndex = NavigationUtil.getIndex(g, startNodeId);
        int endNodeIndex = NavigationUtil.getIndex(g, endNodeId);
        MyPath result = DijkstraUtil.getSingleShortestPath_dijkstra(g,
                startNodeIndex, endNodeIndex, null, null);
        return NavigationUtil.myPathToNavPath(g, result);
    }


}

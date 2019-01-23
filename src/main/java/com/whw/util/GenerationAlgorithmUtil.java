package com.whw.util;

import java.util.*;

/**
 * <pre>
 *     author : 杨丽金
 *     time   : 2018/12/15
 *     desc   : 遗传算法实现：解决任务分配问题
 *     假设有 N 个任务，需要负载均衡器分配给 M 个服务器节点去处理。
 *     每个任务的任务长度、每台服务器节点(下面简称“节点”)的处理速度已知，
 *     请给出一种任务分配方式，使得所有任务的总处理时间最短。
 *     version: 1.0
 * </pre>
 */
public class GenerationAlgorithmUtil {

    // 将任务从 0 开始编号，存储每个任务的长度
    int[] tasks;
    int TASK_NUM;

    // 将处理器从 0 开始编号，存储每个处理器处理任务的速度 可处理长度/单位时间
    int[] nodes;
    // 处理器个数
    int NODE_NUM;

    // timeMatrix[i][j]：第 i 个任务放在处理器 j 上处理需要的时间
    double[][] timeMatrix;


    // adaptabilitu[i] 染色体 i 的适应度概率
    double[] adaptability;

    // selectionProbability[i]:第 i 个染色体的适应度概率
    double[] selectionProbability;

    int iteratorNum;
    int chromosomeNum;

    // 染色体复制比例
    double cp;

    public void ga() {
        inputParam();
        inputData();
        initMatrix();
        gaSearch(iteratorNum, chromosomeNum);
    }

    public void initMatrix() {
        // 任务-处理时间
        timeMatrix = new double[TASK_NUM][NODE_NUM];

        for(int i=0;i<TASK_NUM;i++) {
            for(int j=0;j<NODE_NUM;j++) {
                // 第i个任务放在处理器j上处理，所需时间
                timeMatrix[i][j] = (tasks[i] +1.0)/ nodes[j];
            }
        }

        // 染色体适应度
        adaptability = new double[chromosomeNum];
        // 染色体适应度概率
        selectionProbability = new double[chromosomeNum];
    }

    /**
     * 输入任务序列、节点序列
     */
    public void inputData() {
        // 任务矩阵
        tasks = new int[TASK_NUM];
        // 节点矩阵
        nodes = new int[NODE_NUM];

        Random r = new Random();
        // 随机生成任务长度[10,100]
        for(int i=0;i<TASK_NUM;i++) {
            tasks[i] = r.nextInt(91)+10;
        }
        // 随机生成处理器处理任务速度[10,100]
        for(int i=0;i<NODE_NUM;i++) {
            nodes[i]=r.nextInt(91)+10;
        }

    }

    /**
     * 初始化相关参数
     */
    public void inputParam() {
        // 任务个数
        TASK_NUM =100;
        // 处理器个数
        NODE_NUM = 10;
        // 迭代次数
        iteratorNum = 100;
        // 群体大小
        chromosomeNum = 10;

        // 染色体复制的比例(每代中保留适应度较高的染色体直接成为下一代)
        cp = 0.2;
    }

    /**
     * 遗传算法迭代
     *
     * @param iteratorNum
     * @param chromosomeNum
     */
    public void gaSearch(int iteratorNum, int chromosomeNum) {
        // 初始化第一代群体
        List<int[]> chromosomeMatrix = createGeneration(null, chromosomeNum);
        // 迭代繁衍
        for (int i = 2; i <= iteratorNum; i++) {
            // 计算上一代群体染色体的适应度
            adaptability = calAdaptability(chromosomeMatrix);
//            System.out.println("迭代次数："+i+",计算上一代群体染色体的适应度:"+Arrays.toString(adaptability));
            // 计算上一代染色体的适应度概率
            selectionProbability = calSelectionProbability(adaptability);
//            System.out.println("迭代次数："+i+",计算上一代染色体的适应度概率:"+Arrays.toString(selectionProbability));

            // 生成新一代染色体
            chromosomeMatrix = createGeneration(chromosomeMatrix, chromosomeNum);
            System.out.println("迭代次数："+i+",的每个染色体总处理时间：");
            for(int j=0;j<chromosomeMatrix.size();j++) {
                System.out.println("处理时间："+getTotalProcessTime(chromosomeMatrix.get(j)));
            }
        }
    }

    /**
     * 得到一个染色体总的处理时间
     * @param chromosome
     * @return
     */
    private double getTotalProcessTime(int[] chromosome) {
        double sum=0;
        for(int j=0;j<chromosome.length;j++) {
            // 将第j号任务分配到第nodeId号处理器行
            int nodeId = chromosome[j];
            sum += timeMatrix[j][nodeId];
        }
        return sum;
    }

    /**
     * 计算群体中染色体的适应度概率
     * @param adaptability
     * @return
     */
    public double[] calSelectionProbability(double[] adaptability) {
        if (adaptability == null || adaptability.length == 0) {
            return null;
        }
        double[] selectionProb = new double[adaptability.length];
        // 总适应度
        double sum = 0;
        for (int i = 0; i < adaptability.length; i++) {
            sum += adaptability[i];
        }
        for (int i = 0; i < adaptability.length; i++) {
            selectionProb[i] = adaptability[i] / sum;
        }
        return selectionProb;
    }

    /**
     * 计算群体中染色体的适应度
     * @param chromosomeMatrix
     * @return
     */
    public double[] calAdaptability(List<int[]> chromosomeMatrix) {
        double[] adaptAbility = new double[chromosomeMatrix.size()];
        for(int i=0;i<chromosomeMatrix.size();i++) {
            // 对每一个染色体，计算总的处理时间
            int[] chromosome = chromosomeMatrix.get(i);
            double sum = getTotalProcessTime(chromosome);
            adaptAbility[i] = 1 / sum;
        }
        return adaptAbility;
    }

    /**
     * 生成每次迭代后的群体
     *
     * @param chromosomeMatrix:上一代群体
     * @param chromosomeNum：群体中染色体数目
     * @return
     */
    public List<int[]> createGeneration(List<int[]> chromosomeMatrix, int chromosomeNum) {
        Random r = new Random();

        if (chromosomeMatrix == null || chromosomeMatrix.size() == 0) {
            chromosomeMatrix = new ArrayList<>();
            // 生成第一代染色体：随机生成染色体
            for (int i = 0; i < chromosomeNum; i++) {
                // 生成每个个体
                int[] chromosome = new int[TASK_NUM];
                for (int j = 0; j < TASK_NUM; j++) {
                    // 第j个任务交给第r.nextInt(NODE_NUM)个节点处理
                    chromosome[j] = r.nextInt(NODE_NUM);//[0,NODE_NUM)
                }
                chromosomeMatrix.add(chromosome);
            }
            return chromosomeMatrix;
        } else {
            // 根据上一代群体生成本代群体
            // 一部分复制，另一份交叉-变异
            int copyNum = (int) (chromosomeNum * cp);
            List<int[]> crossChromosome = cross(chromosomeMatrix, chromosomeNum - copyNum);
            System.out.println("交叉产生的染色体个数："+crossChromosome.size());
            // 从上一群体中复制的染色体
            List<int[]> copyChromosome = copy(chromosomeMatrix,copyNum );
            System.out.println("复制产生的染色体个数："+copyChromosome.size());

            // 生成新一代染色体
            List<int[]> newChromosomeMatrix = new ArrayList<>();
            newChromosomeMatrix.addAll(crossChromosome);
            newChromosomeMatrix.addAll(copyChromosome);
            System.out.println("新一代染色体个数："+newChromosomeMatrix.size());

            return newChromosomeMatrix;
        }

    }

    /**
     * 从上一代群体chromosomeMatrix中复制产生 num 个染色体
     * @param chromosomeMatrix
     * @param num
     * @return
     */
    public List<int[]> copy(List<int[]> chromosomeMatrix, int num) {
        // 从上一代群体中找出适应度最高的num个染色体，把这些染色体复制到新的群体中
        return getMaxN(chromosomeMatrix,selectionProbability,num);
    }

    /**
     * 得到适应度最高的前num个染色体
     * @param chromosomeMatrix
     * @param selectionProbability
     * @param num
     * @return
     */
    public List<int[]> getMaxN(List<int[]> chromosomeMatrix, double[] selectionProbability,int num) {
        PriorityQueue<Chromosome> queue = new PriorityQueue<>(num);
        for(int i=0;i<selectionProbability.length;i++) {
            if (queue.size() < num) {
                queue.add(new Chromosome(i, selectionProbability[i]));

            }else{
                Chromosome mincs = queue.peek();
                if (mincs.selectionProb < selectionProbability[i]) {
                    queue.poll();
                    queue.add(new Chromosome(i, selectionProbability[i]));
                }
            }
        }
        List<int[]> result = new ArrayList<>();
        // 变量PriorityQueue
        if (queue != null && queue.size() != 0) {
            Iterator<Chromosome> iterator=queue.iterator();
            while (iterator.hasNext()) {
                Chromosome chromosome= iterator.next();
                result.add(chromosomeMatrix.get(chromosome.id));
            }
        }
        return result;
    }

    public static class Chromosome implements Comparable<Chromosome>{
        int id;
        double selectionProb;

        public Chromosome() {
        }

        public Chromosome(int id, double selectionProb) {
            this.id = id;
            this.selectionProb = selectionProb;
        }

        @Override
        public int compareTo(Chromosome o) {
            if (o == null) return -1;
            double r = this.selectionProb - o.selectionProb;
            if (r > 0) {
                return 1;
            } else if (r < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * 从上一代群体chromosomeMatrix中交叉-变异产生 num 个染色体
     *
     * @param chromosomeMatrix
     * @param num
     * @return
     */
    public List<int[]> cross(List<int[]> chromosomeMatrix, int num) {
        if (chromosomeMatrix == null || chromosomeMatrix.size() == 0) {
            return null;
        }
        Random r = new Random();
        List<int[]> crossChromosome = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            int[] chromosome = new int[TASK_NUM];
            // 采用轮盘赌算法获得爸爸和妈妈染色体
            int fatherIndex = NavigationUtil.getSelectedOne_byRoulette(selectionProbability);
            int motherIndex = NavigationUtil.getSelectedOne_byRoulette(selectionProbability);
            // 交叉位置
            int crossIndex = r.nextInt(TASK_NUM);
            System.arraycopy(chromosomeMatrix.get(fatherIndex),0,
                    chromosome,0,crossIndex+1);
            System.arraycopy(chromosomeMatrix.get(motherIndex),
                    crossIndex+1,chromosome,crossIndex+1,TASK_NUM-crossIndex-1);
            // 交叉后变异
            // 变异位置
            int mutationIndex = r.nextInt(TASK_NUM);
            int newNodesNum = r.nextInt(NODE_NUM);
            chromosome[mutationIndex] = newNodesNum;
            crossChromosome.add(chromosome);
        }
        return crossChromosome;
    }




}

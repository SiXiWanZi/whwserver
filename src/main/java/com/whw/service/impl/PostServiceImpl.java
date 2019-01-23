package com.whw.service.impl;

import com.whw.dao.GoodsDao;
import com.whw.dao.GoodsTypeDao;
import com.whw.dao.UserDao;
import com.whw.model.Goods;
import com.whw.model.User;
import com.whw.service.PostService;
import com.whw.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 张芳容 on 2017/07/05.
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {
    @Resource
    private GoodsDao goodsDao;
    @Resource
    private GoodsTypeDao goodsTypeDao;
    @Resource
    private UserDao userDao;

    @Override
    public void postGood(Goods goods) {
        goodsDao.save(goods);
    }

    @Override
    public void updateGoods(Goods goods) {
        goodsDao.update(goods);
    }

    @Override
    public boolean deleteByBH(String bh) {
        Goods goods = goodsDao.findGoodsByBH(bh);
        if (goods == null)
            return false;
        goodsDao.delete(goods);
        return true;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public boolean postGoods(CommonsMultipartFile[] multipartFile, String dir, String userID, String title, String content, String oldlevel, String price, String goodsType, String area, String changeType, String want_change) {
        try {
            Goods goods = new Goods();
            goods.setBt(title);
            goods.setFbsj(new Date());
            if(!"".equals(price.trim()))
                goods.setGj(Float.parseFloat(price));
            goods.setJylx(changeType);
            goods.setJyzt("0");
            goods.setMs(content);
            goods.setSfyx("1");
            goods.setSzss(area);
            goods.setXhwp(want_change);
            goods.setXjcd(Integer.valueOf(oldlevel));
            goods.setGoodsType(goodsTypeDao.findGoodTypeByLBBM(goodsType));
            goods.setUser(userDao.findById(userID));
            //图片
            String ZP = "";
            String fileName = "";
            String fileNameNew = "";
            for (int i = 0; i < multipartFile.length; i++) {
                fileName = multipartFile[i].getOriginalFilename();
                fileNameNew = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf('.'));
                boolean b = FileUtils.savePic(  multipartFile[i], dir + "/"+fileNameNew);
                if (b) {
                    ZP += fileNameNew+";";
                }
            }
            goods.setZp(ZP);
            goodsDao.save(goods);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

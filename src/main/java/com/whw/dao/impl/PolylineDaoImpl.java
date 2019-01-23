package com.whw.dao.impl;

import com.whw.dao.PolylineDao;
import com.whw.model.Polyline;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
@Repository
public class PolylineDaoImpl extends BaseDaoImpl<Polyline> implements PolylineDao {

    @Override
    public List<Polyline> findAllLines() {
        String hql = "from Polyline";
        return find(hql);
    }
}

package com.josie.quake.service.impl;

import com.josie.quake.Dao.QuakeInfoDao;
import com.josie.quake.model.QuakeInfo;
import com.josie.quake.service.QuakeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Josie on 16/5/12.
 */
@Service
public class QuakeInfoServiceImpl implements QuakeInfoService {

    @Autowired
    private QuakeInfoDao quakeInfoDao;

    @Override
    public List<QuakeInfo> getAllByCount(int start, int count) {
        return quakeInfoDao.getAll(start, count);
    }

    @Override
    public List<QuakeInfo> getAllByStatusByCount(QuakeInfo.Status enable, int start, int count) {
        return quakeInfoDao.getAllByStatus(enable.toInt(), start, count);
    }
}

package com.josie.quake.service.impl;

import com.josie.quake.dao.SystemConfigDao;
import com.josie.quake.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Glacier on 16/5/24.
 */
@Service
public class SystemConfigServiceImpl implements SystemConfigService {

    @Autowired
    private SystemConfigDao systemDao;

    @Override
    public boolean isSystemStart() {
        return systemDao.getStatus(0);
    }

    @Override
    public boolean isExamineStart() {
        return systemDao.getStatus(1);
    }

    @Override
    public void shutdownExamine() {
        systemDao.setStatus(1, false);
    }

    @Override
    public void shutdownSystem() {
        systemDao.setStatus(0, false);
    }

    @Override
    public void startExamine() {
        systemDao.setStatus(1, true);
    }

    @Override
    public void startSystem() {
        systemDao.setStatus(0, true);
    }
}

package com.josie.quake.service.impl;

import com.josie.quake.dao.FilterWhiteListDao;
import com.josie.quake.model.FilterWhiteList;
import com.josie.quake.service.FilterWhiteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Josie on 16/5/21.
 */
@Service
public class FilterWhiteListServiceImpl implements FilterWhiteListService {

    @Autowired
    private FilterWhiteListDao filterWhiteListDao;

    @Override
    public void addWhiteList(
            String url,
            int operator) {
        filterWhiteListDao.add(url, operator);
    }

    @Override
    public void deleteWhiteList(
            int id,
            int operator) {
        filterWhiteListDao.delete(id, operator, FilterWhiteList.Status.Disable.toInt());
    }
}

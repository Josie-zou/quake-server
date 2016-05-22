package com.josie.quake.service.impl;

import com.josie.quake.dao.FilterRuleDao;
import com.josie.quake.model.FilterRule;
import com.josie.quake.service.FilterRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Josie on 16/5/21.
 */
@Service
public class FilterRuleServiceImpl implements FilterRuleService {

    @Autowired
    private FilterRuleDao filterRuleDao;


    @Override
    public void addRule(String rule, int id) {
        filterRuleDao.addRule(rule, id);
        return;
    }

    @Override
    public void deletRule(int id, int operator) {
        filterRuleDao.delete(id, operator, FilterRule.Status.Disable.toInt());
    }

    @Override
    public List<Object> getAll() {
        return filterRuleDao.getAll();
    }

    @Override
    public void update(Integer id, String rule, Integer operator) {
        filterRuleDao.update(id, rule, operator);
        return;
    }
}

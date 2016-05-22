package com.josie.quake.controller.vo;

import com.google.common.collect.Lists;
import com.josie.quake.model.FilterRule;
import com.josie.quake.service.impl.UserServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * Created by Josie on 16/5/22.
 */
public class FilterRuleVo {
    private String username;

    private UserServiceImpl userService = new UserServiceImpl();

    private FilterRule filterRule;

    public FilterRuleVo(FilterRule filterRule) {
        this.filterRule = filterRule;
        this.username = userService.getById(filterRule.getOperator()).getUsername();
    }

//    public int getId() {
//        return filterRule.getId();
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getRule() {
//        return filterRule.getRule();
//    }
//
//    public int getOperator() {
//        return filterRule.getOperator();
//    }
//
//    public Date getCreateTime() {
//        return filterRule.getCreateTime();
//    }
//
//    @Override
//    public String toString() {
//        return "FilterRuleVo [getId()=" + getId() + ",getRule()" + getRule() + ",getUsername()" + getUsername()
//                + "getOperator" + getOperator() + ", getCreateTime" + getCreateTime() + "]";
//    }


    public static List<FilterRuleVo> convert(List<FilterRule> filterRules) {
        if (filterRules == null || filterRules.size() == 0) {
            return Lists.newArrayList();
        }
        List<FilterRuleVo> result = Lists.newArrayListWithExpectedSize(filterRules.size());
        for (FilterRule filterRule : filterRules) {
            result.add(new FilterRuleVo(filterRule));
        }
        return result;
    }
}

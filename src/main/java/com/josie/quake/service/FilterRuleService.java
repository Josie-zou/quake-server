package com.josie.quake.service;

import com.josie.quake.model.FilterRule;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Josie on 16/5/21.
 */

@Component
public interface FilterRuleService {
    public void addRule(String rule, int id);

    public void deletRule(int id, int operator);

    public List<Object> getAll();

}

package com.josie.quake.service;

import org.springframework.stereotype.Component;

/**
 * Created by Josie on 16/5/21.
 */

@Component
public interface FilterRuleService {
    public void addRule(String rule, int id);

    public void deletRule(int id, int operator);
}

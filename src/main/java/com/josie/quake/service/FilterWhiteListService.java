package com.josie.quake.service;

import org.springframework.stereotype.Component;

/**
 * Created by Josie on 16/5/21.
 */
@Component
public interface FilterWhiteListService {
    public void addWhiteList(
            String url,
            int operator);

    public void deleteWhiteList(
            int id,
            int operator);
}

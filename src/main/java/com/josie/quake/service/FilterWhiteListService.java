package com.josie.quake.service;

import com.josie.quake.model.FilterWhiteList;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<FilterWhiteList> getall();

}

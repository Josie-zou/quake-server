package com.josie.quake.service;

import com.josie.quake.model.QuakeInfo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Josie on 16/5/12.
 */
@Component
public interface QuakeInfoService {

    public List<QuakeInfo> getAllByCount(
            int start,
            int count);

    public List<QuakeInfo> getAllByStatusByCount(
            QuakeInfo.Status enable,
            int start,
            int count);

    public Map<String, ? extends Object> getAllByStatusByDate(
            QuakeInfo.Status enable,
            Date startDate,
            Date lastDate);

    public Map<String, ? extends Object> getAllByDate(
            Date startDate,
            Date lastDate);
}

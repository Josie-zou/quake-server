package com.josie.quake.service;

import com.josie.quake.model.QuakeInfo;
import org.springframework.stereotype.Component;

import java.text.ParseException;
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

    public List<Map<String, Object>> getAllByStatusByDate(
            QuakeInfo.Status enable,
            Date startDate,
            Date lastDate) throws ParseException;

    public List<Map<String, Object>> getAllByDate(
            Date startDate,
            Date lastDate) throws ParseException;

    public List<Map<String, ? extends Object>> getAllByTypeByStatus(QuakeInfo.Status enable);

    public List<Map<String, ? extends Object>> getAllByType();

    public int updateStatus(int uid, int id, int status);

    public List<Map<String, Object>> getByKeywords();

    public List<Map<String, Object>> getPublishTime();

    public Map<String, Object> getGatherInfo();

}

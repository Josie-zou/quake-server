package com.josie.quake.service.impl;

import com.josie.quake.dao.QuakeInfoDao;
import com.josie.quake.model.QuakeInfo;
import com.josie.quake.service.QuakeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Josie on 16/5/12.
 */
@Service
public class QuakeInfoServiceImpl implements QuakeInfoService {

    @Autowired
    private QuakeInfoDao quakeInfoDao;

    @Override
    public List<QuakeInfo> getAllByCount(
            int start,
            int count) {
        return quakeInfoDao.getAll(start, count);
    }

    @Override
    public List<QuakeInfo> getAllByStatusByCount(
            QuakeInfo.Status enable,
            int start,
            int count) {
        return quakeInfoDao.getAllByStatus(enable.toInt(), start, count);
    }

    @Override
    public List<Map<String, Object>> getAllByStatusByDate(
            QuakeInfo.Status enable,
            Date startDate,
            Date lastDate) throws ParseException {
        List<Map<String, Object>> result = quakeInfoDao.getAllByStatusByDate(enable.toInt(), startDate, lastDate);
        result = convertResult(result, startDate, 15);
        return result;
    }

    @Override
    public List<Map<String, Object>> getAllByDate(
            Date startDate,
            Date lastDate) throws ParseException {
        List<Map<String, Object>> result = quakeInfoDao.getAllByDate(startDate, lastDate);
        result = convertResult(result, startDate, 15);
        return result;
    }

    @Override
    public List<Map<String, ? extends Object>> getAllByTypeByStatus(QuakeInfo.Status enable) {
        return quakeInfoDao.getAllByTypeByStatus(enable.toInt());
    }

    @Override
    public List<Map<String, ? extends Object>> getAllByType() {
        return quakeInfoDao.getAllByType();
    }

    private List<Map<String, Object>> convertResult(
            List<Map<String, Object>> result,
            Date start,
            int days) throws ParseException {
        List<Map<String, Object>> newResult = new ArrayList<>();
        int k = 0;
        while (k <= days) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Map<String, Object> newMap = new HashMap<>();
            String dateString = simpleDateFormat.format(start);
            String count = "0";
            for (int i = 0; i < result.size(); i++) {
                Map<String, Object> map = result.get(i);
                Date date = simpleDateFormat.parse(map.get("date").toString());
                if (simpleDateFormat.format(date).equals(simpleDateFormat.format(start))) {
                    dateString = simpleDateFormat.format(date);
                    count = map.get("count").toString();
                    break;
                }
            }
            newMap.put("date", dateString);
            newMap.put("count", count);
            newResult.add(newMap);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(start);
            calendar.add(Calendar.DATE, 1);
            k++;
            start = calendar.getTime();
        }

        return newResult;
    }


}

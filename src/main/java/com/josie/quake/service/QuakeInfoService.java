package com.josie.quake.service;

import com.josie.quake.model.QuakeInfo;
import org.springframework.stereotype.Component;

import java.util.List;

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
}

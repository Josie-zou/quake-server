package com.josie.quake.controller;

import com.josie.quake.commons.Constant;
import com.josie.quake.commons.utils.ResponseUtils;
import com.josie.quake.model.QuakeInfo;
import com.josie.quake.model.User;
import com.josie.quake.service.QuakeInfoService;
import com.josie.quake.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Josie on 16/5/12.
 */
@Controller
@RequestMapping(value = "api/quake")
public class QuakeInfoController {

    @Autowired
    private QuakeInfoService quakeInfoService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "getall", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String getInfo(
            @RequestParam("id") String id,
            @RequestParam("start") String start,
            @RequestParam("count") String count) {

        User user = userService.getById(Integer.valueOf(id));
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnOK(quakeInfoService.getAllByStatusByCount(QuakeInfo.Status.Enable,
                    Integer.valueOf(start), Integer.valueOf(count)));
        } else {
            return ResponseUtils.returnOK(quakeInfoService.getAllByCount(Integer.valueOf(start),
                    Integer.valueOf(count)));
        }
    }
}

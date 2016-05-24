package com.josie.quake.controller;

import com.josie.quake.commons.Constant;
import com.josie.quake.commons.utils.ErrorInfo;
import com.josie.quake.commons.utils.ResponseUtils;
import com.josie.quake.model.User;
import com.josie.quake.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Glacier on 16/5/24.
 */
@Controller
@RequestMapping(value = "api")
public class SystemConfigController {

    @Autowired
    private SystemConfigService configService;

    @RequestMapping(value = "system/status", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String isSystemStart() {
        return ResponseUtils.returnOK(configService.isSystemStart());
    }

    @RequestMapping(value = "examine/status", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String isExamineStart() {
        return ResponseUtils.returnOK(configService.isExamineStart());
    }

    @RequestMapping(value = "examine/shutdown", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String shutdownExamine(HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        }
        configService.shutdownExamine();
        return ResponseUtils.returnOK();
    }

    @RequestMapping(value = "system/shutdown", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String shutdownSystem(HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        }
        configService.shutdownSystem();
        return ResponseUtils.returnOK();
    }

    @RequestMapping(value = "examine/start", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String startExamine(HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        }
        configService.startExamine();
        return ResponseUtils.returnOK();
    }

    @RequestMapping(value = "system/start", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String startSystem(HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        }
        configService.startSystem();
        return ResponseUtils.returnOK();
    }


}

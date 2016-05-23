package com.josie.quake.controller;

import com.josie.quake.commons.Constant;
import com.josie.quake.commons.utils.ErrorInfo;
import com.josie.quake.commons.utils.ResponseUtils;
import com.josie.quake.model.User;
import com.josie.quake.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Josie on 16/5/12.
 */
@Controller
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "getAll", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String getAll(
            @RequestParam("start") int start,
            @RequestParam("count") int count,
            HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        } else {
            return ResponseUtils.returnOK(userService.getAll(start, count));
        }
    }

    @RequestMapping(value = "get", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String getById(
            @RequestParam("id") String id) {
        return ResponseUtils.returnOK(userService.getById(Integer.valueOf(id)));
    }

    @RequestMapping(value = "update", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String update(
            @RequestParam(value = "username", required = false, defaultValue = "") String username,
            @RequestParam(value = "mailAdress", required = false, defaultValue = "") String mailAdress,
            @RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
            @RequestParam(value = "positon", required = false, defaultValue = "") String positon,
            @RequestParam(value = "qq", required = false, defaultValue = "") String qq,
            @RequestParam(value = "workPlace", required = false, defaultValue = "") String workPlace,
            HttpSession session) {
        User user = (User)session.getAttribute("user");
        userService.updateUser(user.getId(), username, mailAdress, qq, phoneNumber, positon, workPlace);
        user.setUsername(username);
        user.setMailAdress(mailAdress);
        user.setPhoneNumber(phoneNumber);
        user.setPositon(positon);
        user.setQq(qq);
        user.setWorkPlace(workPlace);
        session.setAttribute("user", user);
        return ResponseUtils.returnOK();
    }

    @RequestMapping(value = "addPrivilege", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String updatePrivilege(
            @RequestParam("userId") int userId,
            HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        } else {
            userService.addPrivilege(userId);
            return ResponseUtils.returnOK();
        }
    }
}

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
import java.util.List;

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

    @RequestMapping(value = "getall", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String getAll(HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        } else {
            return ResponseUtils.returnOK(userService.getall());
        }
    }

    @RequestMapping(value = "getAllByStatus", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String getAllByStatus(@RequestParam("start") int start,
                                 @RequestParam("count") int count,
                                 HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        } else {
            System.out.println(user.getPrivilege());
            return ResponseUtils.returnOK(userService.getAllLowerPrivilege(start, count, user.getPrivilege()));
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

    @RequestMapping(value = "delete", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String delete(@RequestParam("id") String id,
                         HttpSession session) {
        User user = (User)session.getAttribute("user");
        User delUser = userService.getById(Integer.parseInt(id));
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        }
        if (delUser == null) {
            return ResponseUtils.returnError(ErrorInfo.CANNOT_FIND_USER);
        }
        else {
            if (user.getPrivilege() >= User.Privilege.Admin.toInt() && delUser.getPrivilege() == User.Privilege.Common.toInt()) {
                userService.deleteUser(delUser.getId());
                return ResponseUtils.returnOK();
            }
            else if (user.getPrivilege() == User.Privilege.Root.toInt() && delUser.getPrivilege() < User.Privilege.Root.toInt()) {
                userService.deleteUser(delUser.getId());
                return ResponseUtils.returnOK();
            }
            else {
                return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
            }
        }
    }

    @RequestMapping(value = "setManager", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String setManager(@RequestParam("id") String id,
                             HttpSession session) {
        User user = (User) session.getAttribute("user");
        User manager = userService.getById(Integer.parseInt(id));
        if (user.getPrivilege() != User.Privilege.Root.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        }
        if (manager == null) {
            return ResponseUtils.returnError(ErrorInfo.CANNOT_FIND_USER);
        }
        else {
            manager.setPrivilege(User.Privilege.Admin.toInt());
            userService.updateUser(manager);
            return ResponseUtils.returnOK();
        }
    }

    @RequestMapping(value = "delManager", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String delManager(@RequestParam("id")String id,
                             HttpSession session) {
        User user = (User)session.getAttribute("user");
        User manager = userService.getById(Integer.parseInt(id));
        if (user.getPrivilege() != User.Privilege.Root.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        }
        if (manager == null) {
            return ResponseUtils.returnError(ErrorInfo.CANNOT_FIND_USER);
        }
        else {
            manager.setPrivilege(User.Privilege.Common.toInt());
            userService.updateUser(manager);
            return ResponseUtils.returnOK();
        }
    }
}

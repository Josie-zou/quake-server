package com.josie.quake.controller;

import com.josie.quake.commons.Constant;
import com.josie.quake.commons.utils.ErrorInfo;
import com.josie.quake.commons.utils.ResponseUtils;
import com.josie.quake.model.User;
import com.josie.quake.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Josie on 16/5/12.
 */
@Controller
@RequestMapping(value = "api")
public class LoginController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "login", produces = Constant.WebConstant.JSON_FORMAT)
    public String login(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        HttpSession session) {
        User user = userService.getByAccount(account);
        if (user == null) {
            return ResponseUtils.returnError(ErrorInfo.NO_LOGUP);
        }
        user = null;
        user = userService.getUserByAccountAndPassword(account, password);
        if (user == null) {
            return ResponseUtils.returnError(ErrorInfo.PASSWORD_ERROR);
        }
        session.setAttribute("username", user.getUsername());
        return ResponseUtils.returnOK(user);

    }

    @ResponseBody
    @RequestMapping(value = "signup", produces = Constant.WebConstant.JSON_FORMAT)
    public String signup(
            @RequestParam("username") String username,
            @RequestParam(value = "mail", required = false, defaultValue = "") String mail,
            @RequestParam(value = "mobile", required = false, defaultValue = "") String mobile,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "qq", required = false, defaultValue = "") String qq,
            @RequestParam(value = "workplace", required = false, defaultValue = "") String workplace,
            @RequestParam(value = "job", required = false, defaultValue = "") String job) {
        User user = new User();
        user.setUsername(username);
        user.setMailAdress(mail);
        user.setPhoneNumber(mobile);
        user.setPassword(password);
        user.setQq(qq);
        user.setWorkPlace(workplace);
        user.setPositon(job);

        try {
            userService.addUser(user);
        } catch (Exception e) {
            return ResponseUtils.returnError(ErrorInfo.LOGBEFORE);
        }
        return ResponseUtils.returnOK();
    }

    @RequestMapping(value = "logout", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String logout(HttpSession session) {
        session.removeAttribute("username");
        return ResponseUtils.returnOK();
    }
}

package com.josie.quake.controller;

import com.josie.quake.commons.Constant;
import com.josie.quake.commons.utils.ResponseUtils;
import com.josie.quake.model.User;
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
@RequestMapping(value = "")
public class LoginController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "login", produces = Constant.WebConstant.JSON_FORMAT)
    public String login(@RequestParam("account") String account,
                        @RequestParam("password") String password) {
        User user = userService.getUserByAccountAndPassword(account, password);
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

        userService.addUser(user);
        return ResponseUtils.returnOK();
    }

    @RequestMapping(value = "test", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String test() {
        return ResponseUtils.returnOK();
    }
}

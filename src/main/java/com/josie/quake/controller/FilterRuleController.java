package com.josie.quake.controller;

import com.josie.quake.commons.Constant;
import com.josie.quake.commons.utils.ErrorInfo;
import com.josie.quake.commons.utils.ResponseUtils;
import com.josie.quake.model.FilterRule;
import com.josie.quake.model.QuakeInfo;
import com.josie.quake.model.User;
import com.josie.quake.service.FilterRuleService;
import com.josie.quake.service.UserService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Josie on 16/5/21.
 */
@Controller
@RequestMapping(value = "api/filer")
public class FilterRuleController {

    @Autowired
    private UserService userService;

    @Autowired
    private FilterRuleService filterRuleService;

    @RequestMapping(value = "addRule", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String addFiler(
            @RequestParam("rule") String rule,
            HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        }
        filterRuleService.addRule(rule, user.getId());
        return ResponseUtils.returnOK();
    }

    @RequestMapping(value = "deleteRule", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String deleteRule(
            @RequestParam("operator") String operator,
            HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        }
        filterRuleService.deletRule(user.getId(), Integer.valueOf(operator));
        return ResponseUtils.returnOK();
    }

    @RequestMapping(value = "getAll", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String getAll(HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        }
        List<FilterRule> filterRules = filterRuleService.getAll();
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < filterRules.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            FilterRule filterRule = filterRules.get(i);
            map.put("id", filterRule.getId());
            map.put("username", userService.getById(filterRule.getOperator()).getUsername());
            map.put("rule", filterRule.getRule());
            map.put("createTime", filterRule.getCreateTime());
            result.add(map);
        }
        return ResponseUtils.returnOK(result);
    }

    @RequestMapping(value = "update", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String update(
            @RequestParam("id") String id,
            @RequestParam("rule") String rule,
            HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        }
        filterRuleService.update(Integer.valueOf(id), rule, user.getId());
        return ResponseUtils.returnOK();
    }

}

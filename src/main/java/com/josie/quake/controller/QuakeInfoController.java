package com.josie.quake.controller;

import com.alibaba.druid.util.StringUtils;
import com.josie.quake.commons.Constant;
import com.josie.quake.commons.utils.ErrorInfo;
import com.josie.quake.commons.utils.ResponseUtils;
import com.josie.quake.model.QuakeInfo;
import com.josie.quake.model.User;
import com.josie.quake.service.QuakeInfoService;
import com.josie.quake.service.SystemConfigService;
import com.josie.quake.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private SystemConfigService configService;

    @RequestMapping(value = "getall", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String getInfo(
            @RequestParam("status") String status,
            @RequestParam("start") String start,
            @RequestParam("count") String count,
            HttpSession session) {

        User user = (User)session.getAttribute("user");
        List<QuakeInfo> quakeInfos = null;
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            if (configService.isExamineStart()) {
                quakeInfos = quakeInfoService.getAllByStatusByCount(QuakeInfo.Status.Enable,
                        Integer.valueOf(start), Integer.valueOf(count));
            }
            else {
                quakeInfos = quakeInfoService.getAllByCount(Integer.valueOf(start),
                        Integer.valueOf(count));
            }

        } else {
            if (StringUtils.equalsIgnoreCase(status, "1") ) {
                quakeInfos = quakeInfoService.getAllByStatusByCount(QuakeInfo.Status.Enable,
                        Integer.valueOf(start), Integer.valueOf(count));
            }
            else if (StringUtils.equalsIgnoreCase(status, "2")) {
                quakeInfos = quakeInfoService.getAllByStatusByCount(QuakeInfo.Status.UNVERIFY,
                        Integer.valueOf(start), Integer.valueOf(count));
            }
            else if (StringUtils.equalsIgnoreCase(status, "3")) {
                quakeInfos = quakeInfoService.getAllByStatusByCount(QuakeInfo.Status.Disable,
                        Integer.valueOf(start), Integer.valueOf(count));
            }
            else {
                quakeInfos = quakeInfoService.getAllByCount(Integer.valueOf(start),
                        Integer.valueOf(count));
            }
        }
        List<Map<String, Object>> result = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for ( QuakeInfo quakeInfo : quakeInfos ) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", quakeInfo.getId());
            map.put("title", quakeInfo.getTitle());
            map.put("jumpTo", quakeInfo.getJumpTo());
            map.put("type", quakeInfo.getType());
            map.put("description", quakeInfo.getDescription());
            map.put("createTime", format.format(quakeInfo.getCreateTime()));
            map.put("publishTime", format.format(quakeInfo.getPublishTime()));
            map.put("verifyTime", format.format(quakeInfo.getVerifyTime()));
            if ( quakeInfo.getStatus() == 0 ) {
                map.put("status", "已删除");
            }
            else if (quakeInfo.getStatus() == 1) {
                map.put("status", "已审核");
            }
            else if (quakeInfo.getStatus() == 2) {
                map.put("status", "未审核");
            }
            else {
                map.put("status", "无法识别");
            }
            map.put("manager", userService.getById(quakeInfo.getManager()).getUsername());
            result.add(map);
        }
        return ResponseUtils.returnOK(result);
    }

    @RequestMapping(value = "getByDate", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String getByDate(HttpSession session) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        Date startDate = calendar.getTime();
        Date lastDate = new Date();

        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnOK(quakeInfoService.getAllByStatusByDate(QuakeInfo.Status.Enable, startDate, lastDate));
        } else {
            return ResponseUtils.returnOK(quakeInfoService.getAllByDate(startDate, lastDate));
        }
    }

    @RequestMapping(value = "getByType", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String getByType(HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnOK(quakeInfoService.getAllByTypeByStatus(QuakeInfo.Status.Enable));
        } else {
            return ResponseUtils.returnOK(quakeInfoService.getAllByType());
        }
    }

    @RequestMapping(value = "getByKeywords", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String getByKeywords(HttpSession session) {
        return ResponseUtils.returnOK(quakeInfoService.getByKeywords());
    }

    @RequestMapping(value = "getByPublishTime", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String getByPublishTime(HttpSession session) {
        return ResponseUtils.returnOK(quakeInfoService.getPublishTime());
    }

    @RequestMapping(value = "getGatherInfo", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String getGatherInfo(HttpSession session) {
        return ResponseUtils.returnOK(quakeInfoService.getGatherInfo());
    }

    @RequestMapping(value = "examine/pass", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String passExamine(@RequestParam("id") String[] quakeIDs,
                            HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        } else {
            boolean flag = true;
            for ( String quakeID : quakeIDs ) {
                flag = flag && (quakeInfoService.updateStatus(user.getId(), Integer.valueOf(quakeID), 1) > 0);
            }
            if (flag) {
                return ResponseUtils.returnOK();
            }
            return ResponseUtils.returnError(ErrorInfo.UNKNOWN_ERROR);
        }
    }

    @RequestMapping(value = "examine/delete", produces = Constant.WebConstant.JSON_FORMAT)
    @ResponseBody
    public String delExamine(@RequestParam("id") String[] quakeIDs,
                            HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getPrivilege() == User.Privilege.Common.toInt()) {
            return ResponseUtils.returnError(ErrorInfo.NO_PRIVILEGE);
        } else {
            boolean flag = true;
            for ( String quakeID : quakeIDs ) {
                flag = flag && (quakeInfoService.updateStatus(user.getId(), Integer.valueOf(quakeID), 0) > 0);
            }
            if (flag) {
                return ResponseUtils.returnOK();
            }
            return ResponseUtils.returnError(ErrorInfo.UNKNOWN_ERROR);
        }
    }
}

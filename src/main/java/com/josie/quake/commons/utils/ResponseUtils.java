package com.josie.quake.commons.utils;

import com.google.common.collect.ImmutableMap;
import com.josie.quake.commons.Constant;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Josie on 16/5/11.
 */
public final class ResponseUtils {


    private ResponseUtils() {
    }

    /**
     * 设置返回类型为json <br>
     * <br>
     *
     * @param response
     */
    public static void setResponseType2Json(
            HttpServletResponse response) {
        response.setContentType(Constant.WebConstant.JSON_FORMAT);
    }

    /**
     * 返回正常的json <br>
     * <br>
     *
     * @param data
     * @return
     */
    public static String returnOK(
            Map<String, ? extends Object> data) {
        return returnOK((Object) data);
    }

    /**
     * 返回正常的数据json <br>
     * <br>
     *
     * @return
     */
    public static String returnOK(
            Object data) {
        Map<String, Object> json = ImmutableMap
                .of(Constant.WebConstant.CODE, Constant.WebConstant.STATUS_OK, "data", data);
        return JacksonUtils.toJson(json);
    }

    /**
     * 返回正常的数据json <br>
     * <br>
     *
     * @param data
     * @param totalCount
     * @return
     */
    public static String returnOK(
            Object totalCount,
            Object data) {
        Map<String, Object> json = ImmutableMap
                .of(Constant.WebConstant.CODE, Constant.WebConstant.STATUS_OK, "totalcount", totalCount, "data", data);
        return JacksonUtils.toJson(json);
    }

    /**
     * 返回正常的json <br>
     * <br>
     *
     * @return
     */
    public static String returnOK() {
        Map<String, ? extends Object> json = ImmutableMap.of(Constant.WebConstant.CODE, Constant.WebConstant.STATUS_OK);
        return JacksonUtils.toJson(json);
    }

    /**
     * 返回正常的json <br>
     * <br>
     *
     * @param data
     * @param totalCount
     * @return
     */
    public static String returnOK(
            String totalCount,
            String data) {
        Map<String, ? extends Object> json = ImmutableMap
                .of(Constant.WebConstant.CODE, Constant.WebConstant.STATUS_OK, "totalcount", totalCount, "data", data);
        return JacksonUtils.toJson(json);
    }

    /**
     * 返回正常的json <br>
     * <br>
     *
     * @param data
     * @return
     */
    public static String returnOK(
            String data) {
        Map<String, ? extends Object> json = ImmutableMap
                .of(Constant.WebConstant.CODE, Constant.WebConstant.STATUS_OK, "data", data);
        return JacksonUtils.toJson(json);
    }

    /**
     * errorinfo <br>
     *
     * @param errorInfo
     * @return
     */
    public static String returnError(
            ErrorInfo errorInfo) {

        int code = errorInfo.getCode();
        String msg = errorInfo.getMsg() == null ? "" : errorInfo.getMsg();
        return JacksonUtils.toJson(ImmutableMap.of(Constant.WebConstant.CODE, code, Constant.WebConstant.MSG, msg));
    }

    /**
     * 返回参数错误 <br>
     * <br>
     *
     * @param e
     * @return
     */
    public static String returnError(
            Exception e) {

        String code = String.valueOf(ErrorInfo.ARGUMENG_ERROR.getCode());
        String msg = e.getMessage() == null ? "" : e.getMessage();

        Map<String, String> json = ImmutableMap.of(Constant.WebConstant.CODE, code, Constant.WebConstant.MSG, msg);
        return JacksonUtils.toJson(json);
    }

    /**
     * 返回错误,提示未知错误<br>
     * <br>
     *
     * @return
     */
    public static String returnError() {
        return returnError(ErrorInfo.UNKNOWN_ERROR);
    }

    /**
     * 向model中添加错误信息,用于向jsp反馈错误信息 <br>
     * <br>
     *
     * @param modelAndView
     * @param errorInfo
     * @return
     */
    public static final ModelAndView addErrorInfo(
            ModelAndView modelAndView,
            ErrorInfo errorInfo) {
        modelAndView.addObject(Constant.WebConstant.CODE, errorInfo.getCode());
        modelAndView.addObject(Constant.WebConstant.MSG, errorInfo.getMsg());
        return modelAndView;
    }

}

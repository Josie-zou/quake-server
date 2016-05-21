package com.josie.quake.commons;

/**
 * Created by Josie on 16/5/11.
 */
public class Constant {
    public final static class WebConstant {
        /**
         * 传递给前端的消息
         */
        public static final String CODE = "code";
        public static final String MSG = "msg";

        /**
         * content type json
         */
        public static final String JSON_FORMAT = "application/json;charset=UTF-8";
        public static final String XML_FORMAT = "text/xml;charset=UTF-8";

        public static final Integer STATUS_OK = 0;
    }

    public final static class PriviliegeStatus {
        public static final Integer COMMOMUSER = 1;
        public static final Integer ROOT = 3;
        public static final Integer ADMIN = 2;
    }

    public final static class Status{
        public static final Integer VERIFIED = 1;
        public static final Integer UN_VERIFY = 2;
    }
}

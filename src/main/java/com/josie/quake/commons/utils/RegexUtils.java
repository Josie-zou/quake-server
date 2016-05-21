package com.josie.quake.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Josie on 16/5/11.
 */
public class RegexUtils {

    /**
     * 是否符合邮箱标准 <br>
     * <br>
     *
     * @param src
     * @return
     */
    public static final boolean isEmail(
            String src) {
        String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        return match(src, regex);
    }


    /**
     * 是否为wiki页面的验证码 <br>
     * <br>
     *
     * @param src
     * @return
     */
    public static final boolean isVerification(
            String src) {
        String regex = "(.*)\\/jcaptcha(.*)";
        return match(src, regex);
    }


    /**
     * 是否是图片格式 <br>
     * <br>
     *
     * @param src
     * @return
     */
    public static final boolean isImg(
            String src) {
        String regex = "(.*).(jpg|png|gif|jpeg|bmp|ico|jng|psd|JPG|PNG|GIF|JPEG|BMP|ICO|JNG|PSD)$";
        return match(src, regex);
    }

    /**
     * 是否是JS格式的文件 <br>
     * <br>
     *
     * @param src
     * @return
     */
    public static final boolean isJS(
            String src) {
        String regex = "(.*).(js\\?(.*)|(js$))";
        return match(src, regex);
    }

    /**
     * 是否是CSS格式的文件 <br>
     * <br>
     *
     * @param src
     * @return
     */
    public static boolean isCSS(
            String src) {
        String regex = "(.*).(css\\?(.*)|(css$))";
        return match(src, regex);
    }

    /**
     * 判断是否是url的后缀 <br>
     * <br>
     *
     * @param src
     * @return
     */
    public static final boolean isURLSuffix(
            String src) {
        String regex = "\\/(.*)";
        return match(src, regex);
    }


    /**
     * 符合电话号码 <br>
     * 2015年4月2日:下午8:08:14<br>
     * <br>
     *
     * @param src
     * @return <pre>
     * </pre>
     */
    public static final boolean isPhoneNumber(
            String src) {
        String regex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        return match(src, regex);
    }

    /**
     * 进行匹配工作 <br>
     * 2015年4月2日:下午8:04:30<br>
     * <br>
     *
     * @param src
     * @param regex
     * @return
     */
    private static final boolean match(
            String src,
            String regex) {

        return src.matches(regex);
        //        Pattern pattern = Pattern.compile(regex);
        //        Matcher matcher = pattern.matcher(src);
        //        return matcher.find();
    }

    /**
     * 过滤纯文本
     *
     * @param src
     * @return
     */
    public static final String htmlFilter(
            String src) {
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(src);
        return m_html.replaceAll(""); //过滤html标签
    }

    public static final boolean isDouble(
            String src) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(src).matches();
    }

    /**
     * 找到所有符合的字符串 <br>
     * 2015年9月23日:上午10:21:36<br>
     * <br>
     *
     * @param src
     * @param regex
     * @return
     */
    @Deprecated
    public static final List<String> groupAll(
            String src,
            String regex) {
        return groupAll(src, regex, Pattern.CASE_INSENSITIVE);
    }

    /**
     * 找到所有符合的字符串 <br>
     * 2015年9月23日:上午10:21:36<br>
     * <br>
     *
     * @param src
     * @param regex
     * @param flags
     * @return
     */
    @Deprecated
    public static final List<String> groupAll(
            String src,
            String regex,
            int flags) {
        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(src);
        List<String> results = new ArrayList<String>();
        while (matcher.find()) {
            results.add(matcher.group(0));
        }
        return results;
    }

    public static final String groupIndex(
            String src,
            String regex,
            int index) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(src);
        if (matcher.find()) {
            return matcher.group(index);
        } else {
            return null;
        }
    }

    /**
     * 获取两个字符之间的字符串 <br>
     * 2015年12月16日:上午11:19:18<br>
     * <br>
     *
     * @param src
     * @param startChar
     * @param endChar
     * @return
     */
    public static final String getBetween(
            String src,
            String startChar,
            String endChar) {
        if (src == null || src.length() == 0) {
            return src;
        }
        int startIndex = src.indexOf(startChar);
        if (startIndex == -1) {
            return "";
        }
        int endIndex = src.lastIndexOf(endChar);
        if (endIndex == -1) {
            return "";
        }
        if (startIndex >= endIndex) {
            return "";
        }
        String result = src.substring(startIndex + 1, endIndex);

        return result;
    }

//    public static void main(
//            String[] args) throws IOException {
//        //        System.out.println("123a4".matches("^[0-9]+$"));
//        //        System.out.println(isEmail("yuzhu.peng@ren-i.com"));
//        //        System.out.println(htmlFilter("<font color='red'>0</font><font color='red'>0</font><font color='red'>1</font><font color='red'>1</font>38"));
//
//        String regex0 = "<string>(http|https)://\\w+\\.\\w+\\.\\w+/(\\.|\\w+|/|-|_)+</string>";
//        String regex = "[a-zA-z]+://[^\\s]*";
//        String string = FileUtils.readFileToString(new File("d:/work/channel.plist"));
//        String[] plistLines = string.split("\n");
//        for (String plistLine : plistLines) {
//            if (plistLine.contains("ipa")) {
//                System.out.println(plistLine.trim());
//                break;
//            }
//        }
//        List<String> groupAll = RegexUtils.groupAll(string, regex);
//        System.out.println(groupAll);
//
//                System.out.println(getBetween(" if (FlierWaitForArrivalFragment.COMMON_MESSAGE_TYPE_FIRST_GRAB_TIP != recommendType) {", "!", "!"));


}

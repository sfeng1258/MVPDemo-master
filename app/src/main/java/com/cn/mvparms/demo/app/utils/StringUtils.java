package com.cn.mvparms.demo.app.utils;

import android.text.TextUtils;

import com.jess.arms.utils.LogUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 200179
 * @version create time2013-3-14 下午2:37:13
 */
public class StringUtils {
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    // 判断字符串里面是否全部是数字
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static String toHexString(byte[] b) { // byte to String
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    /**
     * MD5加密字符串
     */
    public static String md5(String source) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(source.getBytes());
            byte[] mess = digest.digest();
            return toHexString(mess);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return source;
    }

    public static boolean isEmpty(String text) {
        if (text == null) {
            return true;
        }
        String regEx = "\\s*";
        return text.matches(regEx);
    }

    /**
     * 去除HTML标签
     * @param src 需要去除HTML标签的字符串
     * @param replacement 要替换为的字符串
     * @return
     */
    public static String removeHtmlTag(String src, String replacement) {
        if (src == null) {
            return "";
        }
        String regEx = "<[^>]*>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(src);
        return m.replaceAll(replacement).trim();
    }

    public static String phoneNumMask(String str){
        if (str==null||str.equals("")||str.length()!=11){
            return str;
        }
        String newStrPrefix=str.substring(0,3);
        String newStrSuffix=str.substring(7,11);
        return newStrPrefix+"****"+newStrSuffix;
    }
    public static String phoneNumMask1(String str){
        if (str==null||str.equals("")||str.length()!=11){
            return str;
        }
        String newStrPrefix=str.substring(0,3);
        String newStrSuffix=str.substring(9,11);
        return newStrPrefix+"******"+newStrSuffix;
    }

    /**
     * 校验银行卡卡号
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if(bit == 'N'){
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }
    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId){
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }

    /**
     * 替换字符串中特殊字符
     * @param str
     * @return
     */
    public static String filter(String str) {
        String regEx = "[`!@#$%^&*()+={}':;,\\[\\].<>?￥%…（）_+|【】‘；：”“’。，、？/\\s]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 检验IP地址是否合法
     * @param str
     * @return
     */
    public static boolean checkIP(String str) {
        Pattern pattern = Pattern
                .compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]"
                        + "|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$");
        return pattern.matcher(str).matches();
    }

    /**
     * 移除多余的 /
     * @return
     */
    public static String repalceAreaServer(String str) {
        String result = "";
        if(!TextUtils.isEmpty(str)) {
            result = str.replace("//", "/");
        }
        if(!TextUtils.isEmpty(result) && result.endsWith("/")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    /**
     * 替换 -
     * @param str
     * @return
     */
    public static String replaceDateTime(String str) {
        if(!TextUtils.isEmpty(str) && str.contains("-")) {
            return str.replace("-", "/");
        }
        return str;
    }

    /**
     * 是否全为汉字
     * @param str
     * @return
     */
    public static Boolean isAllChinese(String str) {
        Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5]{1,9}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    /**
     * 是否为正确的身份证号码
     * @param idNum
     * @return
     */
    public static Boolean isIdNm(String idNum) {
        Pattern pattern = Pattern.compile("(^\\d{15}$)|(^\\d{17}([0-9]|X|x)$)");
        Matcher matcher = pattern.matcher(idNum);
        return matcher.matches();
    }

    /**
     * 将指定字符串里面的指定位置的字符转换为需要的字符
     * @param originMsg
     * @param start
     * @param end
     * @param symbolUWant
     * @return
     */
    public static String replaceChar2Symbol(String originMsg, int start, int end, String symbolUWant) {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < originMsg.length(); i++) {
                char c = originMsg.charAt(i);
                if (i >= start && i <= end) {
                    sb.append(symbolUWant);
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            LogUtils.debugInfo("StringUtils",e.toString());
            return "";

        }
    }

}

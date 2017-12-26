package com.cn.mvparms.demo.app.utils;

import com.cn.mvparms.demo.mvp.model.api.Api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 网络请求所使用的方法
 *
 * @author Swain
 */
public class NetUtils {
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * Sign 加密
     *
     * @param type 传输的类型 false: RequestToken true：access
     */
    public static String SignBase64(long time, String rquestSecret, String requestToken, boolean type) {
        StringBuffer sb = new StringBuffer();
        sb.append(Api.APP_ID).append("&").append(Api.RESULT_VERSION).append("&")
                .append(Api.RESULT_TYPE).append("&");
        if (type)
            sb.append(requestToken).append("&");
        sb.append(Api.RESULT_MD5).append("&").append(time);
        String sign = signParas(sb.toString(), rquestSecret, type);
        return Base64Utils.encodeBytes(sign.getBytes());
    }

    /**
     * 签名参数信息
     */
    private static String signParas(String paras, String rquestSecret, boolean type) {
        if (!type)
            return Api.KAYVALUE + "&" + SetMD5(paras) + "&";
        else
            return Api.KAYVALUE + "&" + SetMD5(paras) + "&" + rquestSecret;
    }

    /**
     * MD5加密
     */
    public static String SetMD5(String source) {
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

    public static String toHexString(byte[] b) { // byte to String
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static String Md5(String plainText) {
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }
}

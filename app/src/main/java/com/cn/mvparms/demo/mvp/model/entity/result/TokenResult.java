package com.cn.mvparms.demo.mvp.model.entity.result;

/**
 * Created by sfeng on 2017/12/12. <Token返回值>
 */

public class TokenResult {


    /**
     * BizResult : {"RequestToken":"r_F79D79BA00973400944B8C7F988345C6","RequestSecret":"rs_4C2978B9967E640EDD5FFBC59F002972","Appid":"and5173","TimeStamp":1513077625}
     * Status : 0
     * FeedBackResult :
     * Key :
     */

    private BizResultBean BizResult;
    private int Status;
    private String FeedBackResult;
    private String Key;

    @Override
    public String toString() {
        return "TokenResult{" +
                "BizResult=" + BizResult +
                ", Status=" + Status +
                ", FeedBackResult='" + FeedBackResult + '\'' +
                ", Key='" + Key + '\'' +
                '}';
    }

    public BizResultBean getBizResult() {
        return BizResult;
    }

    public void setBizResult(BizResultBean BizResult) {
        this.BizResult = BizResult;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getFeedBackResult() {
        return FeedBackResult;
    }

    public void setFeedBackResult(String FeedBackResult) {
        this.FeedBackResult = FeedBackResult;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String Key) {
        this.Key = Key;
    }

    public static class BizResultBean {
        /**
         * RequestToken : r_F79D79BA00973400944B8C7F988345C6
         * RequestSecret : rs_4C2978B9967E640EDD5FFBC59F002972
         * Appid : and5173
         * TimeStamp : 1513077625
         */

        private String RequestToken;
        private String RequestSecret;
        private String Appid;
        private int TimeStamp;

        @Override
        public String toString() {
            return "BizResultBean{" +
                    "RequestToken='" + RequestToken + '\'' +
                    ", RequestSecret='" + RequestSecret + '\'' +
                    ", Appid='" + Appid + '\'' +
                    ", TimeStamp=" + TimeStamp +
                    '}';
        }

        public String getRequestToken() {
            return RequestToken;
        }

        public void setRequestToken(String RequestToken) {
            this.RequestToken = RequestToken;
        }

        public String getRequestSecret() {
            return RequestSecret;
        }

        public void setRequestSecret(String RequestSecret) {
            this.RequestSecret = RequestSecret;
        }

        public String getAppid() {
            return Appid;
        }

        public void setAppid(String Appid) {
            this.Appid = Appid;
        }

        public int getTimeStamp() {
            return TimeStamp;
        }

        public void setTimeStamp(int TimeStamp) {
            this.TimeStamp = TimeStamp;
        }
    }
}

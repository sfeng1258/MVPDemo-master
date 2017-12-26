package com.cn.mvparms.demo.mvp.model.entity.result;

/**
 * Created by sfeng on 2017/12/12. <>
 */

public class AccessResult {

    /**
     * BizResult : {"AccessToken":"a_DFD606318D95057D42ADD09E7F05E196","AccessSecret":"as_3E76EB31533D0C75E538D6AB9381DDA7","Appid":"and5173","TimeStamp":1513077800}
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
        return "AccessResult{" +
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
         * AccessToken : a_DFD606318D95057D42ADD09E7F05E196
         * AccessSecret : as_3E76EB31533D0C75E538D6AB9381DDA7
         * Appid : and5173
         * TimeStamp : 1513077800
         */

        private String AccessToken;
        private String AccessSecret;
        private String Appid;
        private int TimeStamp;

        @Override
        public String toString() {
            return "BizResultBean{" +
                    "AccessToken='" + AccessToken + '\'' +
                    ", AccessSecret='" + AccessSecret + '\'' +
                    ", Appid='" + Appid + '\'' +
                    ", TimeStamp=" + TimeStamp +
                    '}';
        }

        public String getAccessToken() {
            return AccessToken;
        }

        public void setAccessToken(String AccessToken) {
            this.AccessToken = AccessToken;
        }

        public String getAccessSecret() {
            return AccessSecret;
        }

        public void setAccessSecret(String AccessSecret) {
            this.AccessSecret = AccessSecret;
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

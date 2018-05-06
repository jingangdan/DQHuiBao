package com.dq.huibao.bean.tixian;

/**
 * 提现账号信息
 * Created by d on 2018/5/6.
 */

public class TiXianAccountInfoB {
    /**
     * status : 1
     * data : {"id":"6","uid":"21","type":"1","desc":"邮政储蓄兰山支行","account":"1556355223645455","ctime":"1524901626"}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 6
         * uid : 21
         * type : 1
         * desc : 邮政储蓄兰山支行
         * account : 1556355223645455
         * ctime : 1524901626
         */

        private String id;
        private String uid;
        private String type;
        private String desc;
        private String account;
        private String ctime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }
    }
}

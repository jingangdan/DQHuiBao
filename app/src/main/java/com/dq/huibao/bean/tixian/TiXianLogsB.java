package com.dq.huibao.bean.tixian;

import java.util.List;

/**
 * 提现记录
 * Status 0 处理中 1 提现成功 2提现失败 -1 提现申请已取消
 * Created by d on 2018/5/5.
 */

public class TiXianLogsB {
    /**
     * status : 1
     * data : [{"id":"9","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"0","ptime":"0","ctime":"1524790568","flag":"处理中"},{"id":"10","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"0","ptime":"0","ctime":"1524790568","flag":"处理中"},{"id":"11","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"0","ptime":"0","ctime":"1524790568","flag":"处理中"},{"id":"12","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"0","ptime":"0","ctime":"1524790568","flag":"处理中"},{"id":"13","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"0","ptime":"0","ctime":"1524790568","flag":"处理中"},{"id":"14","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"0","ptime":"0","ctime":"1524790568","flag":"处理中"},{"id":"15","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"0","ptime":"0","ctime":"1524790568","flag":"处理中"},{"id":"16","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"0","ptime":"0","ctime":"1524790568","flag":"处理中"},{"id":"17","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"0","ptime":"0","ctime":"1524790568","flag":"处理中"},{"id":"18","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"0","ptime":"0","ctime":"1524790568","flag":"处理中"},{"id":"19","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"1","ptime":"0","ctime":"1524790568","flag":"提现成功"},{"id":"20","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"1","ptime":"0","ctime":"1524790568","flag":"提现成功"},{"id":"21","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"1","ptime":"0","ctime":"1524790568","flag":"提现成功"},{"id":"22","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"-1","ptime":"0","ctime":"1524790568","flag":"提现申请已取消"},{"id":"23","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"-1","ptime":"0","ctime":"1524790568","flag":"提现申请已取消"},{"id":"24","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"-1","ptime":"0","ctime":"1524790568","flag":"提现申请已取消"},{"id":"25","uid":"21","money":"20.00","account":"22222222222222","account_desc":"托尔斯泰","status":"-1","ptime":"0","ctime":"1524790568","flag":"提现申请已取消"}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 9
         * uid : 21
         * money : 20.00
         * account : 22222222222222
         * account_desc : 托尔斯泰
         * status : 0
         * ptime : 0
         * ctime : 1524790568
         * flag : 处理中
         */

        private String id;
        private String uid;
        private String money;
        private String account;
        private String account_desc;
        private String status;
        private String ptime;
        private String ctime;
        private String flag;

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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getAccount_desc() {
            return account_desc;
        }

        public void setAccount_desc(String account_desc) {
            this.account_desc = account_desc;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }
}

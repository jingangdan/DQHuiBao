package com.dq.huibao.bean;

/**
 * 话费订单
 * Created by d on 2018/4/26.
 */

public class RechargeOrderB {
    /**
     * status : 1
     * data : {"pay_money":20,"ordersn":"MP201804261704483598","pay_mobile":"15854968931","bill":20,"ctime":1524733488,"status":0,"uid":21}
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
         * pay_money : 20
         * ordersn : MP201804261704483598
         * pay_mobile : 15854968931
         * bill : 20
         * ctime : 1524733488
         * status : 0
         * uid : 21
         */

        private int pay_money;
        private String ordersn;
        private String pay_mobile;
        private int bill;
        private int ctime;
        private int status;
        private int uid;

        public int getPay_money() {
            return pay_money;
        }

        public void setPay_money(int pay_money) {
            this.pay_money = pay_money;
        }

        public String getOrdersn() {
            return ordersn;
        }

        public void setOrdersn(String ordersn) {
            this.ordersn = ordersn;
        }

        public String getPay_mobile() {
            return pay_mobile;
        }

        public void setPay_mobile(String pay_mobile) {
            this.pay_mobile = pay_mobile;
        }

        public int getBill() {
            return bill;
        }

        public void setBill(int bill) {
            this.bill = bill;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}

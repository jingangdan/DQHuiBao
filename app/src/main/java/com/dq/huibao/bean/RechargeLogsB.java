package com.dq.huibao.bean;

import java.util.List;

/**
 * 话费充值记录
 * Created by d on 2018/4/26.
 */

public class RechargeLogsB {
    /**
     * status : 1
     * data : [{"id":"45","ordersn":"MP201804261704483598","payorder":null,"paytype":null,"trade_type":null,"bill":"00000020.00","pay_money":"20.00","pay_mobile":"15854968931","ctime":"1524733488","status":"未充值","openid":null,"uid":"21","paytime":null},{"id":"46","ordersn":"MP201804261709032222","payorder":null,"paytype":null,"trade_type":null,"bill":"00000020.00","pay_money":"20.00","pay_mobile":"15854968931","ctime":"1524733743","status":"未充值","openid":null,"uid":"21","paytime":null},{"id":"47","ordersn":"MP201804261723587210","payorder":null,"paytype":null,"trade_type":null,"bill":"00000020.00","pay_money":"20.00","pay_mobile":"15854968931","ctime":"1524734638","status":"未充值","openid":null,"uid":"21","paytime":null}]
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
         * id : 45
         * ordersn : MP201804261704483598
         * payorder : null
         * paytype : null
         * trade_type : null
         * bill : 00000020.00
         * pay_money : 20.00
         * pay_mobile : 15854968931
         * ctime : 1524733488
         * status : 未充值
         * openid : null
         * uid : 21
         * paytime : null
         */

        private String id;
        private String ordersn;
        private String payorder;
        private String paytype;
        private String trade_type;
        private String bill;
        private String pay_money;
        private String pay_mobile;
        private String ctime;
        private String status;
        private String openid;
        private String uid;
        private String paytime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrdersn() {
            return ordersn;
        }

        public void setOrdersn(String ordersn) {
            this.ordersn = ordersn;
        }

        public String getPayorder() {
            return payorder;
        }

        public void setPayorder(String payorder) {
            this.payorder = payorder;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public String getBill() {
            return bill;
        }

        public void setBill(String bill) {
            this.bill = bill;
        }

        public String getPay_money() {
            return pay_money;
        }

        public void setPay_money(String pay_money) {
            this.pay_money = pay_money;
        }

        public String getPay_mobile() {
            return pay_mobile;
        }

        public void setPay_mobile(String pay_mobile) {
            this.pay_mobile = pay_mobile;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }
    }
}

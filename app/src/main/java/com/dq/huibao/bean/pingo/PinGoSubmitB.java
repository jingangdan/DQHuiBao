package com.dq.huibao.bean.pingo;

/**
 * 拼go下单成功返回
 * Created by d on 2018/5/10.
 */

public class PinGoSubmitB {
    /**
     * status : 1
     * data : {"status":1,"ordersn":"DXS201805101553048235","orderid":"430"}
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
         * status : 1
         * ordersn : DXS201805101553048235
         * orderid : 430
         */

        private int status;
        private String ordersn;
        private String orderid;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getOrdersn() {
            return ordersn;
        }

        public void setOrdersn(String ordersn) {
            this.ordersn = ordersn;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }
    }
}

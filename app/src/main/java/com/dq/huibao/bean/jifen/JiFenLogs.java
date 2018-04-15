package com.dq.huibao.bean.jifen;

import java.util.List;

/**
 * 积分兑换的商品记录
 * Created by d on 2018/4/15.
 */

public class JiFenLogs {
    /**
     * status : 1
     * data : [{"id":"1","mid":"21","gid":"2","goodsname":"商品名称","price":"5.00","score":"3000","expprice":"6.00","paystatus":"0","expstatus":"0","status":"0","addtime":null,"expaddr":"邮寄地址","mobile":"联系手机","addressee":"联系人","youbian":"邮编","expcode":"快递单号","expname":"快递公司"}]
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
         * id : 1
         * mid : 21
         * gid : 2
         * goodsname : 商品名称
         * price : 5.00
         * score : 3000
         * expprice : 6.00
         * paystatus : 0
         * expstatus : 0
         * status : 0
         * addtime : null
         * expaddr : 邮寄地址
         * mobile : 联系手机
         * addressee : 联系人
         * youbian : 邮编
         * expcode : 快递单号
         * expname : 快递公司
         */

        private String id;
        private String mid;
        private String gid;
        private String goodsname;
        private String price;
        private String score;
        private String expprice;
        private String paystatus;
        private String expstatus;
        private String status;
        private Object addtime;
        private String expaddr;
        private String mobile;
        private String addressee;
        private String youbian;
        private String expcode;
        private String expname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getExpprice() {
            return expprice;
        }

        public void setExpprice(String expprice) {
            this.expprice = expprice;
        }

        public String getPaystatus() {
            return paystatus;
        }

        public void setPaystatus(String paystatus) {
            this.paystatus = paystatus;
        }

        public String getExpstatus() {
            return expstatus;
        }

        public void setExpstatus(String expstatus) {
            this.expstatus = expstatus;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getAddtime() {
            return addtime;
        }

        public void setAddtime(Object addtime) {
            this.addtime = addtime;
        }

        public String getExpaddr() {
            return expaddr;
        }

        public void setExpaddr(String expaddr) {
            this.expaddr = expaddr;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddressee() {
            return addressee;
        }

        public void setAddressee(String addressee) {
            this.addressee = addressee;
        }

        public String getYoubian() {
            return youbian;
        }

        public void setYoubian(String youbian) {
            this.youbian = youbian;
        }

        public String getExpcode() {
            return expcode;
        }

        public void setExpcode(String expcode) {
            this.expcode = expcode;
        }

        public String getExpname() {
            return expname;
        }

        public void setExpname(String expname) {
            this.expname = expname;
        }
    }
}

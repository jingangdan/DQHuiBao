package com.dq.huibao.bean.coupons;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品-可使用优惠券
 * Created by d on 2018/4/25.
 */

public class CouponsB {
    /**
     * status : 1
     * data : [{"id":"1","cid":"1","mid":"21","gettime":"1213333311","dotime":"1213123131","starttime":"1213123131","endtime":"1993123131","status":"0","youhui":"100.00"},{"id":"2","cid":"2","mid":"21","gettime":"1213123131","dotime":"1213123131","starttime":"1213123131","endtime":"1993123131","status":"0","youhui":"66.00"}]
     */

    private int status;
    private List<DataBean> data = new ArrayList<>();

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
         * cid : 1
         * mid : 21
         * gettime : 1213333311
         * dotime : 1213123131
         * starttime : 1213123131
         * endtime : 1993123131
         * status : 0
         * youhui : 100.00
         */

        private String id;
        private String cid;
        private String mid;
        private String gettime;
        private String dotime;
        private String starttime;
        private String endtime;
        private String status;
        private String youhui;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getGettime() {
            return gettime;
        }

        public void setGettime(String gettime) {
            this.gettime = gettime;
        }

        public String getDotime() {
            return dotime;
        }

        public void setDotime(String dotime) {
            this.dotime = dotime;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getYouhui() {
            return youhui;
        }

        public void setYouhui(String youhui) {
            this.youhui = youhui;
        }
    }
}

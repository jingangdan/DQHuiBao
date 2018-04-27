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
     * data : [{"id":"1","cid":"1","mid":"21","gettime":"1524790568","dotime":"0","starttime":"1524790568","endtime":"1525881600","status":"0","youhui":10,"title":"满20立减10"}]
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
         * gettime : 1524790568
         * dotime : 0
         * starttime : 1524790568
         * endtime : 1525881600
         * status : 0
         * youhui : 10
         * title : 满20立减10
         */

        private String id;
        private String cid;
        private String mid;
        private String gettime;
        private String dotime;
        private String starttime;
        private String endtime;
        private String status;
        private int youhui;
        private String title;

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

        public int getYouhui() {
            return youhui;
        }

        public void setYouhui(int youhui) {
            this.youhui = youhui;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

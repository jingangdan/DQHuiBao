package com.dq.huibao.bean.coupons;

import java.util.List;

/**
 * 我的优惠券
 * Created by d on 2018/4/25.
 */

public class MyCouponsB {
    /**
     * status : 1
     * data : [{"title":"满888七折优惠...","thumb":"/attachment/admin//20180425/1524634781_1543114471.jpg","deduct":"0.00","discount":"7.00","id":"7","cid":"3","mid":"21","gettime":"1354678900","dotime":null,"starttime":"1354678900","endtime":"1354678900","status":"1"},{"title":"满888七折优惠...","thumb":"/attachment/admin//20180425/1524634781_1543114471.jpg","deduct":"0.00","discount":"7.00","id":"6","cid":"3","mid":"21","gettime":null,"dotime":null,"starttime":"1354678900","endtime":"1354678900","status":"1"}]
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
         * title : 满888七折优惠...
         * thumb : /attachment/admin//20180425/1524634781_1543114471.jpg
         * deduct : 0.00
         * discount : 7.00
         * id : 7
         * cid : 3
         * mid : 21
         * gettime : 1354678900
         * dotime : null
         * starttime : 1354678900
         * endtime : 1354678900
         * status : 1
         */

        private String title;
        private String thumb;
        private String deduct;
        private String discount;
        private String id;
        private String cid;
        private String mid;
        private String gettime;
        private String dotime;
        private String starttime;
        private String endtime;
        private String status;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getDeduct() {
            return deduct;
        }

        public void setDeduct(String deduct) {
            this.deduct = deduct;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

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
    }
}

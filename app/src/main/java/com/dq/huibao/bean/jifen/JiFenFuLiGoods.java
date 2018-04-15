package com.dq.huibao.bean.jifen;

import java.util.List;

/**
 * 积分福利--积分可兑换商品
 * Created by d on 2018/4/15.
 */

public class JiFenFuLiGoods {
    /**
     * status : 1
     * data : [{"id":"1","goodsid":"25737","goodsname":"蜀道香 麻辣豆腐干250g四川成都特产 辣味零食好吃的小吃香辣即食","thumb":"/attachment/images/sz_yi/1604/2017/12/e75H556mwHW6SQ6ajQzSW759HxX9Z5.jpg","expprice":"100.00","price":"5.00","score":"1","allcount":"1","salecount":"0","lastcount":1},{"id":"2","goodsid":"25523","goodsname":"泰国原装进口虾条 卡乐美虾味条  大罐装 110g 12罐/箱","thumb":"/attachment/images/sz_yi/1604/2017/12/IhPH666W6G607NnP6XPXph9rPPXhWy.jpg","expprice":"100.00","price":"2.00","score":"1","allcount":"1","salecount":"0","lastcount":1}]
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
         * goodsid : 25737
         * goodsname : 蜀道香 麻辣豆腐干250g四川成都特产 辣味零食好吃的小吃香辣即食
         * thumb : /attachment/images/sz_yi/1604/2017/12/e75H556mwHW6SQ6ajQzSW759HxX9Z5.jpg
         * expprice : 100.00
         * price : 5.00
         * score : 1
         * allcount : 1
         * salecount : 0
         * lastcount : 1
         */

        private String id;
        private String goodsid;
        private String goodsname;
        private String thumb;
        private String expprice;
        private String price;
        private String score;
        private String allcount;
        private String salecount;
        private int lastcount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(String goodsid) {
            this.goodsid = goodsid;
        }

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getExpprice() {
            return expprice;
        }

        public void setExpprice(String expprice) {
            this.expprice = expprice;
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

        public String getAllcount() {
            return allcount;
        }

        public void setAllcount(String allcount) {
            this.allcount = allcount;
        }

        public String getSalecount() {
            return salecount;
        }

        public void setSalecount(String salecount) {
            this.salecount = salecount;
        }

        public int getLastcount() {
            return lastcount;
        }

        public void setLastcount(int lastcount) {
            this.lastcount = lastcount;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", goodsid='" + goodsid + '\'' +
                    ", goodsname='" + goodsname + '\'' +
                    ", thumb='" + thumb + '\'' +
                    ", expprice='" + expprice + '\'' +
                    ", price='" + price + '\'' +
                    ", score='" + score + '\'' +
                    ", allcount='" + allcount + '\'' +
                    ", salecount='" + salecount + '\'' +
                    ", lastcount=" + lastcount +
                    '}';
        }
    }
}

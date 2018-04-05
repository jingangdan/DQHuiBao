package com.dq.huibao.bean.xstore;

/**
 * 小店信息
 * Created by d on 2018/4/2.
 */

public class XStoreInfo {
    /**
     * status : 1
     * data : {"id":"3","mid":"21","shopname":null,"thumb":"attachment/user/shop/head.jpg","focusthumb":"attachment/user/shop/bg.jpg","intro":null,"goodstatus":"0"}
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
         * id : 3
         * mid : 21
         * shopname : null
         * thumb : attachment/user/shop/head.jpg
         * focusthumb : attachment/user/shop/bg.jpg
         * intro : null
         * goodstatus : 0
         */

        private String id;
        private String mid;
        private String shopname;
        private String thumb;
        private String focusthumb;
        private String intro;
        private String goodstatus;

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

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getFocusthumb() {
            return focusthumb;
        }

        public void setFocusthumb(String focusthumb) {
            this.focusthumb = focusthumb;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getGoodstatus() {
            return goodstatus;
        }

        public void setGoodstatus(String goodstatus) {
            this.goodstatus = goodstatus;
        }
    }
}

package com.dq.huibao.bean.pingo;

import java.util.List;

/**
 * 拼go需要分享的内容
 * Created by d on 2018/5/10.
 */

public class PinGoShareB {
    /**
     * status : 1
     * data : {"distype":"zhe","endtime":76679,"zhe":0,"nzhe":"9.50","price":"13.80","remoney":0,"nremoney":0.69,"nowcount":"1","lastcount":4,"regname":"临沂大学城","goodslist":[{"goodsid":"6","goodsname":"儿童宝宝益智玩具7色雪花片100片积木玩具拼插拼装积木玩具","thumb":"/attachment/images/sz_yi/1604/2017/11/CsqQnXZnJl9Zt968FfsQs7j9EFsFZe.jpg","optionname":""}]}
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
         * distype : zhe
         * endtime : 76679
         * zhe : 0
         * nzhe : 9.50
         * price : 13.80
         * remoney : 0
         * nremoney : 0.69
         * nowcount : 1
         * lastcount : 4
         * regname : 临沂大学城
         * goodslist : [{"goodsid":"6","goodsname":"儿童宝宝益智玩具7色雪花片100片积木玩具拼插拼装积木玩具","thumb":"/attachment/images/sz_yi/1604/2017/11/CsqQnXZnJl9Zt968FfsQs7j9EFsFZe.jpg","optionname":""}]
         */

        private String distype;
        private int endtime;
        private int zhe;
        private String nzhe;
        private String price;
        private int remoney;
        private double nremoney;
        private String nowcount;
        private int lastcount;
        private String regname;
        private List<GoodslistBean> goodslist;

        public String getDistype() {
            return distype;
        }

        public void setDistype(String distype) {
            this.distype = distype;
        }

        public int getEndtime() {
            return endtime;
        }

        public void setEndtime(int endtime) {
            this.endtime = endtime;
        }

        public int getZhe() {
            return zhe;
        }

        public void setZhe(int zhe) {
            this.zhe = zhe;
        }

        public String getNzhe() {
            return nzhe;
        }

        public void setNzhe(String nzhe) {
            this.nzhe = nzhe;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getRemoney() {
            return remoney;
        }

        public void setRemoney(int remoney) {
            this.remoney = remoney;
        }

        public double getNremoney() {
            return nremoney;
        }

        public void setNremoney(double nremoney) {
            this.nremoney = nremoney;
        }

        public String getNowcount() {
            return nowcount;
        }

        public void setNowcount(String nowcount) {
            this.nowcount = nowcount;
        }

        public int getLastcount() {
            return lastcount;
        }

        public void setLastcount(int lastcount) {
            this.lastcount = lastcount;
        }

        public String getRegname() {
            return regname;
        }

        public void setRegname(String regname) {
            this.regname = regname;
        }

        public List<GoodslistBean> getGoodslist() {
            return goodslist;
        }

        public void setGoodslist(List<GoodslistBean> goodslist) {
            this.goodslist = goodslist;
        }

        public static class GoodslistBean {
            /**
             * goodsid : 6
             * goodsname : 儿童宝宝益智玩具7色雪花片100片积木玩具拼插拼装积木玩具
             * thumb : /attachment/images/sz_yi/1604/2017/11/CsqQnXZnJl9Zt968FfsQs7j9EFsFZe.jpg
             * optionname :
             */

            private String goodsid;
            private String goodsname;
            private String thumb;
            private String optionname;

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

            public String getOptionname() {
                return optionname;
            }

            public void setOptionname(String optionname) {
                this.optionname = optionname;
            }
        }
    }
}

package com.dq.huibao.bean.xstore;

import java.util.List;

/**
 * 小店首页展示列表商品
 * Created by d on 2018/4/3.
 */

public class XStoreGoods {
    /**
     * status : 1
     * data : {"list":[{"id":"20041","goodsname":"韩版儿童可爱扭扭花朵帽 贴标针织毛线帽尖尖帽保暖西瓜帽男女童","thumb":"/attachment/images/sz_yi/1604/2017/09/Um656nY95iZ9RSsnMYzXoy9XMRz6rn.jpg","marketprice":"14.50","unit":"件","productprice":"16.50","ishot":"0","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0"},{"id":"20042","goodsname":"冬季新款儿童围脖毛线婴儿兔耳朵宝宝披肩 可爱保暖连体针织帽子","thumb":"/attachment/images/sz_yi/1604/2017/09/aBhNBcUdJ0zopo0EQJhUNjTLqBjDBd.jpg","marketprice":"40.00","unit":"件","productprice":"45.00","ishot":"0","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0"}],"allcount":"2","isload":0}
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
         * list : [{"id":"20041","goodsname":"韩版儿童可爱扭扭花朵帽 贴标针织毛线帽尖尖帽保暖西瓜帽男女童","thumb":"/attachment/images/sz_yi/1604/2017/09/Um656nY95iZ9RSsnMYzXoy9XMRz6rn.jpg","marketprice":"14.50","unit":"件","productprice":"16.50","ishot":"0","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0"},{"id":"20042","goodsname":"冬季新款儿童围脖毛线婴儿兔耳朵宝宝披肩 可爱保暖连体针织帽子","thumb":"/attachment/images/sz_yi/1604/2017/09/aBhNBcUdJ0zopo0EQJhUNjTLqBjDBd.jpg","marketprice":"40.00","unit":"件","productprice":"45.00","ishot":"0","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0"}]
         * allcount : 2
         * isload : 0
         */

        private String allcount;
        private int isload;
        private List<ListBean> list;

        public String getAllcount() {
            return allcount;
        }

        public void setAllcount(String allcount) {
            this.allcount = allcount;
        }

        public int getIsload() {
            return isload;
        }

        public void setIsload(int isload) {
            this.isload = isload;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 20041
             * goodsname : 韩版儿童可爱扭扭花朵帽 贴标针织毛线帽尖尖帽保暖西瓜帽男女童
             * thumb : /attachment/images/sz_yi/1604/2017/09/Um656nY95iZ9RSsnMYzXoy9XMRz6rn.jpg
             * marketprice : 14.50
             * unit : 件
             * productprice : 16.50
             * ishot : 0
             * isrecommand : 0
             * isnew : 0
             * isdiscount : 0
             * issendfree : 0
             * istime : 0
             */

            private String id;
            private String goodsname;
            private String thumb;
            private String marketprice;
            private String unit;
            private String productprice;
            private String ishot;
            private String isrecommand;
            private String isnew;
            private String isdiscount;
            private String issendfree;
            private String istime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getProductprice() {
                return productprice;
            }

            public void setProductprice(String productprice) {
                this.productprice = productprice;
            }

            public String getIshot() {
                return ishot;
            }

            public void setIshot(String ishot) {
                this.ishot = ishot;
            }

            public String getIsrecommand() {
                return isrecommand;
            }

            public void setIsrecommand(String isrecommand) {
                this.isrecommand = isrecommand;
            }

            public String getIsnew() {
                return isnew;
            }

            public void setIsnew(String isnew) {
                this.isnew = isnew;
            }

            public String getIsdiscount() {
                return isdiscount;
            }

            public void setIsdiscount(String isdiscount) {
                this.isdiscount = isdiscount;
            }

            public String getIssendfree() {
                return issendfree;
            }

            public void setIssendfree(String issendfree) {
                this.issendfree = issendfree;
            }

            public String getIstime() {
                return istime;
            }

            public void setIstime(String istime) {
                this.istime = istime;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "id='" + id + '\'' +
                        ", goodsname='" + goodsname + '\'' +
                        ", thumb='" + thumb + '\'' +
                        ", marketprice='" + marketprice + '\'' +
                        ", unit='" + unit + '\'' +
                        ", productprice='" + productprice + '\'' +
                        ", ishot='" + ishot + '\'' +
                        ", isrecommand='" + isrecommand + '\'' +
                        ", isnew='" + isnew + '\'' +
                        ", isdiscount='" + isdiscount + '\'' +
                        ", issendfree='" + issendfree + '\'' +
                        ", istime='" + istime + '\'' +
                        '}';
            }
        }
    }
}

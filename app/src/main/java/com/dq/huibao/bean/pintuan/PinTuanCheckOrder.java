package com.dq.huibao.bean.pintuan;

/**
 * 拼团确认订单
 * Created by d on 2018/4/14.
 */

public class PinTuanCheckOrder {
    /**
     * status : 1
     * data : {"address":{"region":null},"tuanid":0,"goodsid":20043,"optionid":0,"count":1,"dispatch_all":10,"discount_all":0,"money_all":0,"productprice":null,"pay_money":10,"goods":{"canbuy":1,"id":20043,"goodsname":"4条装南极人男士内裤男 男平角裤纯棉质透气四角底裤衩青年短裤头","status":1,"istime":"0","minbuy":0,"maxbuy":0,"option":{"thumb":"/attachment/images/sz_yi/1604/2017/09/Rpl0V8VEjb2eE3zjbJ3EBjB3bV3RBl.jpg"},"thumb":"/attachment/images/sz_yi/1604/2017/09/Rpl0V8VEjb2eE3zjbJ3EBjB3bV3RBl.jpg","unit":"盒","stock":796,"buycount":1,"weight":0,"shopid":0,"issendfree":0,"marketprice":42.9,"discount_money":0,"discountprice":42.9,"score":42},"is_tuan":1,"pid":0}
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
         * address : {"region":null}
         * tuanid : 0
         * goodsid : 20043
         * optionid : 0
         * count : 1
         * dispatch_all : 10
         * discount_all : 0
         * money_all : 0
         * productprice : null
         * pay_money : 10
         * goods : {"canbuy":1,"id":20043,"goodsname":"4条装南极人男士内裤男 男平角裤纯棉质透气四角底裤衩青年短裤头","status":1,"istime":"0","minbuy":0,"maxbuy":0,"option":{"thumb":"/attachment/images/sz_yi/1604/2017/09/Rpl0V8VEjb2eE3zjbJ3EBjB3bV3RBl.jpg"},"thumb":"/attachment/images/sz_yi/1604/2017/09/Rpl0V8VEjb2eE3zjbJ3EBjB3bV3RBl.jpg","unit":"盒","stock":796,"buycount":1,"weight":0,"shopid":0,"issendfree":0,"marketprice":42.9,"discount_money":0,"discountprice":42.9,"score":42}
         * is_tuan : 1
         * pid : 0
         */

        private AddressBean address;
        private int tuanid;
        private int goodsid;
        private int optionid;
        private int count;
        private int dispatch_all;
        private int discount_all;
        private int money_all;
        private Object productprice;
        private int pay_money;
        private GoodsBean goods;
        private int is_tuan;
        private int pid;

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public int getTuanid() {
            return tuanid;
        }

        public void setTuanid(int tuanid) {
            this.tuanid = tuanid;
        }

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
        }

        public int getOptionid() {
            return optionid;
        }

        public void setOptionid(int optionid) {
            this.optionid = optionid;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getDispatch_all() {
            return dispatch_all;
        }

        public void setDispatch_all(int dispatch_all) {
            this.dispatch_all = dispatch_all;
        }

        public int getDiscount_all() {
            return discount_all;
        }

        public void setDiscount_all(int discount_all) {
            this.discount_all = discount_all;
        }

        public int getMoney_all() {
            return money_all;
        }

        public void setMoney_all(int money_all) {
            this.money_all = money_all;
        }

        public Object getProductprice() {
            return productprice;
        }

        public void setProductprice(Object productprice) {
            this.productprice = productprice;
        }

        public int getPay_money() {
            return pay_money;
        }

        public void setPay_money(int pay_money) {
            this.pay_money = pay_money;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public int getIs_tuan() {
            return is_tuan;
        }

        public void setIs_tuan(int is_tuan) {
            this.is_tuan = is_tuan;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public static class AddressBean {
            /**
             * region : null
             */

            private Object region;

            public Object getRegion() {
                return region;
            }

            public void setRegion(Object region) {
                this.region = region;
            }
        }

        public static class GoodsBean {
            /**
             * canbuy : 1
             * id : 20043
             * goodsname : 4条装南极人男士内裤男 男平角裤纯棉质透气四角底裤衩青年短裤头
             * status : 1
             * istime : 0
             * minbuy : 0
             * maxbuy : 0
             * option : {"thumb":"/attachment/images/sz_yi/1604/2017/09/Rpl0V8VEjb2eE3zjbJ3EBjB3bV3RBl.jpg"}
             * thumb : /attachment/images/sz_yi/1604/2017/09/Rpl0V8VEjb2eE3zjbJ3EBjB3bV3RBl.jpg
             * unit : 盒
             * stock : 796
             * buycount : 1
             * weight : 0
             * shopid : 0
             * issendfree : 0
             * marketprice : 42.9
             * discount_money : 0
             * discountprice : 42.9
             * score : 42
             */

            private int canbuy;
            private int id;
            private String goodsname;
            private int status;
            private String istime;
            private int minbuy;
            private int maxbuy;
            private OptionBean option;
            private String thumb;
            private String unit;
            private int stock;
            private int buycount;
            private int weight;
            private int shopid;
            private int issendfree;
            private double marketprice;
            private int discount_money;
            private double discountprice;
            private int score;

            public int getCanbuy() {
                return canbuy;
            }

            public void setCanbuy(int canbuy) {
                this.canbuy = canbuy;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGoodsname() {
                return goodsname;
            }

            public void setGoodsname(String goodsname) {
                this.goodsname = goodsname;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getIstime() {
                return istime;
            }

            public void setIstime(String istime) {
                this.istime = istime;
            }

            public int getMinbuy() {
                return minbuy;
            }

            public void setMinbuy(int minbuy) {
                this.minbuy = minbuy;
            }

            public int getMaxbuy() {
                return maxbuy;
            }

            public void setMaxbuy(int maxbuy) {
                this.maxbuy = maxbuy;
            }

            public OptionBean getOption() {
                return option;
            }

            public void setOption(OptionBean option) {
                this.option = option;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public int getBuycount() {
                return buycount;
            }

            public void setBuycount(int buycount) {
                this.buycount = buycount;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public int getShopid() {
                return shopid;
            }

            public void setShopid(int shopid) {
                this.shopid = shopid;
            }

            public int getIssendfree() {
                return issendfree;
            }

            public void setIssendfree(int issendfree) {
                this.issendfree = issendfree;
            }

            public double getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(double marketprice) {
                this.marketprice = marketprice;
            }

            public int getDiscount_money() {
                return discount_money;
            }

            public void setDiscount_money(int discount_money) {
                this.discount_money = discount_money;
            }

            public double getDiscountprice() {
                return discountprice;
            }

            public void setDiscountprice(double discountprice) {
                this.discountprice = discountprice;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public static class OptionBean {
                /**
                 * thumb : /attachment/images/sz_yi/1604/2017/09/Rpl0V8VEjb2eE3zjbJ3EBjB3bV3RBl.jpg
                 */

                private String thumb;

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }
            }
        }
    }
}

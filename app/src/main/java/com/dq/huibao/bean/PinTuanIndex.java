package com.dq.huibao.bean;

import java.util.List;

/**
 * 拼团首页列表
 * Created by d on 2018/4/11.
 */

public class PinTuanIndex {
    /**
     * status : 1
     * data : {"list":[{"id":"1","goodsid":"25728","tuanname":"拼团标题测试000","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","mincount":"2","discount":"0.00","tuanprice":"1.33","saletype":"0","goodsname":"久久丫甜辣薄豆干好吃的豆腐干"},{"id":"2","goodsid":"25339","tuanname":"测试信息22222222","thumb":"http://p2.qhimgs4.com/t01a567cd219e92911c.webp","mincount":"20","discount":"6.00","tuanprice":"0.00","saletype":"1","goodsname":"百草味多味花生米210g 休闲零食品特产坚果美味"},{"id":"3","goodsid":"25339","tuanname":"海澜之家","thumb":"/attachment/admin//20180404/01522823968_122523762.jpg","mincount":"10","discount":"0.00","tuanprice":"5.00","saletype":"0","goodsname":"百草味多味花生米210g 休闲零食品特产坚果美味"},{"id":"4","goodsid":"25739","tuanname":"4","thumb":"/attachment/admin//20180404/01522826342_1836841441.jpg","mincount":"12","discount":"0.00","tuanprice":"4.00","saletype":"0","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃"},{"id":"5","goodsid":"25739","tuanname":"5","thumb":"/attachment/admin//20180404/01522823968_122523762.jpg","mincount":null,"discount":null,"tuanprice":null,"saletype":"0","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃"},{"id":"6","goodsid":"25739","tuanname":"6","thumb":"/attachment/admin//20180404/01522823968_122523762.jpg","mincount":null,"discount":null,"tuanprice":null,"saletype":"0","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃"},{"id":"7","goodsid":"25739","tuanname":"7","thumb":"/attachment/admin//20180404/01522823968_122523762.jpg","mincount":null,"discount":null,"tuanprice":null,"saletype":"0","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃"},{"id":"8","goodsid":"25739","tuanname":"8","thumb":"/attachment/admin//20180404/01522823968_122523762.jpg","mincount":null,"discount":null,"tuanprice":null,"saletype":"0","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃"},{"id":"9","goodsid":"25739","tuanname":"9","thumb":"/attachment/admin//20180404/01522823968_122523762.jpg","mincount":null,"discount":null,"tuanprice":null,"saletype":"0","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃"},{"id":"10","goodsid":"25739","tuanname":"10","thumb":"/attachment/admin//20180404/01522823968_122523762.jpg","mincount":null,"discount":null,"tuanprice":null,"saletype":"0","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃"}],"listcount":"11"}
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
         * list : [{"id":"1","goodsid":"25728","tuanname":"拼团标题测试000","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","mincount":"2","discount":"0.00","tuanprice":"1.33","saletype":"0","goodsname":"久久丫甜辣薄豆干好吃的豆腐干"},{"id":"2","goodsid":"25339","tuanname":"测试信息22222222","thumb":"http://p2.qhimgs4.com/t01a567cd219e92911c.webp","mincount":"20","discount":"6.00","tuanprice":"0.00","saletype":"1","goodsname":"百草味多味花生米210g 休闲零食品特产坚果美味"},{"id":"3","goodsid":"25339","tuanname":"海澜之家","thumb":"/attachment/admin//20180404/01522823968_122523762.jpg","mincount":"10","discount":"0.00","tuanprice":"5.00","saletype":"0","goodsname":"百草味多味花生米210g 休闲零食品特产坚果美味"},{"id":"4","goodsid":"25739","tuanname":"4","thumb":"/attachment/admin//20180404/01522826342_1836841441.jpg","mincount":"12","discount":"0.00","tuanprice":"4.00","saletype":"0","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃"},{"id":"5","goodsid":"25739","tuanname":"5","thumb":"/attachment/admin//20180404/01522823968_122523762.jpg","mincount":null,"discount":null,"tuanprice":null,"saletype":"0","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃"},{"id":"6","goodsid":"25739","tuanname":"6","thumb":"/attachment/admin//20180404/01522823968_122523762.jpg","mincount":null,"discount":null,"tuanprice":null,"saletype":"0","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃"},{"id":"7","goodsid":"25739","tuanname":"7","thumb":"/attachment/admin//20180404/01522823968_122523762.jpg","mincount":null,"discount":null,"tuanprice":null,"saletype":"0","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃"},{"id":"8","goodsid":"25739","tuanname":"8","thumb":"/attachment/admin//20180404/01522823968_122523762.jpg","mincount":null,"discount":null,"tuanprice":null,"saletype":"0","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃"},{"id":"9","goodsid":"25739","tuanname":"9","thumb":"/attachment/admin//20180404/01522823968_122523762.jpg","mincount":null,"discount":null,"tuanprice":null,"saletype":"0","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃"},{"id":"10","goodsid":"25739","tuanname":"10","thumb":"/attachment/admin//20180404/01522823968_122523762.jpg","mincount":null,"discount":null,"tuanprice":null,"saletype":"0","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃"}]
         * listcount : 11
         */

        private String listcount;
        private List<ListBean> list;

        public String getListcount() {
            return listcount;
        }

        public void setListcount(String listcount) {
            this.listcount = listcount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * goodsid : 25728
             * tuanname : 拼团标题测试000
             * thumb : http://p2.qhimgs4.com/t014a167f02825c4d94.webp
             * mincount : 2
             * discount : 0.00
             * tuanprice : 1.33
             * saletype : 0
             * goodsname : 久久丫甜辣薄豆干好吃的豆腐干
             */

            private String id;
            private String goodsid;
            private String tuanname;
            private String thumb;
            private String mincount;
            private String discount;
            private String tuanprice;
            private String saletype;
            private String goodsname;

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

            public String getTuanname() {
                return tuanname;
            }

            public void setTuanname(String tuanname) {
                this.tuanname = tuanname;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getMincount() {
                return mincount;
            }

            public void setMincount(String mincount) {
                this.mincount = mincount;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getTuanprice() {
                return tuanprice;
            }

            public void setTuanprice(String tuanprice) {
                this.tuanprice = tuanprice;
            }

            public String getSaletype() {
                return saletype;
            }

            public void setSaletype(String saletype) {
                this.saletype = saletype;
            }

            public String getGoodsname() {
                return goodsname;
            }

            public void setGoodsname(String goodsname) {
                this.goodsname = goodsname;
            }
        }
    }
}

package com.dq.huibao.bean.homepage;

import java.util.List;

/**
 * 首页下边更多商品
 * Created by d on 2018/4/11.
 */

public class IndexMoreGoods {
    /**
     * status : 1
     * data : {"list":[{"id":"25743","goodsname":"马奇新新马来西亚进口LEXUS力士系列巧克力味涂层夹心饼干200g","thumb":"/attachment/images/sz_yi/1604/2017/12/p4Xt84g4r3S88Z3yx49c0xxy7cxGD8.jpg","productprice":"19.90","marketprice":"12.80"},{"id":"25741","goodsname":"韩国进口 可瑞安 可拉奥crown巧克力榛子威化饼干","thumb":"/attachment/images/sz_yi/1604/2017/12/XLR1kOP19IHKzkooi9YFv9i494W1kI.jpg","productprice":"29.00","marketprice":"14.80"},{"id":"25740","goodsname":"亿滋奥利奥缤纷双果味夹心饼干97g休闲零食","thumb":"/attachment/images/sz_yi/1604/2017/12/EDA7wsZ2D72s62dC7s2d28WW2N7VIC.jpg","productprice":"8.00","marketprice":"6.00"},{"id":"25739","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃","thumb":"/attachment/images/sz_yi/1604/2017/12/GbF0t9I4ByT0fT30ymTjiYbNZyff4Y.jpg","productprice":"29.50","marketprice":"10.80"},{"id":"25738","goodsname":"红灯记肉松蚕豆兰花豆250g独立小包炒货零食品年货","thumb":"/attachment/images/sz_yi/1604/2017/12/c44oWBP2484Wipa5BwNdeMi22iECNw.png","productprice":"9.90","marketprice":"7.80"},{"id":"25737","goodsname":"蜀道香 麻辣豆腐干250g四川成都特产 辣味零食好吃的小吃香辣即食","thumb":"/attachment/images/sz_yi/1604/2017/12/e75H556mwHW6SQ6ajQzSW759HxX9Z5.jpg","productprice":"19.00","marketprice":"12.80"},{"id":"25736","goodsname":"蜀道香麻辣鸭脖118g 卤味熟食 四川麻辣味零食好吃的小吃","thumb":"/attachment/images/sz_yi/1604/2017/12/JcEy5QoqByhce5IyvIchEeZCQiOHcv.jpg","productprice":"19.90","marketprice":"14.80"},{"id":"25735","goodsname":"蜀道香麻辣金针菇 独立包装四川泡菜零食220g","thumb":"/attachment/images/sz_yi/1604/2017/12/hKg7AGdcq661ZUVwL77567KTFddqin.jpg","productprice":"15.80","marketprice":"13.80"},{"id":"25734","goodsname":"蜀道香麻辣笋尖80g 四川风味嫩竹笋麻辣味休闲零食小吃","thumb":"/attachment/images/sz_yi/1604/2017/12/aNzNRIUNuNoQubUOEhQ7MbnaF2HoIU.jpg","productprice":"12.00","marketprice":"8.00"},{"id":"25732","goodsname":"蜀道香麻辣章鱼足片88g 四川特产休闲","thumb":"/attachment/images/sz_yi/1604/2017/12/OXB2BXIiv0sJOQbS7URV3IzOQsM1X3.jpg","productprice":"15.80","marketprice":"10.80"}],"isload":1}
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
         * list : [{"id":"25743","goodsname":"马奇新新马来西亚进口LEXUS力士系列巧克力味涂层夹心饼干200g","thumb":"/attachment/images/sz_yi/1604/2017/12/p4Xt84g4r3S88Z3yx49c0xxy7cxGD8.jpg","productprice":"19.90","marketprice":"12.80"},{"id":"25741","goodsname":"韩国进口 可瑞安 可拉奥crown巧克力榛子威化饼干","thumb":"/attachment/images/sz_yi/1604/2017/12/XLR1kOP19IHKzkooi9YFv9i494W1kI.jpg","productprice":"29.00","marketprice":"14.80"},{"id":"25740","goodsname":"亿滋奥利奥缤纷双果味夹心饼干97g休闲零食","thumb":"/attachment/images/sz_yi/1604/2017/12/EDA7wsZ2D72s62dC7s2d28WW2N7VIC.jpg","productprice":"8.00","marketprice":"6.00"},{"id":"25739","goodsname":"卫龙香辣卤藕丁180g麻辣莲藕片零食美食素小吃","thumb":"/attachment/images/sz_yi/1604/2017/12/GbF0t9I4ByT0fT30ymTjiYbNZyff4Y.jpg","productprice":"29.50","marketprice":"10.80"},{"id":"25738","goodsname":"红灯记肉松蚕豆兰花豆250g独立小包炒货零食品年货","thumb":"/attachment/images/sz_yi/1604/2017/12/c44oWBP2484Wipa5BwNdeMi22iECNw.png","productprice":"9.90","marketprice":"7.80"},{"id":"25737","goodsname":"蜀道香 麻辣豆腐干250g四川成都特产 辣味零食好吃的小吃香辣即食","thumb":"/attachment/images/sz_yi/1604/2017/12/e75H556mwHW6SQ6ajQzSW759HxX9Z5.jpg","productprice":"19.00","marketprice":"12.80"},{"id":"25736","goodsname":"蜀道香麻辣鸭脖118g 卤味熟食 四川麻辣味零食好吃的小吃","thumb":"/attachment/images/sz_yi/1604/2017/12/JcEy5QoqByhce5IyvIchEeZCQiOHcv.jpg","productprice":"19.90","marketprice":"14.80"},{"id":"25735","goodsname":"蜀道香麻辣金针菇 独立包装四川泡菜零食220g","thumb":"/attachment/images/sz_yi/1604/2017/12/hKg7AGdcq661ZUVwL77567KTFddqin.jpg","productprice":"15.80","marketprice":"13.80"},{"id":"25734","goodsname":"蜀道香麻辣笋尖80g 四川风味嫩竹笋麻辣味休闲零食小吃","thumb":"/attachment/images/sz_yi/1604/2017/12/aNzNRIUNuNoQubUOEhQ7MbnaF2HoIU.jpg","productprice":"12.00","marketprice":"8.00"},{"id":"25732","goodsname":"蜀道香麻辣章鱼足片88g 四川特产休闲","thumb":"/attachment/images/sz_yi/1604/2017/12/OXB2BXIiv0sJOQbS7URV3IzOQsM1X3.jpg","productprice":"15.80","marketprice":"10.80"}]
         * isload : 1
         */

        private int isload;
        private List<ListBean> list;

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
             * id : 25743
             * goodsname : 马奇新新马来西亚进口LEXUS力士系列巧克力味涂层夹心饼干200g
             * thumb : /attachment/images/sz_yi/1604/2017/12/p4Xt84g4r3S88Z3yx49c0xxy7cxGD8.jpg
             * productprice : 19.90
             * marketprice : 12.80
             */

            private String id;
            private String goodsname;
            private String thumb;
            private String productprice;
            private String marketprice;

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

            public String getProductprice() {
                return productprice;
            }

            public void setProductprice(String productprice) {
                this.productprice = productprice;
            }

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }
        }
    }
}

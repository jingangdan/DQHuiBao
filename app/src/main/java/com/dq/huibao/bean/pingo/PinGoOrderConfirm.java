package com.dq.huibao.bean.pingo;

import java.util.List;

/**
 * 购物车确认订单-拼go
 * Created by d on 2018/4/18.
 */

public class PinGoOrderConfirm {

    /**
     * status : 1
     * data : {"allcount":7,"expprice":0,"allprice":108.6,"list":[{"id":"136","uid":"21","goodsid":"4","count":"6","createtime":null,"shopid":"0","marketprice":"15.80","optionid":"232717","type":"1","goodsname":"鼠大厨开心果108gX1袋 休闲零食坚果炒货干果","distype":"打折","thumb":"/attachment/images/sz_yi/1604/2017/12/j5ipzl926p56pPVLto24Z4PLmmTkP6.jpg","optiontitle":"原味","allprice":94.8},{"id":"143","uid":"21","goodsid":"6","count":"1","createtime":null,"shopid":"0","marketprice":"13.80","optionid":"0","type":"1","goodsname":"儿童宝宝益智玩具7色雪花片100片积木玩具拼插拼装积木玩具","distype":"打折","thumb":"/attachment/images/sz_yi/1604/2017/11/CsqQnXZnJl9Zt968FfsQs7j9EFsFZe.jpg","optiontitle":"","allprice":13.8}]}
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
         * allcount : 7
         * expprice : 0
         * allprice : 108.6
         * list : [{"id":"136","uid":"21","goodsid":"4","count":"6","createtime":null,"shopid":"0","marketprice":"15.80","optionid":"232717","type":"1","goodsname":"鼠大厨开心果108gX1袋 休闲零食坚果炒货干果","distype":"打折","thumb":"/attachment/images/sz_yi/1604/2017/12/j5ipzl926p56pPVLto24Z4PLmmTkP6.jpg","optiontitle":"原味","allprice":94.8},{"id":"143","uid":"21","goodsid":"6","count":"1","createtime":null,"shopid":"0","marketprice":"13.80","optionid":"0","type":"1","goodsname":"儿童宝宝益智玩具7色雪花片100片积木玩具拼插拼装积木玩具","distype":"打折","thumb":"/attachment/images/sz_yi/1604/2017/11/CsqQnXZnJl9Zt968FfsQs7j9EFsFZe.jpg","optiontitle":"","allprice":13.8}]
         */

        private int allcount;
        private int expprice;
        private double allprice;

        public String getCommet() {
            return commet;
        }

        public void setCommet(String commet) {
            this.commet = commet;
        }

        private String commet;
        private List<ListBean> list;

        public int getAllcount() {
            return allcount;
        }

        public void setAllcount(int allcount) {
            this.allcount = allcount;
        }

        public int getExpprice() {
            return expprice;
        }

        public void setExpprice(int expprice) {
            this.expprice = expprice;
        }

        public double getAllprice() {
            return allprice;
        }

        public void setAllprice(double allprice) {
            this.allprice = allprice;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 136
             * uid : 21
             * goodsid : 4
             * count : 6
             * createtime : null
             * shopid : 0
             * marketprice : 15.80
             * optionid : 232717
             * type : 1
             * goodsname : 鼠大厨开心果108gX1袋 休闲零食坚果炒货干果
             * distype : 打折
             * thumb : /attachment/images/sz_yi/1604/2017/12/j5ipzl926p56pPVLto24Z4PLmmTkP6.jpg
             * optiontitle : 原味
             * allprice : 94.8
             */

            private String id;
            private String uid;
            private String goodsid;
            private String count;
            private Object createtime;
            private String shopid;
            private String marketprice;
            private String optionid;
            private String type;
            private String goodsname;
            private String distype;
            private String thumb;
            private String optiontitle;
            private double allprice;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getGoodsid() {
                return goodsid;
            }

            public void setGoodsid(String goodsid) {
                this.goodsid = goodsid;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public Object getCreatetime() {
                return createtime;
            }

            public void setCreatetime(Object createtime) {
                this.createtime = createtime;
            }

            public String getShopid() {
                return shopid;
            }

            public void setShopid(String shopid) {
                this.shopid = shopid;
            }

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }

            public String getOptionid() {
                return optionid;
            }

            public void setOptionid(String optionid) {
                this.optionid = optionid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getGoodsname() {
                return goodsname;
            }

            public void setGoodsname(String goodsname) {
                this.goodsname = goodsname;
            }

            public String getDistype() {
                return distype;
            }

            public void setDistype(String distype) {
                this.distype = distype;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getOptiontitle() {
                return optiontitle;
            }

            public void setOptiontitle(String optiontitle) {
                this.optiontitle = optiontitle;
            }

            public double getAllprice() {
                return allprice;
            }

            public void setAllprice(double allprice) {
                this.allprice = allprice;
            }
        }
    }
}

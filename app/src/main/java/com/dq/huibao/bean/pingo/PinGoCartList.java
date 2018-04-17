package com.dq.huibao.bean.pingo;

import java.util.List;

/**
 * 拼go购物车列表
 * Created by d on 2018/4/17.
 */

public class PinGoCartList {

    /**
     * status : 1
     * data : {"list":[{"id":"132","uid":"21","goodsid":"5","count":"7","createtime":null,"shopid":"0","marketprice":"162.00","optionid":"223019","type":"1"},{"id":"134","uid":"21","goodsid":"3","count":"2","createtime":null,"shopid":"0","marketprice":"12.80","optionid":"223019","type":"1"},{"id":"135","uid":"21","goodsid":"1","count":"8","createtime":null,"shopid":"0","marketprice":"1.50","optionid":"0","type":"1"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 132
             * uid : 21
             * goodsid : 5
             * count : 7
             * createtime : null
             * shopid : 0
             * marketprice : 162.00
             * optionid : 223019
             * type : 1
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
        }
    }
}

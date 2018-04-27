package com.dq.huibao.bean.pingo;

import java.util.List;

/**
 * 拼go首页更多商品
 *
 *   "status":"2",                  status:0-非周五(非当前天);1-未开始;2-进行中;3-已结束;
 *   "etstr":"1524816000",          结束时间戳
 *   "ststr":"",                    开始时间戳
 *   "timestr":"15:50-16:00",       时间提示
 *
 * Created by d on 2018/4/17.
 */

public class PinGoIndexMoreGoods {
    /**
     * status : 1
     * data : {"status":"2","etstr":"1524816000","ststr":"","timestr":"15:50-16:00","isload":0,"list":[{"id":"1","goodsid":"25728","goodsname":"久久丫甜辣薄豆干好吃的豆腐干","typeid":"1","distype":"jian","msmaxcount":"20","msonecount":"2","salecount":"13","thumb":"/attachment/images/sz_yi/1604/2017/12/F5iAUklELCEcrH755EA8klHikLI5e8.jpg","productprice":"3.00","marketprice":"0.01","stock":"147"},{"id":"3","goodsid":"25743","goodsname":"马奇新新马来西亚进口LEXUS力士系列巧克力味涂层夹心饼干200g","typeid":"1","distype":"jian","msmaxcount":"10","msonecount":"1","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/12/p4Xt84g4r3S88Z3yx49c0xxy7cxGD8.jpg","productprice":"19.90","marketprice":"12.80","stock":"8054"},{"id":"13","goodsid":"25588","goodsname":"莎布蕾武士曲奇 爆浆曲奇 网红零食小吃 软心蔓越莓饼干128g*3袋","typeid":"0","distype":"jian","msmaxcount":"100","msonecount":"1","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/12/w5j455s7F26U82W55lS7U6j5s5jUW8.jpg","productprice":"0.00","marketprice":"64.00","stock":"501"}]}
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
         * status : 2
         * etstr : 1524816000
         * ststr :
         * timestr : 15:50-16:00
         * isload : 0
         * list : [{"id":"1","goodsid":"25728","goodsname":"久久丫甜辣薄豆干好吃的豆腐干","typeid":"1","distype":"jian","msmaxcount":"20","msonecount":"2","salecount":"13","thumb":"/attachment/images/sz_yi/1604/2017/12/F5iAUklELCEcrH755EA8klHikLI5e8.jpg","productprice":"3.00","marketprice":"0.01","stock":"147"},{"id":"3","goodsid":"25743","goodsname":"马奇新新马来西亚进口LEXUS力士系列巧克力味涂层夹心饼干200g","typeid":"1","distype":"jian","msmaxcount":"10","msonecount":"1","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/12/p4Xt84g4r3S88Z3yx49c0xxy7cxGD8.jpg","productprice":"19.90","marketprice":"12.80","stock":"8054"},{"id":"13","goodsid":"25588","goodsname":"莎布蕾武士曲奇 爆浆曲奇 网红零食小吃 软心蔓越莓饼干128g*3袋","typeid":"0","distype":"jian","msmaxcount":"100","msonecount":"1","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/12/w5j455s7F26U82W55lS7U6j5s5jUW8.jpg","productprice":"0.00","marketprice":"64.00","stock":"501"}]
         */

        private String status;
        private String etstr;
        private String ststr;
        private String timestr;
        private int isload;
        private List<ListBean> list;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getEtstr() {
            return etstr;
        }

        public void setEtstr(String etstr) {
            this.etstr = etstr;
        }

        public String getStstr() {
            return ststr;
        }

        public void setStstr(String ststr) {
            this.ststr = ststr;
        }

        public String getTimestr() {
            return timestr;
        }

        public void setTimestr(String timestr) {
            this.timestr = timestr;
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
             * id : 1
             * goodsid : 25728
             * goodsname : 久久丫甜辣薄豆干好吃的豆腐干
             * typeid : 1
             * distype : jian
             * msmaxcount : 20
             * msonecount : 2
             * salecount : 13
             * thumb : /attachment/images/sz_yi/1604/2017/12/F5iAUklELCEcrH755EA8klHikLI5e8.jpg
             * productprice : 3.00
             * marketprice : 0.01
             * stock : 147
             */

            private String id;
            private String goodsid;
            private String goodsname;
            private String typeid;
            private String distype;
            private String msmaxcount;
            private String msonecount;
            private String salecount;
            private String thumb;
            private String productprice;
            private String marketprice;
            private String stock;

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

            public String getTypeid() {
                return typeid;
            }

            public void setTypeid(String typeid) {
                this.typeid = typeid;
            }

            public String getDistype() {
                return distype;
            }

            public void setDistype(String distype) {
                this.distype = distype;
            }

            public String getMsmaxcount() {
                return msmaxcount;
            }

            public void setMsmaxcount(String msmaxcount) {
                this.msmaxcount = msmaxcount;
            }

            public String getMsonecount() {
                return msonecount;
            }

            public void setMsonecount(String msonecount) {
                this.msonecount = msonecount;
            }

            public String getSalecount() {
                return salecount;
            }

            public void setSalecount(String salecount) {
                this.salecount = salecount;
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

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }
        }
    }
}

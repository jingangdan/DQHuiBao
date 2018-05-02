package com.dq.huibao.bean.pingo;

import java.util.List;

/**
 * 拼go log-详情
 * Created by d on 2018/5/2.
 */

public class PinGoLogInfoB {
    /**
     * status : 1
     * data : {"id":"9","tuanid":"6","regid":"2","price":"7.00","paystatus":"0","remoney":"0","restatus":"0","addtime":"1524473821","paytype":"1","distype":"jian","tips":"","goodslist":[{"id":"9","logid":"9","goodsid":"2","goodsname":"奶油葵花籽 美味奶油瓜子坚果炒货休闲零食","thumb":"","buycount":"1","price":"7.00","optionid":"233631","optionname":"新货奶油瓜子"}],"regname":"山东医专","tuan":{"maxcount":"5","nowcount":"2","jian":2.5,"zhe":0,"price":0,"status":"1"}}
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
         * id : 9
         * tuanid : 6
         * regid : 2
         * price : 7.00
         * paystatus : 0
         * remoney : 0
         * restatus : 0
         * addtime : 1524473821
         * paytype : 1
         * distype : jian
         * tips :
         * goodslist : [{"id":"9","logid":"9","goodsid":"2","goodsname":"奶油葵花籽 美味奶油瓜子坚果炒货休闲零食","thumb":"","buycount":"1","price":"7.00","optionid":"233631","optionname":"新货奶油瓜子"}]
         * regname : 山东医专
         * tuan : {"maxcount":"5","nowcount":"2","jian":2.5,"zhe":0,"price":0,"status":"1"}
         */

        private String id;
        private String tuanid;
        private String regid;
        private String price;
        private String paystatus;
        private String remoney;
        private String restatus;
        private String addtime;
        private String paytype;
        private String distype;
        private String tips;
        private String regname;
        private TuanBean tuan;
        private List<GoodslistBean> goodslist;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTuanid() {
            return tuanid;
        }

        public void setTuanid(String tuanid) {
            this.tuanid = tuanid;
        }

        public String getRegid() {
            return regid;
        }

        public void setRegid(String regid) {
            this.regid = regid;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPaystatus() {
            return paystatus;
        }

        public void setPaystatus(String paystatus) {
            this.paystatus = paystatus;
        }

        public String getRemoney() {
            return remoney;
        }

        public void setRemoney(String remoney) {
            this.remoney = remoney;
        }

        public String getRestatus() {
            return restatus;
        }

        public void setRestatus(String restatus) {
            this.restatus = restatus;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getDistype() {
            return distype;
        }

        public void setDistype(String distype) {
            this.distype = distype;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public String getRegname() {
            return regname;
        }

        public void setRegname(String regname) {
            this.regname = regname;
        }

        public TuanBean getTuan() {
            return tuan;
        }

        public void setTuan(TuanBean tuan) {
            this.tuan = tuan;
        }

        public List<GoodslistBean> getGoodslist() {
            return goodslist;
        }

        public void setGoodslist(List<GoodslistBean> goodslist) {
            this.goodslist = goodslist;
        }

        public static class TuanBean {
            /**
             * maxcount : 5
             * nowcount : 2
             * jian : 2.5
             * zhe : 0
             * price : 0
             * status : 1
             */

            private String maxcount;
            private String nowcount;
            private double jian;
            private int zhe;
            private int price;
            private String status;

            public String getMaxcount() {
                return maxcount;
            }

            public void setMaxcount(String maxcount) {
                this.maxcount = maxcount;
            }

            public String getNowcount() {
                return nowcount;
            }

            public void setNowcount(String nowcount) {
                this.nowcount = nowcount;
            }

            public double getJian() {
                return jian;
            }

            public void setJian(double jian) {
                this.jian = jian;
            }

            public int getZhe() {
                return zhe;
            }

            public void setZhe(int zhe) {
                this.zhe = zhe;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }

        public static class GoodslistBean {
            /**
             * id : 9
             * logid : 9
             * goodsid : 2
             * goodsname : 奶油葵花籽 美味奶油瓜子坚果炒货休闲零食
             * thumb :
             * buycount : 1
             * price : 7.00
             * optionid : 233631
             * optionname : 新货奶油瓜子
             */

            private String id;
            private String logid;
            private String goodsid;
            private String goodsname;
            private String thumb;
            private String buycount;
            private String price;
            private String optionid;
            private String optionname;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLogid() {
                return logid;
            }

            public void setLogid(String logid) {
                this.logid = logid;
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

            public String getBuycount() {
                return buycount;
            }

            public void setBuycount(String buycount) {
                this.buycount = buycount;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getOptionid() {
                return optionid;
            }

            public void setOptionid(String optionid) {
                this.optionid = optionid;
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

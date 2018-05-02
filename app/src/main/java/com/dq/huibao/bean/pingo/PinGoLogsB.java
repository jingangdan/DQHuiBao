package com.dq.huibao.bean.pingo;

import java.util.List;

/**
 * 拼go记录
 * Created by d on 2018/5/2.
 * tuan.status:0-开团;1-满团;2-未满;3-完成结束;
 */

public class PinGoLogsB {
    /**
     * status : 1
     * data : [{"id":"1","tuanid":"1","regid":"2","price":"0.01","paystatus":"1","remoney":null,"restatus":"0","addtime":"1524361631","paytype":"2","distype":"jian","tips":"","regname":"山东医专","tuan":{"maxcount":"5","nowcount":"1","status":"2"}},{"id":"2","tuanid":"2","regid":"2","price":"1.00","paystatus":"1","remoney":null,"restatus":"0","addtime":"1524361684","paytype":"2","distype":"jian","tips":"","regname":"山东医专","tuan":{"maxcount":"5","nowcount":"1","status":"2"}},{"id":"3","tuanid":"3","regid":"1","price":"0.01","paystatus":"1","remoney":null,"restatus":"0","addtime":"1524362388","paytype":"2","distype":"jian","tips":"","regname":"临沂大学城","tuan":{"maxcount":"5","nowcount":"1","status":"2"}},{"id":"8","tuanid":"0","regid":"1","price":"0.01","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524473806","paytype":"2","distype":"jian","tips":"","regname":"临沂大学城","tuan":{"maxcount":0,"nowcount":0,"status":0}},{"id":"9","tuanid":"6","regid":"2","price":"7.00","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524473821","paytype":"1","distype":"jian","tips":"","regname":"山东医专","tuan":{"maxcount":"5","nowcount":"2","status":"1"}},{"id":"10","tuanid":"4","regid":"1","price":"45.00","paystatus":"1","remoney":null,"restatus":"0","addtime":"1524474193","paytype":"2","distype":"jian","tips":"","regname":"临沂大学城","tuan":{"maxcount":"5","nowcount":"2","status":"2"}},{"id":"13","tuanid":"0","regid":"1","price":"0.01","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524622042","paytype":"2","distype":"jian","tips":"","regname":"临沂大学城","tuan":{"maxcount":0,"nowcount":0,"status":0}},{"id":"14","tuanid":"0","regid":"1","price":"52.04","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524622457","paytype":"2","distype":"jian","tips":"","regname":"临沂大学城","tuan":{"maxcount":0,"nowcount":0,"status":0}},{"id":"16","tuanid":"10","regid":"1","price":"0.01","paystatus":"1","remoney":null,"restatus":"0","addtime":"1524791692","paytype":"2","distype":"jian","tips":"","regname":"临沂大学城","tuan":{"maxcount":"5","nowcount":"1","status":"2"}},{"id":"17","tuanid":"11","regid":"1","price":"7.00","paystatus":"1","remoney":null,"restatus":"0","addtime":"1524880338","paytype":"2","distype":"jian","tips":"","regname":"临沂大学城","tuan":{"maxcount":"5","nowcount":"1","status":"2"}}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * tuanid : 1
         * regid : 2
         * price : 0.01
         * paystatus : 1
         * remoney : null
         * restatus : 0
         * addtime : 1524361631
         * paytype : 2
         * distype : jian
         * tips :
         * regname : 山东医专
         * tuan : {"maxcount":"5","nowcount":"1","status":"2"}
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

        public static class TuanBean {
            /**
             * maxcount : 5
             * nowcount : 1
             * status : 2
             */

            private String maxcount;
            private String nowcount;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}

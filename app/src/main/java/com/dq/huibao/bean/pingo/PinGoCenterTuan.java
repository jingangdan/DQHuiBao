package com.dq.huibao.bean.pingo;

import java.util.List;

/**
 * 拼go首页中间团信息
 * Created by d on 2018/4/17.
 */

public class PinGoCenterTuan {
    /**
     * status : 1
     * data : {"endtime":1524288600,"list":[[{"id":"3","regid":"1","mid":"21","jian":"0","zhe":null,"distype":"jian","maxcount":"5","nowcount":"1","addtime":"1524229655","endtime":"1524288600","status":"0","orderprice":3,"regname":"临沂大学城","list":[{"id":"3","tuanid":"3","mid":"21","price":"3.00","paystatus":"1","remoney":null,"restatus":"0","addtime":"1524229654","regid":"1","paytype":"2","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","rname":"158****8931","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg"}]},{"id":"","regid":"","mid":"","jian":"","zhe":"","distype":"","maxcount":"5","nowcount":"0","addtime":"","endtime":"","statis":"","regname":"山东医专","list":[]}],[{"id":"","regid":"","mid":"","jian":"","zhe":"","distype":"","maxcount":"5","nowcount":"0","addtime":"","endtime":"","statis":"","regname":"临沂大学城","list":[]},{"id":"","regid":"","mid":"","jian":"","zhe":"","distype":"","maxcount":"5","nowcount":"0","addtime":"","endtime":"","statis":"","regname":"山东医专","list":[]}]]}
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
         * endtime : 1524288600
         * list : [[{"id":"3","regid":"1","mid":"21","jian":"0","zhe":null,"distype":"jian","maxcount":"5","nowcount":"1","addtime":"1524229655","endtime":"1524288600","status":"0","orderprice":3,"regname":"临沂大学城","list":[{"id":"3","tuanid":"3","mid":"21","price":"3.00","paystatus":"1","remoney":null,"restatus":"0","addtime":"1524229654","regid":"1","paytype":"2","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","rname":"158****8931","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg"}]},{"id":"","regid":"","mid":"","jian":"","zhe":"","distype":"","maxcount":"5","nowcount":"0","addtime":"","endtime":"","statis":"","regname":"山东医专","list":[]}],[{"id":"","regid":"","mid":"","jian":"","zhe":"","distype":"","maxcount":"5","nowcount":"0","addtime":"","endtime":"","statis":"","regname":"临沂大学城","list":[]},{"id":"","regid":"","mid":"","jian":"","zhe":"","distype":"","maxcount":"5","nowcount":"0","addtime":"","endtime":"","statis":"","regname":"山东医专","list":[]}]]
         */

        private int endtime;
        private List<List<ListBeanX>> list;

        public int getEndtime() {
            return endtime;
        }

        public void setEndtime(int endtime) {
            this.endtime = endtime;
        }

        public List<List<ListBeanX>> getList() {
            return list;
        }

        public void setList(List<List<ListBeanX>> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * id : 3
             * regid : 1
             * mid : 21
             * jian : 0
             * zhe : null
             * distype : jian
             * maxcount : 5
             * nowcount : 1
             * addtime : 1524229655
             * endtime : 1524288600
             * status : 0
             * orderprice : 3
             * regname : 临沂大学城
             * list : [{"id":"3","tuanid":"3","mid":"21","price":"3.00","paystatus":"1","remoney":null,"restatus":"0","addtime":"1524229654","regid":"1","paytype":"2","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","rname":"158****8931","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg"}]
             * statis :
             */

            private String id;
            private String regid;
            private String mid;
            private String jian;
            private Object zhe;
            private String distype;
            private String maxcount;
            private String nowcount;
            private String addtime;
            private String endtime;
            private String status;
            private int orderprice;
            private String regname;
            private String statis;
            private List<ListBean> list;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRegid() {
                return regid;
            }

            public void setRegid(String regid) {
                this.regid = regid;
            }

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getJian() {
                return jian;
            }

            public void setJian(String jian) {
                this.jian = jian;
            }

            public Object getZhe() {
                return zhe;
            }

            public void setZhe(Object zhe) {
                this.zhe = zhe;
            }

            public String getDistype() {
                return distype;
            }

            public void setDistype(String distype) {
                this.distype = distype;
            }

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

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getOrderprice() {
                return orderprice;
            }

            public void setOrderprice(int orderprice) {
                this.orderprice = orderprice;
            }

            public String getRegname() {
                return regname;
            }

            public void setRegname(String regname) {
                this.regname = regname;
            }

            public String getStatis() {
                return statis;
            }

            public void setStatis(String statis) {
                this.statis = statis;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 3
                 * tuanid : 3
                 * mid : 21
                 * price : 3.00
                 * paystatus : 1
                 * remoney : null
                 * restatus : 0
                 * addtime : 1524229654
                 * regid : 1
                 * paytype : 2
                 * distype : jian
                 * addr : 北京市北京市市辖区东城区顺利解决的功能
                 * mobile : 15846985398
                 * addressee : ～～～～
                 * tips :
                 * rname : 158****8931
                 * headimgurl : /attachment/user//20180404/1522808772_326938360.jpg
                 */

                private String id;
                private String tuanid;
                private String mid;
                private String price;
                private String paystatus;
                private Object remoney;
                private String restatus;
                private String addtime;
                private String regid;
                private String paytype;
                private String distype;
                private String addr;
                private String mobile;
                private String addressee;
                private String tips;
                private String rname;
                private String headimgurl ="";

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

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
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

                public Object getRemoney() {
                    return remoney;
                }

                public void setRemoney(Object remoney) {
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

                public String getRegid() {
                    return regid;
                }

                public void setRegid(String regid) {
                    this.regid = regid;
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

                public String getAddr() {
                    return addr;
                }

                public void setAddr(String addr) {
                    this.addr = addr;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getAddressee() {
                    return addressee;
                }

                public void setAddressee(String addressee) {
                    this.addressee = addressee;
                }

                public String getTips() {
                    return tips;
                }

                public void setTips(String tips) {
                    this.tips = tips;
                }

                public String getRname() {
                    return rname;
                }

                public void setRname(String rname) {
                    this.rname = rname;
                }

                public String getHeadimgurl() {
                    return headimgurl;
                }

                public void setHeadimgurl(String headimgurl) {
                    this.headimgurl = headimgurl;
                }
            }
        }
    }
}

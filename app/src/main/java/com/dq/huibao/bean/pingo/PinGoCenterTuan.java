package com.dq.huibao.bean.pingo;

import java.util.List;

/**
 * 拼go首页中间团信息
 * Created by d on 2018/4/17.
 */

public class PinGoCenterTuan {

    /**
     * status : 1
     * data : {"endtime":1524029400,"list":[[{"id":"1","regid":"1","mid":"2","jian":3.33,"zhe":null,"distype":"jian","maxcount":"5","addtime":"1523941422","endtime":null,"status":"0","tuancount":"2","orderprice":30,"regname":"临沂大学城","list":[{"id":"1","tuanid":"1","mid":"2","price":"20.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"逍遥","headimgurl":null},{"id":"2","tuanid":"1","mid":"4","price":"10.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"jingang","headimgurl":"/attachment/user//20180310/1520640871_1366171733.jpg"}]},{"id":"3","regid":"2","mid":"7","jian":3.33,"zhe":null,"distype":"jian","maxcount":"5","addtime":"1523941422","endtime":null,"status":"0","tuancount":"2","orderprice":40,"regname":"山东医专","list":[{"id":"5","tuanid":"3","mid":"7","price":"20.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"lionnnn","headimgurl":"/attachment/user//20180208/1518072488_75220307.jpg"},{"id":"6","tuanid":"3","mid":"8","price":"20.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"183****1017","headimgurl":null}]}],[{"id":"2","regid":"1","mid":"4","jian":null,"zhe":"9.00","distype":"zhe","maxcount":"5","addtime":"1523941422","endtime":null,"status":"0","tuancount":"2","orderprice":40,"regname":"临沂大学城","list":[{"id":"3","tuanid":"2","mid":"4","price":"20.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"jingang","headimgurl":"/attachment/user//20180310/1520640871_1366171733.jpg"},{"id":"4","tuanid":"2","mid":"7","price":"20.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"lionnnn","headimgurl":"/attachment/user//20180208/1518072488_75220307.jpg"}]},{"id":"4","regid":"2","mid":"8","jian":null,"zhe":"8.50","distype":"zhe","maxcount":"5","addtime":"1523941422","endtime":null,"status":"0","tuancount":"3","orderprice":50,"regname":"山东医专","list":[{"id":"7","tuanid":"4","mid":"8","price":"20.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"183****1017","headimgurl":null},{"id":"8","tuanid":"4","mid":"2","price":"20.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"逍遥","headimgurl":null},{"id":"9","tuanid":"4","mid":"4","price":"10.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"jingang","headimgurl":"/attachment/user//20180310/1520640871_1366171733.jpg"}]}]]}
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
         * endtime : 1524029400
         * list : [[{"id":"1","regid":"1","mid":"2","jian":3.33,"zhe":null,"distype":"jian","maxcount":"5","addtime":"1523941422","endtime":null,"status":"0","tuancount":"2","orderprice":30,"regname":"临沂大学城","list":[{"id":"1","tuanid":"1","mid":"2","price":"20.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"逍遥","headimgurl":null},{"id":"2","tuanid":"1","mid":"4","price":"10.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"jingang","headimgurl":"/attachment/user//20180310/1520640871_1366171733.jpg"}]},{"id":"3","regid":"2","mid":"7","jian":3.33,"zhe":null,"distype":"jian","maxcount":"5","addtime":"1523941422","endtime":null,"status":"0","tuancount":"2","orderprice":40,"regname":"山东医专","list":[{"id":"5","tuanid":"3","mid":"7","price":"20.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"lionnnn","headimgurl":"/attachment/user//20180208/1518072488_75220307.jpg"},{"id":"6","tuanid":"3","mid":"8","price":"20.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"183****1017","headimgurl":null}]}],[{"id":"2","regid":"1","mid":"4","jian":null,"zhe":"9.00","distype":"zhe","maxcount":"5","addtime":"1523941422","endtime":null,"status":"0","tuancount":"2","orderprice":40,"regname":"临沂大学城","list":[{"id":"3","tuanid":"2","mid":"4","price":"20.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"jingang","headimgurl":"/attachment/user//20180310/1520640871_1366171733.jpg"},{"id":"4","tuanid":"2","mid":"7","price":"20.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"lionnnn","headimgurl":"/attachment/user//20180208/1518072488_75220307.jpg"}]},{"id":"4","regid":"2","mid":"8","jian":null,"zhe":"8.50","distype":"zhe","maxcount":"5","addtime":"1523941422","endtime":null,"status":"0","tuancount":"3","orderprice":50,"regname":"山东医专","list":[{"id":"7","tuanid":"4","mid":"8","price":"20.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"183****1017","headimgurl":null},{"id":"8","tuanid":"4","mid":"2","price":"20.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"逍遥","headimgurl":null},{"id":"9","tuanid":"4","mid":"4","price":"10.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"jingang","headimgurl":"/attachment/user//20180310/1520640871_1366171733.jpg"}]}]]
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
             * id : 1
             * regid : 1
             * mid : 2
             * jian : 3.33
             * zhe : null
             * distype : jian
             * maxcount : 5
             * addtime : 1523941422
             * endtime : null
             * status : 0
             * tuancount : 2
             * orderprice : 30
             * regname : 临沂大学城
             * list : [{"id":"1","tuanid":"1","mid":"2","price":"20.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"逍遥","headimgurl":null},{"id":"2","tuanid":"1","mid":"4","price":"10.00","paystatus":"1","remoney":null,"restatus":"0","addtime":null,"rname":"jingang","headimgurl":"/attachment/user//20180310/1520640871_1366171733.jpg"}]
             */

            private String id;
            private String regid;
            private String mid;
            private double jian;
            private Object zhe;
            private String distype;
            private String maxcount;
            private String addtime;
            private Object endtime;
            private String status;
            private String tuancount;
            private int orderprice;
            private String regname;
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

            public double getJian() {
                return jian;
            }

            public void setJian(double jian) {
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

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public Object getEndtime() {
                return endtime;
            }

            public void setEndtime(Object endtime) {
                this.endtime = endtime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTuancount() {
                return tuancount;
            }

            public void setTuancount(String tuancount) {
                this.tuancount = tuancount;
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

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 1
                 * tuanid : 1
                 * mid : 2
                 * price : 20.00
                 * paystatus : 1
                 * remoney : null
                 * restatus : 0
                 * addtime : null
                 * rname : 逍遥
                 * headimgurl : null
                 */

                private String id;
                private String tuanid;
                private String mid;
                private String price;
                private String paystatus;
                private Object remoney;
                private String restatus;
                private Object addtime;
                private String rname;
                private String headimgurl;

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

                public Object getAddtime() {
                    return addtime;
                }

                public void setAddtime(Object addtime) {
                    this.addtime = addtime;
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

package com.dq.huibao.bean.pingo;

import java.util.List;

/**
 * 拼go我的订单
 * Created by d on 2018/4/19.
 */

public class PinGoMyOrder {
    /**
     * status : 1
     * data : {"isload":1,"list":[{"id":"10","tuanid":"1","mid":"21","price":"41.40","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524049148","regid":"1","paytype":"1","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","regname":"临沂大学城"},{"id":"11","tuanid":"0","mid":"21","price":"13.80","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524049308","regid":"2","paytype":"2","distype":"zhe","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","regname":"山东医专"},{"id":"12","tuanid":"0","mid":"21","price":"191.40","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524054631","regid":"1","paytype":"2","distype":"zhe","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","regname":"临沂大学城"},{"id":"13","tuanid":"0","mid":"21","price":"6.00","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524054718","regid":"1","paytype":"2","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","regname":"临沂大学城"},{"id":"14","tuanid":"1","mid":"21","price":"3.00","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524056529","regid":"1","paytype":"1","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","regname":"临沂大学城"},{"id":"15","tuanid":"1","mid":"21","price":"3.00","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524056642","regid":"1","paytype":"1","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","regname":"临沂大学城"},{"id":"16","tuanid":"1","mid":"21","price":"3.00","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524056712","regid":"1","paytype":"1","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","regname":"临沂大学城"},{"id":"17","tuanid":"1","mid":"21","price":"115.20","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524098149","regid":"1","paytype":"1","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"JUU2JTk4JUFGJUU0JUI4JThEJUU2JTk4JUFGJUU0JUI5JUIxJUU3JUEwJTgx","regname":"临沂大学城"},{"id":"18","tuanid":"0","mid":"21","price":"3.00","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524098335","regid":"1","paytype":"2","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"%E4%B9%B1%E7%A0%81%EF%BC%9F","regname":"临沂大学城"},{"id":"19","tuanid":"1","mid":"21","price":"3.00","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524098351","regid":"1","paytype":"1","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"%E4%B9%B1%E7%A0%81%EF%BC%9F","regname":"临沂大学城"}]}
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
         * isload : 1
         * list : [{"id":"10","tuanid":"1","mid":"21","price":"41.40","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524049148","regid":"1","paytype":"1","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","regname":"临沂大学城"},{"id":"11","tuanid":"0","mid":"21","price":"13.80","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524049308","regid":"2","paytype":"2","distype":"zhe","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","regname":"山东医专"},{"id":"12","tuanid":"0","mid":"21","price":"191.40","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524054631","regid":"1","paytype":"2","distype":"zhe","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","regname":"临沂大学城"},{"id":"13","tuanid":"0","mid":"21","price":"6.00","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524054718","regid":"1","paytype":"2","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","regname":"临沂大学城"},{"id":"14","tuanid":"1","mid":"21","price":"3.00","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524056529","regid":"1","paytype":"1","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","regname":"临沂大学城"},{"id":"15","tuanid":"1","mid":"21","price":"3.00","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524056642","regid":"1","paytype":"1","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","regname":"临沂大学城"},{"id":"16","tuanid":"1","mid":"21","price":"3.00","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524056712","regid":"1","paytype":"1","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"","regname":"临沂大学城"},{"id":"17","tuanid":"1","mid":"21","price":"115.20","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524098149","regid":"1","paytype":"1","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"JUU2JTk4JUFGJUU0JUI4JThEJUU2JTk4JUFGJUU0JUI5JUIxJUU3JUEwJTgx","regname":"临沂大学城"},{"id":"18","tuanid":"0","mid":"21","price":"3.00","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524098335","regid":"1","paytype":"2","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"%E4%B9%B1%E7%A0%81%EF%BC%9F","regname":"临沂大学城"},{"id":"19","tuanid":"1","mid":"21","price":"3.00","paystatus":"0","remoney":null,"restatus":"0","addtime":"1524098351","regid":"1","paytype":"1","distype":"jian","addr":"北京市北京市市辖区东城区顺利解决的功能","mobile":"15846985398","addressee":"～～～～","tips":"%E4%B9%B1%E7%A0%81%EF%BC%9F","regname":"临沂大学城"}]
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
             * id : 10
             * tuanid : 1
             * mid : 21
             * price : 41.40
             * paystatus : 0
             * remoney : null
             * restatus : 0
             * addtime : 1524049148
             * regid : 1
             * paytype : 1
             * distype : jian
             * addr : 北京市北京市市辖区东城区顺利解决的功能
             * mobile : 15846985398
             * addressee : ～～～～
             * tips :
             * regname : 临沂大学城
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
            private String regname;

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

            public String getRegname() {
                return regname;
            }

            public void setRegname(String regname) {
                this.regname = regname;
            }
        }
    }
}

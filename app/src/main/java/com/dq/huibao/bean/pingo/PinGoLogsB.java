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
     * data : [{"id":"25","tuanid":"18","regid":"1","price":"0.01","paystatus":"1","remoney":"0","restatus":"0","addtime":"1525336600","paytype":"2","distype":"jian","tips":"","userlist":[{"mid":"38","headimgurl":"/attachement/user/shop/head.jpg"},{"mid":"21","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg"}],"regname":"临沂大学城","tuan":{"maxcount":"5","nowcount":"2","status":"0"},"goodsname":"久久丫甜辣薄豆干好吃的豆腐干","thumb":"/attachment/images/sz_yi/1604/2017/12/F5iAUklELCEcrH755EA8klHikLI5e8.jpg"},{"id":"9","tuanid":"6","regid":"2","price":"7.00","paystatus":"0","remoney":"0","restatus":"0","addtime":"1524473821","paytype":"1","distype":"jian","tips":"","userlist":[{"mid":"25","headimgurl":"/attachement/user/shop/head.jpg"},{"mid":"21","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg"}],"regname":"山东医专","tuan":{"maxcount":"5","nowcount":"2","status":"1"},"goodsname":"奶油葵花籽 美味奶油瓜子坚果炒货休闲零食","thumb":""},{"id":"22","tuanid":"14","regid":"1","price":"162.00","paystatus":"1","remoney":"0","restatus":"0","addtime":"1525226489","paytype":"2","distype":"jian","tips":"","userlist":[{"mid":"21","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg"}],"regname":"临沂大学城","tuan":{"maxcount":"5","nowcount":"1","status":"2"},"goodsname":"NPVU打底套头毛衣纯色针织衫喇叭袖修身圆领衣服2017秋装新款3954","thumb":""},{"id":"21","tuanid":"13","regid":"1","price":"0.01","paystatus":"1","remoney":"0","restatus":"0","addtime":"1525226431","paytype":"2","distype":"jian","tips":"","userlist":[{"mid":"21","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg"}],"regname":"临沂大学城","tuan":{"maxcount":"5","nowcount":"1","status":"2"},"goodsname":"久久丫甜辣薄豆干好吃的豆腐干","thumb":""},{"id":"20","tuanid":"15","regid":"1","price":"13.80","paystatus":"1","remoney":"0","restatus":"0","addtime":"1525221927","paytype":"2","distype":"zhe","tips":"","userlist":[{"mid":"21","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg"}],"regname":"临沂大学城","tuan":{"maxcount":"5","nowcount":"1","status":"2"},"goodsname":"儿童宝宝益智玩具7色雪花片100片积木玩具拼插拼装积木玩具","thumb":""},{"id":"19","tuanid":"16","regid":"1","price":"13.80","paystatus":"1","remoney":"0","restatus":"0","addtime":"1525221899","paytype":"2","distype":"zhe","tips":"","userlist":[{"mid":"21","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg"}],"regname":"临沂大学城","tuan":{"maxcount":"5","nowcount":"1","status":"2"},"goodsname":"儿童宝宝益智玩具7色雪花片100片积木玩具拼插拼装积木玩具","thumb":""},{"id":"17","tuanid":"11","regid":"1","price":"7.00","paystatus":"1","remoney":"0","restatus":"0","addtime":"1524880338","paytype":"2","distype":"jian","tips":"","userlist":[{"mid":"21","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg"}],"regname":"临沂大学城","tuan":{"maxcount":"5","nowcount":"1","status":"2"},"goodsname":"奶油葵花籽 美味奶油瓜子坚果炒货休闲零食","thumb":""},{"id":"16","tuanid":"10","regid":"1","price":"0.01","paystatus":"1","remoney":"0","restatus":"0","addtime":"1524791692","paytype":"2","distype":"jian","tips":"","userlist":[{"mid":"21","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg"}],"regname":"临沂大学城","tuan":{"maxcount":"5","nowcount":"1","status":"2"},"goodsname":"久久丫甜辣薄豆干好吃的豆腐干","thumb":""},{"id":"10","tuanid":"4","regid":"1","price":"45.00","paystatus":"1","remoney":"0","restatus":"0","addtime":"1524474193","paytype":"2","distype":"jian","tips":"","userlist":[{"mid":"25","headimgurl":"/attachement/user/shop/head.jpg"},{"mid":"21","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg"}],"regname":"临沂大学城","tuan":{"maxcount":"5","nowcount":"2","status":"2"},"goodsname":"大号可爱萌条纹趴趴狗毛绒玩具狗玩偶布娃娃爬爬狗生日礼物送女生","thumb":""},{"id":"3","tuanid":"3","regid":"1","price":"0.01","paystatus":"1","remoney":"0","restatus":"0","addtime":"1524362388","paytype":"2","distype":"jian","tips":"","userlist":[{"mid":"21","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg"}],"regname":"临沂大学城","tuan":{"maxcount":"5","nowcount":"1","status":"2"},"goodsname":"久久丫甜辣薄豆干好吃的豆腐干","thumb":""}]
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
         * id : 25
         * tuanid : 18
         * regid : 1
         * price : 0.01
         * paystatus : 1
         * remoney : 0
         * restatus : 0
         * addtime : 1525336600
         * paytype : 2
         * distype : jian
         * tips :
         * userlist : [{"mid":"38","headimgurl":"/attachement/user/shop/head.jpg"},{"mid":"21","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg"}]
         * regname : 临沂大学城
         * tuan : {"maxcount":"5","nowcount":"2","status":"0"}
         * goodsname : 久久丫甜辣薄豆干好吃的豆腐干
         * thumb : /attachment/images/sz_yi/1604/2017/12/F5iAUklELCEcrH755EA8klHikLI5e8.jpg
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
        private String goodsname;
        private String thumb;
        private List<UserlistBean> userlist;

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

        public List<UserlistBean> getUserlist() {
            return userlist;
        }

        public void setUserlist(List<UserlistBean> userlist) {
            this.userlist = userlist;
        }

        public static class TuanBean {
            /**
             * maxcount : 5
             * nowcount : 2
             * status : 0
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

        public static class UserlistBean {
            /**
             * mid : 38
             * headimgurl : /attachement/user/shop/head.jpg
             */

            private String mid;
            private String headimgurl;

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
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

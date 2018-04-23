package com.dq.huibao.bean.pintuan;

import java.util.List;

/**
 * Created by d on 2018/4/22.
 */

public class MyPinTuanLogs {

    /**
     * status : 1
     * data : {"list":[{"id":"8","uid":"21","pid":"21","starttime":"1523674816","endtime":"1523674916","tuanid":"12","num":"27","nickname":"66666","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"9","uid":"21","pid":"21","starttime":"1566888888","endtime":"1655232332","tuanid":"12","num":"26","nickname":"5555555555","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"10","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"25","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"11","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"24","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"12","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"23","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"13","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"22","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"14","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"21","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"15","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"20","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"16","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"19","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"17","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"18","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}}],"listcount":"27"}
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
         * list : [{"id":"8","uid":"21","pid":"21","starttime":"1523674816","endtime":"1523674916","tuanid":"12","num":"27","nickname":"66666","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"9","uid":"21","pid":"21","starttime":"1566888888","endtime":"1655232332","tuanid":"12","num":"26","nickname":"5555555555","headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"10","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"25","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"11","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"24","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"12","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"23","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"13","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"22","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"14","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"21","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"15","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"20","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"16","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"19","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}},{"id":"17","uid":"21","pid":"21","starttime":null,"endtime":null,"tuanid":"12","num":"18","nickname":null,"headimgurl":"/attachment/user//20180404/1522808772_326938360.jpg","status":"0","ctime":"0","idfordetail":"21","tuan":{"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}}]
         * listcount : 27
         */

        private String listcount;
        private List<ListBean> list;

        public String getListcount() {
            return listcount;
        }

        public void setListcount(String listcount) {
            this.listcount = listcount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 8
             * uid : 21
             * pid : 21
             * starttime : 1523674816
             * endtime : 1523674916
             * tuanid : 12
             * num : 27
             * nickname : 66666
             * headimgurl : /attachment/user//20180404/1522808772_326938360.jpg
             * status : 0
             * ctime : 0
             * idfordetail : 21
             * tuan : {"id":"12","tuanname":"test国际快递费包括历史年度半年","thumb":"http://p2.qhimgs4.com/t014a167f02825c4d94.webp","goodsid":"20043"}
             */

            private String id;
            private String uid;
            private String pid;
            private String starttime;
            private String endtime;
            private String tuanid;
            private String num;
            private String nickname;
            private String headimgurl;
            private String status;
            private String ctime;
            private String idfordetail;
            private TuanBean tuan;

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

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getTuanid() {
                return tuanid;
            }

            public void setTuanid(String tuanid) {
                this.tuanid = tuanid;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getIdfordetail() {
                return idfordetail;
            }

            public void setIdfordetail(String idfordetail) {
                this.idfordetail = idfordetail;
            }

            public TuanBean getTuan() {
                return tuan;
            }

            public void setTuan(TuanBean tuan) {
                this.tuan = tuan;
            }

            public static class TuanBean {
                /**
                 * id : 12
                 * tuanname : test国际快递费包括历史年度半年
                 * thumb : http://p2.qhimgs4.com/t014a167f02825c4d94.webp
                 * goodsid : 20043
                 */

                private String id;
                private String tuanname;
                private String thumb;
                private String goodsid;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getTuanname() {
                    return tuanname;
                }

                public void setTuanname(String tuanname) {
                    this.tuanname = tuanname;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getGoodsid() {
                    return goodsid;
                }

                public void setGoodsid(String goodsid) {
                    this.goodsid = goodsid;
                }
            }
        }
    }
}

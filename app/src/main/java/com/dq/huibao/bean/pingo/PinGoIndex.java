package com.dq.huibao.bean.pingo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 拼go首页
 * Created by d on 2018/4/17.
 */

public class PinGoIndex {
    /**
     * status : 1
     * data : [{"id":"40","title":"大学生拼购首页banner","orderdisplay":"17","type":"mobile","width":"100","module":"banner","float":"left","isshow":"3","child":[{"id":"52","title":"","thumb":"/attachment/admin//20180417/1523930173_1785382490.png","width":"100","type":"url","content":"#","module_id":"40","explain":"#","displayorder":"0"}]},{"id":"41","title":"大学生拼购首页菜单","orderdisplay":"18","type":"mobile","width":"100","module":"imglist","float":"left","isshow":"3","child":[{"id":"53","title":"","thumb":"/attachment/admin//20180417/1523930389_304900958.png","width":"25","type":"other","content":"立减金额","module_id":"41","explain":"立减金额","displayorder":"0"},{"id":"54","title":"","thumb":"/attachment/admin//20180417/1523930501_1288100121.png","width":"100","type":"other","content":"立打折扣","module_id":"41","explain":"立打折扣","displayorder":"0"},{"id":"55","title":"","thumb":"/attachment/admin//20180417/1523930536_1368930000.png","width":"25","type":"other","content":"当季特卖","module_id":"41","explain":"当季特卖","displayorder":"0"},{"id":"56","title":"","thumb":"/attachment/admin//20180417/1523930578_1172191204.png","width":"100","type":"other","content":"周五秒杀","module_id":"41","explain":"周五秒杀","displayorder":"0"}]},{"id":"43","title":"大学生拼购秒杀","orderdisplay":"19","type":"mobile","width":"100","module":"dxspg","float":"left","isshow":"3","child":[]},{"id":"44","title":"周五秒杀图片","orderdisplay":"21","type":"mobile","width":"100","module":"imglist","float":"left","isshow":"3","child":[{"id":"57","title":"","thumb":"/attachment/admin//20180417/1523931424_227388615.jpg","width":"100","type":"url","content":"#","module_id":"44","explain":"#","displayorder":"0"}]},{"id":"45","title":"周五秒杀商品","orderdisplay":"22","type":"mobile","width":"100","module":"glist","float":"left","isshow":"3","child":[{"id":"6","goodsid":"25123","goodsname":"儿童宝宝益智玩具7色雪花片100片积木玩具拼插拼装积木玩具","thumb":"/attachment/images/sz_yi/1604/2017/11/CsqQnXZnJl9Zt968FfsQs7j9EFsFZe.jpg","marketprice":"13.80","width":"100"},{"id":"7","goodsid":"20179","goodsname":"大号可爱萌条纹趴趴狗毛绒玩具狗玩偶布娃娃爬爬狗生日礼物送女生","thumb":"/attachment/images/sz_yi/1604/2017/09/FtK9AF92a5Aff4L5Tb4Q2kA99j52Q9.png","marketprice":"105.00","width":"100"},{"id":"8","goodsid":"24939","goodsname":"加大版150g 韩国春秋冬季獭兔毛领子毛绒围巾女韩版学生围脖 套口","thumb":"/attachment/images/sz_yi/1604/2017/11/V1e18218M8czZo41Omnff1Z0Gj83n5.jpg","marketprice":"16.80","width":"100"},{"id":"9","goodsid":"24544","goodsname":" 厂家直销睡衣女春秋时尚韩版休闲套装家居服女士睡衣长袖新品","thumb":"/attachment/images/sz_yi/1604/2017/10/xKDYtYY3FRzK62z8zfFkID8d8j8Fc8.jpg","marketprice":"108.00","width":"100"}]},{"id":"46","title":"当季特卖图片","orderdisplay":"23","type":"mobile","width":"100","module":"imglist","float":"left","isshow":"3","child":[{"id":"58","title":"","thumb":"/attachment/admin//20180417/1523931467_617016411.jpg","width":"100","type":"url","content":"#","module_id":"46","explain":"#","displayorder":"0"}]},{"id":"47","title":"当季特卖商品","orderdisplay":"24","type":"mobile","width":"100","module":"glist","float":"left","isshow":"3","child":[{"id":"1","goodsid":"25728","goodsname":"久久丫甜辣薄豆干好吃的豆腐干","thumb":"/attachment/images/sz_yi/1604/2017/12/F5iAUklELCEcrH755EA8klHikLI5e8.jpg","marketprice":"1.50","width":"100"},{"id":"2","goodsid":"25670","goodsname":"奶油瓜子现金补拍","thumb":"/attachment/images/1604/2017/12/PHHJpQvBFjql1Pu7JJJ7Pb1hFlDJ1c.jpg","marketprice":"1.00","width":"100"},{"id":"3","goodsid":"25743","goodsname":"马奇新新马来西亚进口LEXUS力士系列巧克力味涂层夹心饼干200g","thumb":"/attachment/images/sz_yi/1604/2017/12/p4Xt84g4r3S88Z3yx49c0xxy7cxGD8.jpg","marketprice":"12.80","width":"100"},{"id":"5","goodsid":"24371","goodsname":"NPVU打底套头毛衣纯色针织衫喇叭袖修身圆领衣服2017秋装新款3954","thumb":"/attachment/images/sz_yi/1604/2017/10/CLgehGKpqeCjPXmeTh1hCZC1REcmND.jpg","marketprice":"162.00","width":"100"}]},{"id":"48","title":"为您推荐图片","orderdisplay":"25","type":"mobile","width":"100","module":"imglist","float":"left","isshow":"3","child":[{"id":"67","title":"","thumb":"/attachment/admin//20180417/1523931962_1828598824.jpg","width":"100","type":"url","content":"#","module_id":"48","explain":"","displayorder":"0"}]}]
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
         * id : 40
         * title : 大学生拼购首页banner
         * orderdisplay : 17
         * type : mobile
         * width : 100
         * module : banner
         * float : left
         * isshow : 3
         * child : [{"id":"52","title":"","thumb":"/attachment/admin//20180417/1523930173_1785382490.png","width":"100","type":"url","content":"#","module_id":"40","explain":"#","displayorder":"0"}]
         */

        private String id;
        private String title;
        private String orderdisplay;
        private String type;
        private String width;
        private String module;
        @SerializedName("float")
        private String floatX;
        private String isshow;
        private List<ChildBean> child;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getOrderdisplay() {
            return orderdisplay;
        }

        public void setOrderdisplay(String orderdisplay) {
            this.orderdisplay = orderdisplay;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public String getFloatX() {
            return floatX;
        }

        public void setFloatX(String floatX) {
            this.floatX = floatX;
        }

        public String getIsshow() {
            return isshow;
        }

        public void setIsshow(String isshow) {
            this.isshow = isshow;
        }

        public List<ChildBean> getChild() {
            return child;
        }

        public void setChild(List<ChildBean> child) {
            this.child = child;
        }

        public static class ChildBean {
            /**
             * id : 52
             * title :
             * thumb : /attachment/admin//20180417/1523930173_1785382490.png
             * width : 100
             * type : url
             * content : #
             * module_id : 40
             * explain : #
             * displayorder : 0
             */

            private String id;
            private String title;
            private String thumb;
            private String width;
            private String type;
            private String content;
            private String module_id;
            private String explain;
            private String displayorder;
            /**
             * goodsid : 24371
             * goodsname : NPVU打底套头毛衣纯色针织衫喇叭袖修身圆领衣服2017秋装新款3954
             * marketprice : 162.00
             */

            private String goodsid;
            private String goodsname;
            private String marketprice;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getModule_id() {
                return module_id;
            }

            public void setModule_id(String module_id) {
                this.module_id = module_id;
            }

            public String getExplain() {
                return explain;
            }

            public void setExplain(String explain) {
                this.explain = explain;
            }

            public String getDisplayorder() {
                return displayorder;
            }

            public void setDisplayorder(String displayorder) {
                this.displayorder = displayorder;
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

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }
        }
    }
}

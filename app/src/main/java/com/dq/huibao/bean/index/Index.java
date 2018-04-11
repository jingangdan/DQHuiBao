package com.dq.huibao.bean.index;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 首页 数据实体类
 * Created by jingang on 2018/1/10.
 */

public class Index {

    /**
     * status : 1
     * data : [{"id":"10","title":"首页顶部banner","orderdisplay":"1","type":"mobile","width":"100","module":"banner","float":"left","isshow":"1","child":[{"id":"1","title":"","thumb":"http://new.dequanhuibao.com/attachment/admin//20180109/1515482176_2030471201.png","width":"100","type":"url","content":"#","module_id":"10","explain":"","displayorder":"0"},{"id":"2","title":"","thumb":"http://new.dequanhuibao.com/attachment/admin//20180109/1515482148_1621848798.jpg","width":"100","type":"url","content":"#","module_id":"10","explain":"","displayorder":"0"},{"id":"3","title":"","thumb":"http://new.dequanhuibao.com/attachment/admin//20180109/1515482127_1034125863.jpg","width":"100","type":"url","content":"#","module_id":"10","explain":"","displayorder":"0"},{"id":"4","title":"","thumb":"http://new.dequanhuibao.com/attachment/admin//20180109/1515482109_1808559096.jpg","width":"100","type":"url","content":"#","module_id":"10","explain":"","displayorder":"0"},{"id":"5","title":"","thumb":"http://new.dequanhuibao.com/attachment/admin//20180109/1515482078_1336605997.jpg","width":"100","type":"url","content":"#","module_id":"10","explain":"","displayorder":"0"}]},{"id":"11","title":"首页菜单","orderdisplay":"2","type":"mobile","width":"100","module":"menu","float":"left","isshow":"1","child":[{"id":"5","title":"热卖推荐","thumb":"/attachment/admin//20180320/1521544001_903727785.png","type":"search","content":"isrecommand=1&ishot=1","displayorder":"5","explain":"推荐,热卖"},{"id":"4","title":"美食天地","thumb":"/attachment/admin//20180320/1521544035_667764337.png","type":"cate","content":"723","displayorder":"4","explain":"美食"},{"id":"3","title":"生活必备","thumb":"/attachment/admin//20180108/1515392072_1807396613.png","type":"cate","content":"722","displayorder":"3","explain":"生活日常"},{"id":"2","title":"服装专区","thumb":"/attachment/admin//20180108/1515392015_1829963740.png","type":"cate","content":"718","displayorder":"2","explain":"服饰"},{"id":"1","title":"每日签到","thumb":"/attachment/admin//20180131/1517368916_894989743.png","type":"action","content":"sign","displayorder":"1","explain":"签到"}]},{"id":"12","title":"公告","orderdisplay":"3","type":"mobile","width":"100","module":"notice","float":"left","isshow":"1","child":[{"id":"7","title":"","thumb":"","width":"100","type":"cate","content":"470","module_id":"12","explain":"服饰-女装-衬衫","displayorder":"0"}]},{"id":"21","title":"热门精选","orderdisplay":"4","type":"mobile","width":"100","module":"imglist","float":"left","isshow":"1","child":[{"id":"8","title":"","thumb":"/attachment/admin//20180409/01523267173_850487962.jpg","width":"25","type":"url","content":"#","module_id":"21","explain":"","displayorder":"0"},{"id":"9","title":"","thumb":"/attachment/admin//20180409/01523267173_850487962.jpg","width":"25","type":"url","content":"#","module_id":"21","explain":"","displayorder":"0"},{"id":"10","title":"","thumb":"/attachment/admin//20180409/01523267173_850487962.jpg","width":"25","type":"url","content":"#","module_id":"21","explain":"","displayorder":"0"},{"id":"11","title":"","thumb":"/attachment/admin//20180409/01523267173_850487962.jpg","width":"25","type":"url","content":"#","module_id":"21","explain":"","displayorder":"0"}]},{"id":"22","title":"爱生活","orderdisplay":"5","type":"mobile","width":"100","module":"imglist","float":"left","isshow":"1","child":[]},{"id":"23","title":"为您推荐","orderdisplay":"6","type":"mobile","width":"100","module":"imglist","float":"left","isshow":"1","child":[]},{"id":"24","title":"全场秒杀仅限快捷支付","orderdisplay":"7","type":"mobile","width":"100","module":"imglist","float":"right","isshow":"1","child":[]},{"id":"25","title":"第二件半价","orderdisplay":"8","type":"mobile","width":"100","module":"imglist","float":"left","isshow":"1","child":[]},{"id":"27","title":"精选商品","orderdisplay":"9","type":"mobile","width":"100","module":"glist","float":"right","isshow":"1","child":[{"id":"25730","thumb":"/attachment/images/sz_yi/1604/2017/12/q6V22yGzsg72wTLtnjy2w4AK2tV2Oy.jpg","goodsname":"四川特产美食 蜀道香麻辣鸭舌头金钩10g小吃零食","marketprice":"4.00","productprice":"5.00","width":"100"}]},{"id":"31","title":"商品列表10","orderdisplay":"10","type":"mobile","width":"100","module":"glist","float":"right","isshow":"1","child":[{"id":"25726","thumb":"/attachment/images/sz_yi/1604/2017/12/fOO4oOvo3NNN0nZN3FdV440N4IfnBI.jpg","goodsname":"蜀道香麻辣鱿鱼条20g 四川特产即食海鲜小吃麻辣鱿鱼丝休闲零食","marketprice":"3.00","productprice":"5.00","width":"100"}]},{"id":"32","title":"商品列表11","orderdisplay":"11","type":"mobile","width":"100","module":"glist","float":"left","isshow":"1","child":[{"id":"25743","thumb":"/attachment/images/sz_yi/1604/2017/12/p4Xt84g4r3S88Z3yx49c0xxy7cxGD8.jpg","goodsname":"马奇新新马来西亚进口LEXUS力士系列巧克力味涂层夹心饼干200g","marketprice":"12.80","productprice":"19.90","width":"100"}]},{"id":"33","title":"商品列表12","orderdisplay":"12","type":"mobile","width":"100","module":"glist","float":"left","isshow":"1","child":[{"id":"25638","thumb":"/attachment/images/sz_yi/1604/2017/12/RX4GUG0ZZevX6cV0gZ7wLS4VvVExm6.jpg","goodsname":"印尼进口Gery芝莉奶酪芝士夹心饼干220g单独盒装cheese休闲零食品","marketprice":"9.90","productprice":"15.80","width":"100"}]}]
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
         * id : 10
         * title : 首页顶部banner
         * orderdisplay : 1
         * type : mobile
         * width : 100
         * module : banner
         * float : left
         * isshow : 1
         * child : [{"id":"1","title":"","thumb":"http://new.dequanhuibao.com/attachment/admin//20180109/1515482176_2030471201.png","width":"100","type":"url","content":"#","module_id":"10","explain":"","displayorder":"0"},{"id":"2","title":"","thumb":"http://new.dequanhuibao.com/attachment/admin//20180109/1515482148_1621848798.jpg","width":"100","type":"url","content":"#","module_id":"10","explain":"","displayorder":"0"},{"id":"3","title":"","thumb":"http://new.dequanhuibao.com/attachment/admin//20180109/1515482127_1034125863.jpg","width":"100","type":"url","content":"#","module_id":"10","explain":"","displayorder":"0"},{"id":"4","title":"","thumb":"http://new.dequanhuibao.com/attachment/admin//20180109/1515482109_1808559096.jpg","width":"100","type":"url","content":"#","module_id":"10","explain":"","displayorder":"0"},{"id":"5","title":"","thumb":"http://new.dequanhuibao.com/attachment/admin//20180109/1515482078_1336605997.jpg","width":"100","type":"url","content":"#","module_id":"10","explain":"","displayorder":"0"}]
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

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", orderdisplay='" + orderdisplay + '\'' +
                    ", type='" + type + '\'' +
                    ", width='" + width + '\'' +
                    ", module='" + module + '\'' +
                    ", floatX='" + floatX + '\'' +
                    ", isshow='" + isshow + '\'' +
                    ", child=" + child +
                    '}';
        }

        public static class ChildBean {
            /**
             * id : 1
             * title :
             * thumb : http://new.dequanhuibao.com/attachment/admin//20180109/1515482176_2030471201.png
             * width : 100
             * type : url
             * content : #
             * module_id : 10
             * explain :
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
             * goodsname : 马奇新新马来西亚进口LEXUS力士系列巧克力味涂层夹心饼干200g
             * marketprice : 12.80
             * productprice : 19.90
             */

            private String goodsname;
            private String marketprice;
            private String productprice;

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

            @Override
            public String toString() {
                return "ChildBean{" +
                        "id='" + id + '\'' +
                        ", title='" + title + '\'' +
                        ", thumb='" + thumb + '\'' +
                        ", width='" + width + '\'' +
                        ", type='" + type + '\'' +
                        ", content='" + content + '\'' +
                        ", module_id='" + module_id + '\'' +
                        ", explain='" + explain + '\'' +
                        ", displayorder='" + displayorder + '\'' +
                        '}';
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

            public String getProductprice() {
                return productprice;
            }

            public void setProductprice(String productprice) {
                this.productprice = productprice;
            }
        }
    }
}

package com.dq.huibao.bean.xstore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 小店自选商品列表
 * Created by d on 2018/4/3.
 */

public class XStoreZXGoods implements Serializable{
    /**
     * status : 1
     * data : {"curpage":1,"isload":1,"list":[{"id":"20043","goodsname":"4条装南极人男士内裤男 男平角裤纯棉质透气四角底裤衩青年短裤头","thumb":"images/sz_yi/1604/2017/09/Rpl0V8VEjb2eE3zjbJ3EBjB3bV3RBl.jpg","marketprice":"42.90","unit":"盒","productprice":"56.80","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"1","issendfree":"0","istime":"0","flag":"0"},{"id":"20236","goodsname":"初慕 37度恒温超薄男士美体保暖内衣 发热纤维紧身秋冬打底套装","thumb":"images/sz_yi/1604/2017/09/rh6B7BKf67Jk87KW48889kBB87JbUB.jpg","marketprice":"47.00","unit":"套","productprice":"49.00","ishot":"0","isrecommand":"0","isnew":"1","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20237","goodsname":"2017新款发热男士保暖内衣套装 无缝美体V领秋衣秋裤","thumb":"images/sz_yi/1604/2017/09/jUBMn7J9n7AJ4p977AMa7ZRJNj9J06.jpg","marketprice":"59.00","unit":"","productprice":"79.00","ishot":"0","isrecommand":"1","isnew":"1","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20277","goodsname":"夏娜奇男士内裤纯棉宽条纹印花男青年单条盒装中腰平角裤","thumb":"images/sz_yi/1604/2017/09/UHwN7hdh1M1cHc7hr7nW1xMW7CwH7n.jpg","marketprice":"23.00","unit":"件","productprice":"28.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20282","goodsname":"MIBOER 男士棉质口袋中腰平角裤 吸汗透气棉质平角裤性感撞色","thumb":"images/sz_yi/1604/2017/09/rKjZuTFT39z3iv8tu9T8c66p9IJcJT.jpg","marketprice":"23.00","unit":"件","productprice":"30.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20320","goodsname":"2017新款多色款男士平角内裤冰丝无痕纯色弹力锦纶贴边男","thumb":"images/sz_yi/1604/2017/09/q6C98qt63tgmgQKl9OmTOdze9dz8OD.jpg","marketprice":"21.80","unit":"件","productprice":"22.80","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20324","goodsname":"夏娜奇海军蓝条纹情侣内裤性感男士平角女士三角纯棉可爱甜美内裤","thumb":"images/sz_yi/1604/2017/09/r12GZ4Q0q9T4TYtyyyS0J116ppw2WT.jpg","marketprice":"21.80","unit":"件","productprice":"26.80","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20325","goodsname":"夏娜奇品牌心形印花情侣内裤天竹纤维男平角女士三角内裤","thumb":"images/sz_yi/1604/2017/09/yOAPBtH4lTBIFvGZFIupPgG8aaAUoI.jpg","marketprice":"29.00","unit":"件","productprice":"36.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20326","goodsname":"夏娜奇狂野豹纹情侣内裤男士平角女士三角底裤新款","thumb":"images/sz_yi/1604/2017/09/vJz8FC9IfjfCi99ckKDdt7D8wiryk5.jpg","marketprice":"21.80","unit":"件","productprice":"26.80","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20327","goodsname":"夏娜奇情侣内裤纯棉撞色小碎花船锚男士中腰平角女士三角内裤","thumb":"images/sz_yi/1604/2017/09/uqME6NAMNDvMdTwUdTANw8QWuvEumN.jpg","marketprice":"21.80","unit":"件","productprice":"26.80","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20329","goodsname":"夏娜奇2017新品纯棉情侣内裤纯色卡通可爱男女士平角中腰内裤","thumb":"images/sz_yi/1604/2017/09/Lx433mi3fR3KgsWgWRzwWDqM7e9Q4K.jpg","marketprice":"21.80","unit":"件","productprice":"23.80","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20330","goodsname":"夏娜奇2016新款时尚情侣内裤舒适竹纤维纯色运动版男女平角内裤","thumb":"images/1604/2017/09/iqaZN21szDs2g5A5nNZS21BagAhfth.png","marketprice":"21.60","unit":"件","productprice":"39.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20332","goodsname":"夏娜奇2017新款情侣内裤纯棉圆点点男士平角女三角内裤","thumb":"images/sz_yi/1604/2017/09/JkYgtKTykoKg5y577yfy97vOZK0oK1.jpg","marketprice":"22.80","unit":"件","productprice":"23.80","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20333","goodsname":"2017新款情侣内裤纯棉男女士内裤个性条纹男平角女三角","thumb":"images/sz_yi/1604/2017/09/Ojf8fUV0OUOv6v8LVB86u68l9zdVOd.jpg","marketprice":"22.80","unit":"件","productprice":"28.00","ishot":"0","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20482","goodsname":"U凸男士冰丝内裤纯色超薄透气性感透明内裤舒适平角裤夏","thumb":"images/1604/2017/09/G1757wGZ5cC5wrc5D73DllcY6r5D7P.png","marketprice":"20.80","unit":"件","productprice":"22.80","ishot":"1","isrecommand":"0","isnew":"1","isdiscount":"1","issendfree":"0","istime":"0","flag":"0"},{"id":"20483","goodsname":"潮流男士冰丝透气中腰U凸超薄锦纶性感透明平角内裤","thumb":"images/sz_yi/1604/2017/09/g9zo6VZSrrSRFsvfrwVRvsZfOvORJ7.jpg","marketprice":"14.00","unit":"件","productprice":"16.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"1","issendfree":"0","istime":"0","flag":"0"},{"id":"20486","goodsname":"耀婷爆款男士仿真丝睡衣包邮中长款家居睡袍长袖家居服睡服","thumb":"images/sz_yi/1604/2017/09/IxjSbJA1PjjHnWuma0y4S9YbhHs1jN.jpg","marketprice":"85.00","unit":"件","productprice":"109.00","ishot":"0","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20490","goodsname":"佐奈尔男士内裤透气舒适吸汗男性莫尔代无痕平角内裤3条礼盒装","thumb":"images/sz_yi/1604/2017/09/P7nBNKJFf7b717GcKTkGf4RbeE7G1Y.jpg","marketprice":"99.00","unit":"盒","productprice":"109.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"1","issendfree":"0","istime":"0","flag":"0"},{"id":"20508","goodsname":"华杰龙花纹阿罗裤男士内裤男生柔软舒适全棉透气B0944","thumb":"images/sz_yi/1604/2017/09/uFFTH6E869p80e50169905e06t6PoT.jpg","marketprice":"32.00","unit":"件","productprice":"40.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"1","issendfree":"0","istime":"0","flag":"0"},{"id":"20510","goodsname":"4条装男士纯棉无痕内裤活性环保印花四角裤U凸个性潮流青年平角裤","thumb":"images/sz_yi/1604/2017/09/rD3wiwUX3igXR5Rc2uDuze2z5cEuuG.jpg","marketprice":"62.00","unit":"件","productprice":"65.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"1","issendfree":"0","istime":"0","flag":"0"}]}
     */

    private int status;
    private DataBean data = new DataBean();

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

    public static class DataBean implements Serializable{
        /**
         * curpage : 1
         * isload : 1
         * list : [{"id":"20043","goodsname":"4条装南极人男士内裤男 男平角裤纯棉质透气四角底裤衩青年短裤头","thumb":"images/sz_yi/1604/2017/09/Rpl0V8VEjb2eE3zjbJ3EBjB3bV3RBl.jpg","marketprice":"42.90","unit":"盒","productprice":"56.80","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"1","issendfree":"0","istime":"0","flag":"0"},{"id":"20236","goodsname":"初慕 37度恒温超薄男士美体保暖内衣 发热纤维紧身秋冬打底套装","thumb":"images/sz_yi/1604/2017/09/rh6B7BKf67Jk87KW48889kBB87JbUB.jpg","marketprice":"47.00","unit":"套","productprice":"49.00","ishot":"0","isrecommand":"0","isnew":"1","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20237","goodsname":"2017新款发热男士保暖内衣套装 无缝美体V领秋衣秋裤","thumb":"images/sz_yi/1604/2017/09/jUBMn7J9n7AJ4p977AMa7ZRJNj9J06.jpg","marketprice":"59.00","unit":"","productprice":"79.00","ishot":"0","isrecommand":"1","isnew":"1","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20277","goodsname":"夏娜奇男士内裤纯棉宽条纹印花男青年单条盒装中腰平角裤","thumb":"images/sz_yi/1604/2017/09/UHwN7hdh1M1cHc7hr7nW1xMW7CwH7n.jpg","marketprice":"23.00","unit":"件","productprice":"28.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20282","goodsname":"MIBOER 男士棉质口袋中腰平角裤 吸汗透气棉质平角裤性感撞色","thumb":"images/sz_yi/1604/2017/09/rKjZuTFT39z3iv8tu9T8c66p9IJcJT.jpg","marketprice":"23.00","unit":"件","productprice":"30.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20320","goodsname":"2017新款多色款男士平角内裤冰丝无痕纯色弹力锦纶贴边男","thumb":"images/sz_yi/1604/2017/09/q6C98qt63tgmgQKl9OmTOdze9dz8OD.jpg","marketprice":"21.80","unit":"件","productprice":"22.80","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20324","goodsname":"夏娜奇海军蓝条纹情侣内裤性感男士平角女士三角纯棉可爱甜美内裤","thumb":"images/sz_yi/1604/2017/09/r12GZ4Q0q9T4TYtyyyS0J116ppw2WT.jpg","marketprice":"21.80","unit":"件","productprice":"26.80","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20325","goodsname":"夏娜奇品牌心形印花情侣内裤天竹纤维男平角女士三角内裤","thumb":"images/sz_yi/1604/2017/09/yOAPBtH4lTBIFvGZFIupPgG8aaAUoI.jpg","marketprice":"29.00","unit":"件","productprice":"36.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20326","goodsname":"夏娜奇狂野豹纹情侣内裤男士平角女士三角底裤新款","thumb":"images/sz_yi/1604/2017/09/vJz8FC9IfjfCi99ckKDdt7D8wiryk5.jpg","marketprice":"21.80","unit":"件","productprice":"26.80","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20327","goodsname":"夏娜奇情侣内裤纯棉撞色小碎花船锚男士中腰平角女士三角内裤","thumb":"images/sz_yi/1604/2017/09/uqME6NAMNDvMdTwUdTANw8QWuvEumN.jpg","marketprice":"21.80","unit":"件","productprice":"26.80","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20329","goodsname":"夏娜奇2017新品纯棉情侣内裤纯色卡通可爱男女士平角中腰内裤","thumb":"images/sz_yi/1604/2017/09/Lx433mi3fR3KgsWgWRzwWDqM7e9Q4K.jpg","marketprice":"21.80","unit":"件","productprice":"23.80","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20330","goodsname":"夏娜奇2016新款时尚情侣内裤舒适竹纤维纯色运动版男女平角内裤","thumb":"images/1604/2017/09/iqaZN21szDs2g5A5nNZS21BagAhfth.png","marketprice":"21.60","unit":"件","productprice":"39.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20332","goodsname":"夏娜奇2017新款情侣内裤纯棉圆点点男士平角女三角内裤","thumb":"images/sz_yi/1604/2017/09/JkYgtKTykoKg5y577yfy97vOZK0oK1.jpg","marketprice":"22.80","unit":"件","productprice":"23.80","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20333","goodsname":"2017新款情侣内裤纯棉男女士内裤个性条纹男平角女三角","thumb":"images/sz_yi/1604/2017/09/Ojf8fUV0OUOv6v8LVB86u68l9zdVOd.jpg","marketprice":"22.80","unit":"件","productprice":"28.00","ishot":"0","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20482","goodsname":"U凸男士冰丝内裤纯色超薄透气性感透明内裤舒适平角裤夏","thumb":"images/1604/2017/09/G1757wGZ5cC5wrc5D73DllcY6r5D7P.png","marketprice":"20.80","unit":"件","productprice":"22.80","ishot":"1","isrecommand":"0","isnew":"1","isdiscount":"1","issendfree":"0","istime":"0","flag":"0"},{"id":"20483","goodsname":"潮流男士冰丝透气中腰U凸超薄锦纶性感透明平角内裤","thumb":"images/sz_yi/1604/2017/09/g9zo6VZSrrSRFsvfrwVRvsZfOvORJ7.jpg","marketprice":"14.00","unit":"件","productprice":"16.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"1","issendfree":"0","istime":"0","flag":"0"},{"id":"20486","goodsname":"耀婷爆款男士仿真丝睡衣包邮中长款家居睡袍长袖家居服睡服","thumb":"images/sz_yi/1604/2017/09/IxjSbJA1PjjHnWuma0y4S9YbhHs1jN.jpg","marketprice":"85.00","unit":"件","productprice":"109.00","ishot":"0","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0","flag":"0"},{"id":"20490","goodsname":"佐奈尔男士内裤透气舒适吸汗男性莫尔代无痕平角内裤3条礼盒装","thumb":"images/sz_yi/1604/2017/09/P7nBNKJFf7b717GcKTkGf4RbeE7G1Y.jpg","marketprice":"99.00","unit":"盒","productprice":"109.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"1","issendfree":"0","istime":"0","flag":"0"},{"id":"20508","goodsname":"华杰龙花纹阿罗裤男士内裤男生柔软舒适全棉透气B0944","thumb":"images/sz_yi/1604/2017/09/uFFTH6E869p80e50169905e06t6PoT.jpg","marketprice":"32.00","unit":"件","productprice":"40.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"1","issendfree":"0","istime":"0","flag":"0"},{"id":"20510","goodsname":"4条装男士纯棉无痕内裤活性环保印花四角裤U凸个性潮流青年平角裤","thumb":"images/sz_yi/1604/2017/09/rD3wiwUX3igXR5Rc2uDuze2z5cEuuG.jpg","marketprice":"62.00","unit":"件","productprice":"65.00","ishot":"1","isrecommand":"0","isnew":"0","isdiscount":"1","issendfree":"0","istime":"0","flag":"0"}]
         */

        private int curpage;
        private int isload;
        private List<ListBean> list = new ArrayList<>();

        public int getCurpage() {
            return curpage;
        }

        public void setCurpage(int curpage) {
            this.curpage = curpage;
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

        public static class ListBean implements Serializable{
            /**
             * id : 20043
             * goodsname : 4条装南极人男士内裤男 男平角裤纯棉质透气四角底裤衩青年短裤头
             * thumb : images/sz_yi/1604/2017/09/Rpl0V8VEjb2eE3zjbJ3EBjB3bV3RBl.jpg
             * marketprice : 42.90
             * unit : 盒
             * productprice : 56.80
             * ishot : 1
             * isrecommand : 0
             * isnew : 0
             * isdiscount : 1
             * issendfree : 0
             * istime : 0
             * flag : 0
             */

            private String id;
            private String goodsname;
            private String thumb;
            private String marketprice;
            private String unit;
            private String productprice;
            private String ishot;
            private String isrecommand;
            private String isnew;
            private String isdiscount;
            private String issendfree;
            private String istime;
            private String flag;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getProductprice() {
                return productprice;
            }

            public void setProductprice(String productprice) {
                this.productprice = productprice;
            }

            public String getIshot() {
                return ishot;
            }

            public void setIshot(String ishot) {
                this.ishot = ishot;
            }

            public String getIsrecommand() {
                return isrecommand;
            }

            public void setIsrecommand(String isrecommand) {
                this.isrecommand = isrecommand;
            }

            public String getIsnew() {
                return isnew;
            }

            public void setIsnew(String isnew) {
                this.isnew = isnew;
            }

            public String getIsdiscount() {
                return isdiscount;
            }

            public void setIsdiscount(String isdiscount) {
                this.isdiscount = isdiscount;
            }

            public String getIssendfree() {
                return issendfree;
            }

            public void setIssendfree(String issendfree) {
                this.issendfree = issendfree;
            }

            public String getIstime() {
                return istime;
            }

            public void setIstime(String istime) {
                this.istime = istime;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "id='" + id + '\'' +
                        ", goodsname='" + goodsname + '\'' +
                        ", thumb='" + thumb + '\'' +
                        ", marketprice='" + marketprice + '\'' +
                        ", unit='" + unit + '\'' +
                        ", productprice='" + productprice + '\'' +
                        ", ishot='" + ishot + '\'' +
                        ", isrecommand='" + isrecommand + '\'' +
                        ", isnew='" + isnew + '\'' +
                        ", isdiscount='" + isdiscount + '\'' +
                        ", issendfree='" + issendfree + '\'' +
                        ", istime='" + istime + '\'' +
                        ", flag='" + flag + '\'' +
                        '}';
            }
        }
    }
}

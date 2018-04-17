package com.dq.huibao.bean.pingo;

import java.util.List;

/**
 * 商品列表页顶部横向商品推荐
 * Created by d on 2018/4/17.
 */

public class GoodsListTop {
    /**
     * status : 1
     * data : {"isload":0,"list":[{"id":"1","goodsid":"25728","goodsname":"久久丫甜辣薄豆干好吃的豆腐干","typeid":"1","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/12/F5iAUklELCEcrH755EA8klHikLI5e8.jpg","productprice":"3.00","marketprice":"1.50","stock":"147"},{"id":"2","goodsid":"25670","goodsname":"奶油瓜子现金补拍","typeid":"1","distype":"jian","salecount":"0","thumb":"/attachment/images/1604/2017/12/PHHJpQvBFjql1Pu7JJJ7Pb1hFlDJ1c.jpg","productprice":"1.00","marketprice":"1.00","stock":"999399"},{"id":"3","goodsid":"25743","goodsname":"马奇新新马来西亚进口LEXUS力士系列巧克力味涂层夹心饼干200g","typeid":"1","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/12/p4Xt84g4r3S88Z3yx49c0xxy7cxGD8.jpg","productprice":"19.90","marketprice":"12.80","stock":"8054"},{"id":"4","goodsid":"25334","goodsname":"鼠大厨开心果108gX1袋 休闲零食坚果炒货干果","typeid":"1","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/12/j5ipzl926p56pPVLto24Z4PLmmTkP6.jpg","productprice":"19.90","marketprice":"15.80","stock":"9293"},{"id":"5","goodsid":"24371","goodsname":"NPVU打底套头毛衣纯色针织衫喇叭袖修身圆领衣服2017秋装新款3954","typeid":"2","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/10/CLgehGKpqeCjPXmeTh1hCZC1REcmND.jpg","productprice":"222.00","marketprice":"162.00","stock":"444"},{"id":"6","goodsid":"25123","goodsname":"儿童宝宝益智玩具7色雪花片100片积木玩具拼插拼装积木玩具","typeid":"3","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/11/CsqQnXZnJl9Zt968FfsQs7j9EFsFZe.jpg","productprice":"18.00","marketprice":"13.80","stock":"13658"},{"id":"7","goodsid":"20179","goodsname":"大号可爱萌条纹趴趴狗毛绒玩具狗玩偶布娃娃爬爬狗生日礼物送女生","typeid":"3","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/09/FtK9AF92a5Aff4L5Tb4Q2kA99j52Q9.png","productprice":"165.00","marketprice":"105.00","stock":"2400"},{"id":"8","goodsid":"24939","goodsname":"加大版150g 韩国春秋冬季獭兔毛领子毛绒围巾女韩版学生围脖 套口","typeid":"2","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/11/V1e18218M8czZo41Omnff1Z0Gj83n5.jpg","productprice":"29.00","marketprice":"16.80","stock":"75363"},{"id":"9","goodsid":"24544","goodsname":" 厂家直销睡衣女春秋时尚韩版休闲套装家居服女士睡衣长袖新品","typeid":"3","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/10/xKDYtYY3FRzK62z8zfFkID8d8j8Fc8.jpg","productprice":"0.00","marketprice":"108.00","stock":"3991"},{"id":"10","goodsid":"23827","goodsname":"风衣女中长款韩版春秋季2017新款英伦大衣宽松港风过膝chic外套潮","typeid":"2","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/10/N63ZWJO2OH2Z6WzPzwGJO3JK60pPoW.jpg","productprice":"999.00","marketprice":"222.00","stock":"840"}]}
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
         * isload : 0
         * list : [{"id":"1","goodsid":"25728","goodsname":"久久丫甜辣薄豆干好吃的豆腐干","typeid":"1","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/12/F5iAUklELCEcrH755EA8klHikLI5e8.jpg","productprice":"3.00","marketprice":"1.50","stock":"147"},{"id":"2","goodsid":"25670","goodsname":"奶油瓜子现金补拍","typeid":"1","distype":"jian","salecount":"0","thumb":"/attachment/images/1604/2017/12/PHHJpQvBFjql1Pu7JJJ7Pb1hFlDJ1c.jpg","productprice":"1.00","marketprice":"1.00","stock":"999399"},{"id":"3","goodsid":"25743","goodsname":"马奇新新马来西亚进口LEXUS力士系列巧克力味涂层夹心饼干200g","typeid":"1","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/12/p4Xt84g4r3S88Z3yx49c0xxy7cxGD8.jpg","productprice":"19.90","marketprice":"12.80","stock":"8054"},{"id":"4","goodsid":"25334","goodsname":"鼠大厨开心果108gX1袋 休闲零食坚果炒货干果","typeid":"1","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/12/j5ipzl926p56pPVLto24Z4PLmmTkP6.jpg","productprice":"19.90","marketprice":"15.80","stock":"9293"},{"id":"5","goodsid":"24371","goodsname":"NPVU打底套头毛衣纯色针织衫喇叭袖修身圆领衣服2017秋装新款3954","typeid":"2","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/10/CLgehGKpqeCjPXmeTh1hCZC1REcmND.jpg","productprice":"222.00","marketprice":"162.00","stock":"444"},{"id":"6","goodsid":"25123","goodsname":"儿童宝宝益智玩具7色雪花片100片积木玩具拼插拼装积木玩具","typeid":"3","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/11/CsqQnXZnJl9Zt968FfsQs7j9EFsFZe.jpg","productprice":"18.00","marketprice":"13.80","stock":"13658"},{"id":"7","goodsid":"20179","goodsname":"大号可爱萌条纹趴趴狗毛绒玩具狗玩偶布娃娃爬爬狗生日礼物送女生","typeid":"3","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/09/FtK9AF92a5Aff4L5Tb4Q2kA99j52Q9.png","productprice":"165.00","marketprice":"105.00","stock":"2400"},{"id":"8","goodsid":"24939","goodsname":"加大版150g 韩国春秋冬季獭兔毛领子毛绒围巾女韩版学生围脖 套口","typeid":"2","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/11/V1e18218M8czZo41Omnff1Z0Gj83n5.jpg","productprice":"29.00","marketprice":"16.80","stock":"75363"},{"id":"9","goodsid":"24544","goodsname":" 厂家直销睡衣女春秋时尚韩版休闲套装家居服女士睡衣长袖新品","typeid":"3","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/10/xKDYtYY3FRzK62z8zfFkID8d8j8Fc8.jpg","productprice":"0.00","marketprice":"108.00","stock":"3991"},{"id":"10","goodsid":"23827","goodsname":"风衣女中长款韩版春秋季2017新款英伦大衣宽松港风过膝chic外套潮","typeid":"2","distype":"jian","salecount":"0","thumb":"/attachment/images/sz_yi/1604/2017/10/N63ZWJO2OH2Z6WzPzwGJO3JK60pPoW.jpg","productprice":"999.00","marketprice":"222.00","stock":"840"}]
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
             * id : 1
             * goodsid : 25728
             * goodsname : 久久丫甜辣薄豆干好吃的豆腐干
             * typeid : 1
             * distype : jian
             * salecount : 0
             * thumb : /attachment/images/sz_yi/1604/2017/12/F5iAUklELCEcrH755EA8klHikLI5e8.jpg
             * productprice : 3.00
             * marketprice : 1.50
             * stock : 147
             */

            private String id;
            private String goodsid;
            private String goodsname;
            private String typeid;
            private String distype;
            private String salecount;
            private String thumb;
            private String productprice;
            private String marketprice;
            private String stock;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getTypeid() {
                return typeid;
            }

            public void setTypeid(String typeid) {
                this.typeid = typeid;
            }

            public String getDistype() {
                return distype;
            }

            public void setDistype(String distype) {
                this.distype = distype;
            }

            public String getSalecount() {
                return salecount;
            }

            public void setSalecount(String salecount) {
                this.salecount = salecount;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getProductprice() {
                return productprice;
            }

            public void setProductprice(String productprice) {
                this.productprice = productprice;
            }

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }
        }
    }
}

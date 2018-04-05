package com.dq.huibao.bean.xstore;

import java.util.List;

/**
 * 小店自选商品分类
 * Created by d on 2018/4/2.
 */

public class XstoreGoodsType {
    /**
     * status : 1
     * data : [{"id":0,"catename":"全部商品"},{"id":"470","catename":"衬衫"},{"id":"471","catename":"T恤"},{"id":"472","catename":"裙子"},{"id":"473","catename":"套装"},{"id":"474","catename":"内衣"},{"id":"475","catename":"女袜"},{"id":"515","catename":"背心"},{"id":"476","catename":"雪纺衫"},{"id":"516","catename":"T恤"},{"id":"585","catename":"下单立减"},{"id":"477","catename":"旗袍"},{"id":"517","catename":"套装"},{"id":"586","catename":"9.9特卖"},{"id":"642","catename":"洁面"},{"id":"478","catename":"牛仔裤"},{"id":"518","catename":"内衣"},{"id":"587","catename":"惠宝自营"},{"id":"643","catename":"面部护理"},{"id":"479","catename":"毛呢大衣"},{"id":"519","catename":"男袜"},{"id":"588","catename":"限时购"},{"id":"644","catename":"洗发护发"},{"id":"480","catename":"风衣"},{"id":"520","catename":"牛仔裤"},{"id":"589","catename":"批发专区"},{"id":"645","catename":"身体护理"},{"id":"677","catename":"毛巾浴巾"},{"id":"481","catename":"吊带/背心"},{"id":"521","catename":"休闲裤"},{"id":"550","catename":"耳饰"},{"id":"590","catename":"元旦狂欢"},{"id":"646","catename":"口腔护理"},{"id":"678","catename":"四件套"},{"id":"482","catename":"打底裤"},{"id":"522","catename":"短裤"},{"id":"551","catename":"项链"},{"id":"591","catename":"内衣初冬优选"},{"id":"647","catename":"女性护理"},{"id":"679","catename":"太空被"},{"id":"483","catename":"短裤"},{"id":"523","catename":"运动裤"},{"id":"552","catename":"发饰"},{"id":"592","catename":"保暖美美过冬"},{"id":"648","catename":"香水彩妆"},{"id":"680","catename":"凉席"},{"id":"484","catename":"大码女装"},{"id":"524","catename":"大码男装"},{"id":"553","catename":"丝巾"},{"id":"593","catename":"购物送积分"},{"id":"649","catename":"清洁用品"},{"id":"681","catename":"毛毯"},{"id":"485","catename":"中老年女装"},{"id":"525","catename":"中老年男装"},{"id":"554","catename":"披肩"},{"id":"594","catename":"积分抵现"},{"id":"650","catename":"兰蔻专区"},{"id":"682","catename":"枕头"},{"id":"486","catename":"睡衣"},{"id":"526","catename":"睡衣"},{"id":"555","catename":"太阳镜"},{"id":"565","catename":"寝居服饰"},{"id":"575","catename":"T恤"},{"id":"595","catename":"优惠券3元区"},{"id":"651","catename":"雅诗兰黛专区"},{"id":"683","catename":"工艺品"},{"id":"487","catename":"泳衣"},{"id":"527","catename":"泳衣"},{"id":"556","catename":"遮阳伞/雨伞"},{"id":"566","catename":"婴儿围嘴"},{"id":"576","catename":"牛仔裤"},{"id":"596","catename":"优惠券10元区"},{"id":"633","catename":"保护壳套"},{"id":"652","catename":"科颜氏专区"},{"id":"684","catename":"生活日用"},{"id":"488","catename":"孕妇装"},{"id":"496","catename":"厨房用具"},{"id":"504","catename":"旅行箱"},{"id":"528","catename":"秋衣秋裤"},{"id":"536","catename":"凉鞋"},{"id":"557","catename":"遮阳帽"},{"id":"567","catename":"背婴带"},{"id":"577","catename":"短裤"},{"id":"597","catename":"优惠券15元区"},{"id":"605","catename":"烧烤架|炉"},{"id":"619","catename":"养护|清洁"},{"id":"634","catename":"贴膜"},{"id":"653","catename":"迪奥专区"},{"id":"661","catename":"凉鞋"},{"id":"669","catename":"双肩包"},{"id":"685","catename":"地毯"},{"id":"700","catename":"学生文具"},{"id":"489","catename":"家居服"},{"id":"497","catename":"厨房配件"},{"id":"505","catename":"登山包"},{"id":"529","catename":"羽绒服"},{"id":"537","catename":"休闲鞋"},{"id":"558","catename":"口罩"},{"id":"568","catename":"宝宝鞋"},{"id":"578","catename":"儿童套装"},{"id":"598","catename":"优惠券20元区"},{"id":"606","catename":"帐篷/垫子"},{"id":"620","catename":"精品装饰"},{"id":"635","catename":"存储卡"},{"id":"654","catename":"倩碧专区"},{"id":"662","catename":"高跟鞋"},{"id":"670","catename":"斜挎包"},{"id":"686","catename":"抱枕靠垫"},{"id":"693","catename":"钱包"},{"id":"701","catename":"办公设备"},{"id":"490","catename":"秋衣秋裤"},{"id":"498","catename":"淋浴花洒"},{"id":"506","catename":"电脑包"},{"id":"530","catename":"外套"},{"id":"538","catename":"正装鞋"},{"id":"544","catename":"休闲零食"},{"id":"559","catename":"围巾/手套/帽子"},{"id":"569","catename":"餐具|水杯"},{"id":"579","catename":"玩具"},{"id":"599","catename":"外单尾货"},{"id":"607","catename":"望远镜"},{"id":"613","catename":"五金家族"},{"id":"621","catename":"脚垫"},{"id":"627","catename":"沂蒙山特产"},{"id":"636","catename":"耳机"},{"id":"655","catename":"泊泉雅专区"},{"id":"663","catename":"拖鞋/人字拖"},{"id":"671","catename":"手提包"},{"id":"687","catename":"帘艺隔断"},{"id":"694","catename":"双肩包"},{"id":"702","catename":"电脑整机"},{"id":"491","catename":"卫衣"},{"id":"499","catename":"厨卫挂件"},{"id":"507","catename":"腰包/胸包"},{"id":"531","catename":"针织衫/毛衣"},{"id":"539","catename":"帆布鞋"},{"id":"545","catename":"新鲜果蔬"},{"id":"560","catename":"品牌银饰"},{"id":"570","catename":"日常护理"},{"id":"580","catename":"长裤"},{"id":"600","catename":"德泉茗善斋食品"},{"id":"608","catename":"便捷桌椅床"},{"id":"614","catename":"生活电器"},{"id":"622","catename":"座套"},{"id":"628","catename":"休闲娱乐"},{"id":"637","catename":"手机"},{"id":"656","catename":"莱蔻专区"},{"id":"664","catename":"帆布鞋"},{"id":"672","catename":"钱包"},{"id":"688","catename":"桌布/罩件"},{"id":"695","catename":"单肩/斜跨包"},{"id":"703","catename":"电脑配件"},{"id":"492","catename":"羽绒服"},{"id":"500","catename":"厨卫配件"},{"id":"508","catename":"书包"},{"id":"532","catename":"夹克"},{"id":"540","catename":"跑步鞋"},{"id":"546","catename":"酒水饮料"},{"id":"561","catename":"眼镜"},{"id":"571","catename":"奶粉/奶嘴"},{"id":"581","catename":"儿童外套"},{"id":"601","catename":"推广专区"},{"id":"609","catename":"睡袋/吊床"},{"id":"615","catename":"厨房小电"},{"id":"623","catename":"记录仪"},{"id":"629","catename":"教育培训"},{"id":"638","catename":"智能手环"},{"id":"657","catename":"缇丽莎尔专区"},{"id":"665","catename":"女靴"},{"id":"673","catename":"零钱包"},{"id":"689","catename":"沙发垫套/椅垫"},{"id":"696","catename":"商务公文包"},{"id":"704","catename":"外设产品"},{"id":"711","catename":"保温杯"},{"id":"493","catename":"针织衫/毛衣"},{"id":"501","catename":"清洁用品"},{"id":"509","catename":"拉杆包"},{"id":"512","catename":"中医保健"},{"id":"533","catename":"衬衫"},{"id":"541","catename":"板鞋"},{"id":"547","catename":"月饼"},{"id":"562","catename":"手表"},{"id":"572","catename":"尿裤湿巾"},{"id":"582","catename":"儿童羽绒服"},{"id":"602","catename":"零食专区"},{"id":"610","catename":"户外装备"},{"id":"616","catename":"厨卫大电"},{"id":"624","catename":"GPS导航"},{"id":"630","catename":"周边旅游"},{"id":"639","catename":"移动电源"},{"id":"658","catename":"男性洗护"},{"id":"666","catename":"休闲鞋"},{"id":"674","catename":"钥匙包"},{"id":"690","catename":"收纳用品"},{"id":"697","catename":"男士手包"},{"id":"705","catename":"游戏设备"},{"id":"708","catename":"礼品"},{"id":"712","catename":"玻璃杯"},{"id":"715","catename":"布艺沙发"},{"id":"494","catename":"正装女"},{"id":"502","catename":"调味品"},{"id":"510","catename":"相机包"},{"id":"513","catename":"按摩器"},{"id":"534","catename":"正装"},{"id":"542","catename":"拖鞋"},{"id":"548","catename":"农副产品"},{"id":"563","catename":"手链"},{"id":"573","catename":"爬爬垫"},{"id":"583","catename":"内衣裤"},{"id":"603","catename":"校园专区"},{"id":"611","catename":"电车挡风"},{"id":"617","catename":"大家电"},{"id":"625","catename":"轮胎"},{"id":"631","catename":"周边酒店"},{"id":"640","catename":"数码配件"},{"id":"659","catename":"爱润妍专区"},{"id":"667","catename":"雪地靴"},{"id":"675","catename":"卡包"},{"id":"691","catename":"电热毯"},{"id":"698","catename":"钥匙包"},{"id":"706","catename":"网络产品"},{"id":"709","catename":"绿植园艺"},{"id":"713","catename":"整套茶具"},{"id":"716","catename":"懒人沙发"}]
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
         * id : 0
         * catename : 全部商品
         */

        private int id;
        private String catename;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCatename() {
            return catename;
        }

        public void setCatename(String catename) {
            this.catename = catename;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", catename='" + catename + '\'' +
                    '}';
        }
    }
}

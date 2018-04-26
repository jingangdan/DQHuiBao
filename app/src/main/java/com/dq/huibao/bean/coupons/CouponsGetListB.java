package com.dq.huibao.bean.coupons;

import java.util.List;

/**
 * 领卷中心列表
 * Created by d on 2018/4/25.
 */

public class CouponsGetListB {
    /**
     * status : 1
     * data : [{"id":"1","title":"满1000立减100","tid":"1","enough":"1000.00","total":"1000","thumb":"/attachment/admin//20180425/1524625982_1348865100.jpg","timetype":"0","timedays":"3","timestart":"0","timeend":"0","backtype":"0","deduct":"100.00","discount":"0.00","intro":"<html>\r\n<head>\r\n\t<title><\/title>\r\n<\/head>\r\n<body>\r\n<p>test<\/p>\r\n<\/body>\r\n<\/html>\r\n","orderby":"0","usetype":"0","adtime":null,"status":"0"},{"id":"2","title":"满666立减66","tid":"2","enough":"666.00","total":"100","thumb":"/attachment/admin//20180425/1524634801_767024424.jpg","timetype":"0","timedays":"3","timestart":"0","timeend":"0","backtype":"0","deduct":"66.00","discount":"0.00","intro":"<html>\r\n<head>\r\n\t<title><\/title>\r\n<\/head>\r\n<body><\/body>\r\n<\/html>\r\n","orderby":"0","usetype":"1","adtime":null,"status":"0"},{"id":"3","title":"满888七折优惠","tid":"3","enough":"888.00","total":"100","thumb":"/attachment/admin//20180425/1524634781_1543114471.jpg","timetype":"0","timedays":"3","timestart":"0","timeend":"0","backtype":"1","deduct":"0.00","discount":"7.00","intro":"<html>\r\n<head>\r\n\t<title><\/title>\r\n<\/head>\r\n<body>\r\n<p>test<\/p>\r\n<\/body>\r\n<\/html>\r\n","orderby":"0","usetype":"2","adtime":null,"status":"0"},{"id":"4","title":"满1000立减100","tid":"3","enough":"1000.00","total":"100","thumb":"/attachment/admin//20180425/1524634937_1632616299.jpg","timetype":"0","timedays":"3","timestart":"0","timeend":"0","backtype":"0","deduct":"100.00","discount":"0.00","intro":"<html>\r\n<head>\r\n\t<title><\/title>\r\n<\/head>\r\n<body>\r\n<p>2222<\/p>\r\n<\/body>\r\n<\/html>\r\n","orderby":"0","usetype":"2","adtime":null,"status":"0"},{"id":"5","title":"满1000立减100","tid":"3","enough":"1000.00","total":"1000","thumb":"/attachment/admin//20180425/1524633866_1868096599.jpg","timetype":"0","timedays":"3","timestart":"0","timeend":"0","backtype":"0","deduct":"100.00","discount":"0.00","intro":"<html>\r\n<head>\r\n\t<title><\/title>\r\n<\/head>\r\n<body><\/body>\r\n<\/html>\r\n","orderby":"0","usetype":"2","adtime":null,"status":"0"}]
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
         * id : 1
         * title : 满1000立减100
         * tid : 1
         * enough : 1000.00
         * total : 1000
         * thumb : /attachment/admin//20180425/1524625982_1348865100.jpg
         * timetype : 0
         * timedays : 3
         * timestart : 0
         * timeend : 0
         * backtype : 0
         * deduct : 100.00
         * discount : 0.00
         * intro : <html>
         <head>
         <title></title>
         </head>
         <body>
         <p>test</p>
         </body>
         </html>

         * orderby : 0
         * usetype : 0
         * adtime : null
         * status : 0
         */

        private String id;
        private String title;
        private String tid;
        private String enough;
        private String total;
        private String thumb;
        private String timetype;
        private String timedays;
        private String timestart;
        private String timeend;
        private String backtype;
        private String deduct;
        private String discount;
        private String intro;
        private String orderby;
        private String usetype;
        private String adtime;
        private String status;

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

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getEnough() {
            return enough;
        }

        public void setEnough(String enough) {
            this.enough = enough;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getTimetype() {
            return timetype;
        }

        public void setTimetype(String timetype) {
            this.timetype = timetype;
        }

        public String getTimedays() {
            return timedays;
        }

        public void setTimedays(String timedays) {
            this.timedays = timedays;
        }

        public String getTimestart() {
            return timestart;
        }

        public void setTimestart(String timestart) {
            this.timestart = timestart;
        }

        public String getTimeend() {
            return timeend;
        }

        public void setTimeend(String timeend) {
            this.timeend = timeend;
        }

        public String getBacktype() {
            return backtype;
        }

        public void setBacktype(String backtype) {
            this.backtype = backtype;
        }

        public String getDeduct() {
            return deduct;
        }

        public void setDeduct(String deduct) {
            this.deduct = deduct;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getOrderby() {
            return orderby;
        }

        public void setOrderby(String orderby) {
            this.orderby = orderby;
        }

        public String getUsetype() {
            return usetype;
        }

        public void setUsetype(String usetype) {
            this.usetype = usetype;
        }

        public String getAdtime() {
            return adtime;
        }

        public void setAdtime(String adtime) {
            this.adtime = adtime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}

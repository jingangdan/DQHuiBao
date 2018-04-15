package com.dq.huibao.bean.jifen;

import java.util.ArrayList;
import java.util.List;

/**
 * 积分可兑换商品类型
 * Created by d on 2018/4/15.
 */

public class JiFenFuLiType {
    /**
     * status : 1
     * data : [{"id":"1","tname":"礼品","thumb":"","isindex":"0"},{"id":"2","tname":"零食","thumb":"","isindex":"1"},{"id":"3","tname":"新奇","thumb":"","isindex":"0"}]
     */

    private int status;
    private List<DataBean> data = new ArrayList<>();

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
         * tname : 礼品
         * thumb :
         * isindex : 0
         */

        private String id;
        private String tname;
        private String thumb;
        private String isindex;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getIsindex() {
            return isindex;
        }

        public void setIsindex(String isindex) {
            this.isindex = isindex;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", tname='" + tname + '\'' +
                    ", thumb='" + thumb + '\'' +
                    ", isindex='" + isindex + '\'' +
                    '}';
        }
    }
}

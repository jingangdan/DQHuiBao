package com.dq.huibao.bean.pingo;

import java.util.List;

/**
 * 拼go地区选择
 * Created by d on 2018/4/17.
 */

public class PinGoiQuSelect {
    /**
     * status : 1
     * data : {"list":[{"id":"1","regname":"临沂大学城","isshow":"1","orderby":"0"},{"id":"2","regname":"山东医专","isshow":"1","orderby":"0"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * regname : 临沂大学城
             * isshow : 1
             * orderby : 0
             */

            private String id;
            private String regname;
            private String isshow;
            private String orderby;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRegname() {
                return regname;
            }

            public void setRegname(String regname) {
                this.regname = regname;
            }

            public String getIsshow() {
                return isshow;
            }

            public void setIsshow(String isshow) {
                this.isshow = isshow;
            }

            public String getOrderby() {
                return orderby;
            }

            public void setOrderby(String orderby) {
                this.orderby = orderby;
            }
        }
    }
}

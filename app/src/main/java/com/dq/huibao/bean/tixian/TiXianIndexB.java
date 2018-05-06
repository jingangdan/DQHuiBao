package com.dq.huibao.bean.tixian;

import java.util.List;

/**
 * 提现首页
 * Created by d on 2018/5/5.
 */

public class TiXianIndexB {
    /**
     * status : 1
     * data : {"uinfo":{"uid":"21","balance":"9975541.72"},"list":[{"id":"5","uid":"21","type":"2","desc":"666","account":"1866666666","ctime":"1524901626"},{"id":"6","uid":"21","type":"1","desc":"邮政储蓄兰山支行","account":"1556355223645455","ctime":"1524901626"},{"id":"7","uid":"21","type":"1","desc":"邮政储蓄兰山支行","account":"1556355223645455","ctime":"1524901626"},{"id":"8","uid":"21","type":"2","desc":"666","account":"1866666666","ctime":"1524901626"},{"id":"9","uid":"21","type":"2","desc":"666","account":"1866666666","ctime":"1524901626"}]}
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
         * uinfo : {"uid":"21","balance":"9975541.72"}
         * list : [{"id":"5","uid":"21","type":"2","desc":"666","account":"1866666666","ctime":"1524901626"},{"id":"6","uid":"21","type":"1","desc":"邮政储蓄兰山支行","account":"1556355223645455","ctime":"1524901626"},{"id":"7","uid":"21","type":"1","desc":"邮政储蓄兰山支行","account":"1556355223645455","ctime":"1524901626"},{"id":"8","uid":"21","type":"2","desc":"666","account":"1866666666","ctime":"1524901626"},{"id":"9","uid":"21","type":"2","desc":"666","account":"1866666666","ctime":"1524901626"}]
         */

        private UinfoBean uinfo;
        private List<ListBean> list;

        public UinfoBean getUinfo() {
            return uinfo;
        }

        public void setUinfo(UinfoBean uinfo) {
            this.uinfo = uinfo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class UinfoBean {
            /**
             * uid : 21
             * balance : 9975541.72
             */

            private String uid;
            private String balance;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }
        }

        public static class ListBean {
            /**
             * id : 5
             * uid : 21
             * type : 2
             * desc : 666
             * account : 1866666666
             * ctime : 1524901626
             */

            private String id;
            private String uid;
            private String type;
            private String desc;
            private String account;
            private String ctime;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
        }
    }
}

package com.dq.huibao.bean.jifen;

import java.util.List;

/**
 * 积分的使用记录
 * Created by d on 2018/4/15.
 */

public class JiFenUserLogs {
    /**
     * status : 1
     * data : [{"id":"1","uid":"21","type":"score","action_type":"exchange","remark":"积分商城兑换商品","score":"300","balance":"5.00","flag":"del","addtime":"123654789"}]
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
         * uid : 21
         * type : score
         * action_type : exchange
         * remark : 积分商城兑换商品
         * score : 300
         * balance : 5.00
         * flag : del
         * addtime : 123654789
         */

        private String id;
        private String uid;
        private String type;
        private String action_type;
        private String remark;
        private String score;
        private String balance;
        private String flag;
        private String addtime;

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

        public String getAction_type() {
            return action_type;
        }

        public void setAction_type(String action_type) {
            this.action_type = action_type;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }
}

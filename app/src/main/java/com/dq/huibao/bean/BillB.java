package com.dq.huibao.bean;

import java.util.List;

/**
 * 话费充值金额
 * Created by d on 2018/4/26.
 */

public class BillB {
    /**
     * status : 1
     * data : [20,30,50,100,200,300]
     */

    private int status;
    private List<Integer> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}

package com.zl.ttshop.pojo.vo;

import com.zl.ttshop.pojo.po.TbItem;

public class TbItemCustom extends TbItem {

    private String catName;

    private String catStatus;

    public String getCatStatus() {
        return catStatus;
    }

    public void setCatStatus(String catStatus) {
        this.catStatus = catStatus;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}

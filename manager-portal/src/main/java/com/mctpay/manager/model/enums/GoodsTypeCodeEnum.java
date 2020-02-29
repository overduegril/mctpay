package com.mctpay.manager.model.enums;


public enum GoodsTypeCodeEnum {
    A("卡券"),
    B("实物");
    GoodsTypeCodeEnum(String name){
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

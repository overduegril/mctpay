package com.mctpay.manager.model.enums;


public enum GoodsTypeCodeEnum {
    CARD("卡券"),
    PHYSICAL("实物");
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

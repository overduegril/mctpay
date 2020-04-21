package com.mctpay.manager.model.enums;

public enum ShowPictureTypeEnum {
    MERCHAN("商户轮播图"),
    MERCHAN_LOGIN("商户登录轮播图"),
    WALLET("钱包"),
    WALLET_LOGIN("钱包登录轮播图");
    ShowPictureTypeEnum(String name){
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

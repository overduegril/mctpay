package com.mctpay.manager.model.enums;

/**
 * @Author: guodongwei
 * @Description: 文件类型枚举
 * @Date: 2020/3/4 11:45
 */
public enum FileUseTypeEnum {

    SHOP_PHOTO("门头照"),
    BUSINESS_LICENSE("营业执照");
    FileUseTypeEnum(String name){
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

package com.mctpay.merchant.model.enums;

/**
 * @Author: guodongwei
 * @Description: 优惠券类型枚举
 * @Date: 2020/6/1 14:53
 */
public enum CardTypeEnum {

    FULL_FOLD_CARD("满折券"),
    FULL_REDUCE_CARD("满减券");

    CardTypeEnum(String name){
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

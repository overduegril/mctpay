package com.mctpay.merchant.model.enums;

/**
 * @Author: guodongwei
 * @Description: 优惠券优惠类型枚举
 * @Date: 2020/6/1 14:53
 */
public enum ReduceTypeEnum {

    FULL_FOLD("满折"),
    FULL_REDUCE("满减");

    ReduceTypeEnum(String name){
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

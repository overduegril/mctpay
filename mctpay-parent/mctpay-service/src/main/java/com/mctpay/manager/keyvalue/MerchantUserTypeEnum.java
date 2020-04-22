package com.mctpay.manager.keyvalue;

/**
 * @author liqiang
 * @date 2020/4/22 09:35
 * @Description: (what)
 * (why)
 * (how)
 */
public enum MerchantUserTypeEnum implements BaseEnum {
    system(0, "超级管理员"),
    manager(1, "管理员");
    private Integer value;
    private String desc;

    MerchantUserTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    @Override
    public int getValue() {
        return value;
    }
}

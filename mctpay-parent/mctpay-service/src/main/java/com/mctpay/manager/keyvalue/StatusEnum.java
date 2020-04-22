package com.mctpay.manager.keyvalue;

/**
 * @author liqiang
 * @date 2020/4/22 16:37
 * @Description: (what)
 * (why)
 * (how)
 */
public enum  StatusEnum implements BaseEnum {
    frozen(-1, "冻结"),
    ACTIVATION(1, "激活");
    private Integer value;
    private String desc;

    StatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    @Override
    public int getValue() {
        return value;
    }
}


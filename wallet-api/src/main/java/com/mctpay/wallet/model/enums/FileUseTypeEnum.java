package com.mctpay.wallet.model.enums;

/**
 * @Author: guodongwei
 * @Description: 文件类型枚举
 * @Date: 2020/3/4 11:45
 */
public enum FileUseTypeEnum {

    RESERVE("预约");
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

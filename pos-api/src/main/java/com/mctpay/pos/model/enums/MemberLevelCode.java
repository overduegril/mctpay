package com.mctpay.pos.model.enums;

/**
 * @Author: guodongwei
 * @Description: 会员等级枚举
 * @Date: 2020/2/27 9:31
 */
public enum MemberLevelCode {

    PRIMARY("primary", "初级会员"),
    INTERMEDIATE("intermediate", "中级会员"),
    ADVANCED("advanced", "高级会员");

    MemberLevelCode() {
    }

    private String memberLevelCode;
    private String memberLevelName;

    private MemberLevelCode(String memberLevelCode, String memberLevelName) {
        this.memberLevelCode = memberLevelCode;
        this.memberLevelName = memberLevelName;
    }
}

package com.mctpay.manager.model.enums;

import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 会员枚举
 * @Date: 2020/2/27 9:31
 */
public enum MemberCode {

    PRIMARY("primary", "初级会员"),
    INTERMEDIATE("intermediate", "中级会员"),
    ADVANCED("advanced", "高级会员");

    MemberCode() {
    }

    private String memberCode;
    private String memberName;

    private MemberCode(String memberCode, String memberName) {
        this.memberCode = memberCode;
        this.memberName = memberName;
    }
}

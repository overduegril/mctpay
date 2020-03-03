package com.mctpay.manager.model.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 会员dto
 * @Date: 2020/3/1 15:59
 */
@Data
public class MemberDTO {


    @ApiModelProperty(value = "")
    private Long id;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 等级
     */
    @ApiModelProperty(value = "会员等级")
    private String memberLevelName;

    /**
     * 银行卡
     */
    @ApiModelProperty(value = "银行卡")
    private String bankCard;
    /**
     * 信用等级
     */
    @ApiModelProperty(value = "信用等级")
    private String creditLevel;

}

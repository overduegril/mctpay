package com.mctpay.pos.model.param;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: guodongwei
 * @Description: 邮箱验证码参数
 * @Date: 2020/3/2 16:38
 */
@Data
public class EmailCodeParam extends BaseEntity{

    //
    @ApiModelProperty(value = "")
    private Long id;
    //接受邮箱
    @ApiModelProperty(value = "接受邮箱")
    private String toEmail;
    //验证吗
    @ApiModelProperty(value = "验证吗")
    private String code;
    //业务类型
    @ApiModelProperty(value = "业务类型")
    private String businessType;
    //过期时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "过期时间")
    private Date expirationTime;

}

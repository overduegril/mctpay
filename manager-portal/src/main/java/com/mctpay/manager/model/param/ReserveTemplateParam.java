package com.mctpay.manager.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: guodongwei
 * @Description: 预定模板参数
 * @Date: 2020/3/12 10:40
 */
@Data
@ApiModel("预定模板参数")
public class ReserveTemplateParam {

    @ApiModelProperty(value = "模板名字")
    private String templateName;

    @ApiModelProperty(value = "手机号")
    private String phoneNmuber;

    @ApiModelProperty(value = "预约时间")
    private String reserveTime;

    @ApiModelProperty(value = "人数")
    private String userCount;

    @ApiModelProperty(value = "备注")
    private String comment;

    @ApiModelProperty(value = "是否需要定金")
    private Integer needDeposit;

    @ApiModelProperty(value = "定金金额")
    private String depositAmount;

    @ApiModelProperty(value = "是否需要图片")
    private Integer needPicture;

    @ApiModelProperty(value = "其余映射字段")
    private List<Map> dynamicField;

}

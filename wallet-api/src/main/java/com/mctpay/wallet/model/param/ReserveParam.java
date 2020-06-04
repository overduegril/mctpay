package com.mctpay.wallet.model.param;

import cn.hutool.json.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author: guodongwei
 * @Description: 预约参数
 * @Date: 2020/5/31 14:50
 */
@ApiModel()
@Data
public class ReserveParam {

    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "模板名字")
    private String templateName;

    @ApiModelProperty(value = "手机号")
    private Integer phoneNmuber;

    @ApiModelProperty(value = "预约时间(注意到分钟)")
    private Integer reserveTime;

    @ApiModelProperty(value = "人数")
    private Integer userCount;

    @ApiModelProperty(value = "备注")
    private Integer comment;

    @ApiModelProperty(value = "是否需要定金（0不需要，1需要）")
    private Integer needDeposit;

    @ApiModelProperty(value = "定金金额")
    private BigDecimal depositAmount;

    @ApiModelProperty(value = "是否需要图片（0不需要，1需要）")
    private Integer needPicture;

    @ApiModelProperty(value = "其余映射字段key,value模式")
    private Map<String, Object> dynamicFields;

    @ApiModelProperty(value = "其余映射字段(字符类型)", hidden = true)
    private JSONObject dynamicField;

}

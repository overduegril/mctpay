package com.mctpay.wallet.model.param;

import cn.hutool.json.JSONObject;
import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @Author: guodongwei
 * @Description: 预约参数
 * @Date: 2020/5/31 14:50
 */
@ApiModel()
@Data
public class ReserveParam extends BaseEntity {

    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "模板名字")
    private String templateName;

    @ApiModelProperty(value = "手机号")
    private String phoneNmuber;

    @ApiModelProperty(value = "预约人姓名")
    private String name;

    @ApiModelProperty(value = "预约时间(注意到分钟)")
    private Date reserveTime;

    @ApiModelProperty(value = "人数")
    private Integer userCount;

    @ApiModelProperty(value = "备注")
    private String comment;

    @ApiModelProperty(value = "定金金额")
    private BigDecimal depositAmount;

    @ApiModelProperty(value = "交易单号")
    private String tradeNo;

    @ApiModelProperty(value = "其余映射字段key,value模式")
    private Map<String, Object> dynamicFields;

    @ApiModelProperty(value = "其余映射字段(字符类型)", hidden = true)
    private JSONObject dynamicField;

}

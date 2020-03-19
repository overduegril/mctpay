package com.mctpay.manager.model.entity.template;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author dongwei_guo
 * @date 2020-02-29 14:00:37
 */
@Data
@ApiModel(value = "预定模板")
public class ReserveTemplateEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;

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
    private String otherField;

}

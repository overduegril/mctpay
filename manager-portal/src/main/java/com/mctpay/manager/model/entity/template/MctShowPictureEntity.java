package com.mctpay.manager.model.entity.template;

import com.xtpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:00
 */
@Data
@ApiModel(value = "图片展示")
public class MctShowPictureEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;
	/**
	 * 图片用途类型码
	 */
	@ApiModelProperty(value = "图片用途类型码")
	private String useTypeCode;
	/**
	 * 图片用户态名称
	 */
	@ApiModelProperty(value = "图片用户态名称")
	private String useTypeName;
	/**
	 * 图片地址
	 */
	@ApiModelProperty(value = "图片地址")
	private String address;
	/**
	 * sdk类型
	 */
	@ApiModelProperty(value = "sdk类型")
	private String sdkType;

}

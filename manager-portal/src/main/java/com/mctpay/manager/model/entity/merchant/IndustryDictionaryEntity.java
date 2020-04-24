package com.mctpay.manager.model.entity.merchant;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dongwei_guo
 * @date 2020-04-24 09:03:03
 */
@Data
@ApiModel(value = "行业字典")
public class IndustryDictionaryEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "行业")
	private String industry;

	@ApiModelProperty(value = "行业英文")
	private String industryEn;

}

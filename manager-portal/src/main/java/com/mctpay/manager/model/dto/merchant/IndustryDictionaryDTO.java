package com.mctpay.manager.model.dto.merchant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class IndustryDictionaryDTO  implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "")
    private Long id;

    @ApiModelProperty(value = "行业")
    private String industry;

    @ApiModelProperty(value = "行业英文")
    private String industryEn;


}

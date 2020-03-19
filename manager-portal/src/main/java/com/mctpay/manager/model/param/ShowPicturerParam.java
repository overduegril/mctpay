package com.mctpay.manager.model.param;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class ShowPicturerParam extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "", hidden = true)
    private Long id;
    /**
     * 图片用途类型码
     */
    @ApiModelProperty(value = "图片用途类型码")
    private String useTypeCode;
    /**
     * 图片用户态名称
     */
    @ApiModelProperty(value = "图片用户类型名称")
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

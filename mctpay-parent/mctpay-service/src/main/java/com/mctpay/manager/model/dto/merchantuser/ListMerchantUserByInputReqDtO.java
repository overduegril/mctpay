package com.mctpay.manager.model.dto.merchantuser;

import com.mctpay.common.base.model.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liqiang
 * @date 2020/4/22 10:37
 * @Description: (what)
 * (why)
 * (how)
 */
@Data
public class ListMerchantUserByInputReqDtO extends PageParam implements Serializable {
    @ApiModelProperty(value = "关键字")
    private String keyword;
    @ApiModelProperty(value = "商户id")
    private String merchantId;
}

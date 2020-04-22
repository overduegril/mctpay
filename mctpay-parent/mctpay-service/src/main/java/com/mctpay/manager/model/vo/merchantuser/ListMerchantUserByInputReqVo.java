package com.mctpay.manager.model.vo.merchantuser;

import com.mctpay.common.base.model.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liqiang
 * @date 2020/4/22 09:59
 * @Description: (what)
 * (why)
 * (how)
 */
@Data
public class ListMerchantUserByInputReqVo extends PageParam  implements Serializable {
    @ApiModelProperty(value = "关键字")
    private String keyword;
}

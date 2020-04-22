package com.mctpay.manager.model.dto.membergrade;

import com.mctpay.common.base.model.PageParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liqiang
 * @date 2020/4/22 17:06
 * @Description: (what)
 * (why)
 * (how)
 */
@Data
public class FindPageReqDto extends PageParam implements Serializable {
    /**
     * 商户id
     */
    private String merchantId;

}

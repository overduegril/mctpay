package com.mctpay.wallet.model.param;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 商户会员参数
 * @Date: 2020/3/1 15:12
 */
@Data
public class MerchantMemberParam extends BaseEntity {

    /**
     * 商铺得到的会员虚拟ID
     */
    @ApiModelProperty(value = "商铺得到的会员虚拟ID", hidden = true)
    private Long id;
    /**
     * 商铺ID
     */
    @ApiModelProperty(value = "商铺ID")
    private Long merchantId;
    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID", hidden = true)
    private Long memberId;


}

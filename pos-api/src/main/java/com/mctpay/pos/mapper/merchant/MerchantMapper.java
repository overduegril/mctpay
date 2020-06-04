package com.mctpay.pos.mapper.merchant;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.pos.model.entity.merchant.MerchantEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:02
 */
@Repository
public interface MerchantMapper extends BaseMapper<MerchantEntity> {

    /**
     * 获取可以登录的商户
     * @param userName
     * @return
     */
    List<MerchantEntity> listMerchantsByUserName(String userName);

    /**
     * 获取商户会员二维码
     */
    String getMemberQRCode(String merchantId);
}

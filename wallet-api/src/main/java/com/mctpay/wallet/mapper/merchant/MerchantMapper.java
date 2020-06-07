package com.mctpay.wallet.mapper.merchant;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.wallet.model.entity.merchant.MerchantEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:02
 */
@Repository
public interface MerchantMapper extends BaseMapper<MerchantEntity> {

    /**
     * @Description 获取商户集合
     * @Date 22:31  2020/2/28
     **/
    List<MerchantEntity> listMerchantByInput( Map<String,Object> param);

    /**
     * @Description 获取商户集合
     * @Date 22:31  2020/2/28
     **/
    List<MerchantEntity> listAllMerchant();
}

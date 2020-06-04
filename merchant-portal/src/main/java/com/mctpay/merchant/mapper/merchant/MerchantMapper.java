package com.mctpay.merchant.mapper.merchant;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.merchant.model.entity.merchant.MerchantEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:02
 */
@Repository
public interface MerchantMapper extends BaseMapper<MerchantEntity> {

    List<MerchantEntity> listMerchantsByUserName(String userName);

}

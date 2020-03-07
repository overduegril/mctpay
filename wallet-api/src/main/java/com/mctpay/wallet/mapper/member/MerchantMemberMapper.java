package com.mctpay.wallet.mapper.member;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.wallet.model.entity.member.MerchantMemberEntity;
import com.mctpay.wallet.model.param.MerchantMemberParam;
import org.springframework.stereotype.Repository;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:00
 */
@Repository
public interface MerchantMemberMapper extends BaseMapper<MerchantMemberEntity> {

    /**
     * @Description 成为会员
     * @Date 0:49 2020/3/1
     **/
    void insertMember(MerchantMemberParam merchantMemberParam);
}

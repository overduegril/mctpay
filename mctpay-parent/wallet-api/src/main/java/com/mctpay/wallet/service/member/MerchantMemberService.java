package com.mctpay.wallet.service.member;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.wallet.model.param.MerchantMemberParam;

/**
 * @Author: guodongwei
 * @Description:
 * @Date: 2020/3/1 0:33
 */
public interface MerchantMemberService {

    ResponseData<Object> insertMember(MerchantMemberParam merchantMemberParam);

}

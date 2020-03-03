package com.mctpay.wallet.service.member.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.wallet.mapper.member.MerchantMemberMapper;
import com.mctpay.wallet.model.param.MerchantMemberParam;
import com.mctpay.wallet.service.member.MerchantMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: guodongwei
 * @Description: 会员相关业务
 * @Date: 2020/3/1 0:33
 */
@Service
public class MerchantMemberServiceImpl implements MerchantMemberService {

    @Autowired
    private MerchantMemberMapper merchantMemberMapper;

    /**
     * @Description 成为某商家会员
     * @Date 0:40 2020/3/1
     **/
    @Override
    public ResponseData<Object> insertMember(MerchantMemberParam merchantMemberParam) {
        merchantMemberMapper.insertMember(merchantMemberParam);
        return new ResponseData<>().success(null);
    }
}

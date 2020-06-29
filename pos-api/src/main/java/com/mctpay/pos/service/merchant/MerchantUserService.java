package com.mctpay.pos.service.merchant;

import com.mctpay.common.base.model.ResponseData;

/**
 * @Author: chenshubiao
 * @Description: 商户用户
 * @Date: 2020/6/29
 */
public interface MerchantUserService {


    /**
     * 修改密码 根据邮箱或者电话号码
     * @return
     */
    ResponseData updatefindBackPassword(Integer type, String number, String password,String code);
}

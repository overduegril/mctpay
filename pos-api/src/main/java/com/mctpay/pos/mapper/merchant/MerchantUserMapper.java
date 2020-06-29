package com.mctpay.pos.mapper.merchant;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.pos.model.entity.merchant.MerchantUserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: guodongwei
 * @Description: 商户账户mapper
 * @Date: 2020/3/3 16:35
 */
@Repository
public interface MerchantUserMapper extends BaseMapper<MerchantUserEntity> {

    /*
     根据邮箱获取商户用户
     */
    MerchantUserEntity findMerchantUserByEmail(@Param("email") String email);
    /*
        根据电话号码取商户用户
        */
    MerchantUserEntity findMerchantUserByPhone(@Param("phone")  String phone);


    /**
     * @Description 重置密码
     * @Date 23:10 2020/3/3
     **/
    void updatePassword(@Param("password") String password, @Param("id") String id);

}

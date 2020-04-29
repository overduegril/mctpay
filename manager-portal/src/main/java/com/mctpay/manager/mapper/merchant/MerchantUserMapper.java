package com.mctpay.manager.mapper.merchant;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.manager.model.entity.merchant.MerchantUserEntity;
import com.mctpay.manager.model.param.MerchantUserParam;
import com.mctpay.manager.model.param.UserParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 商户账户mapper
 * @Date: 2020/3/3 16:35
 */
@Repository
public interface MerchantUserMapper extends BaseMapper<MerchantUserEntity> {

    /**
     * @Description 注册管理员
     * @Date 20:45 2020/2/24
     **/
    void insertUser(MerchantUserParam merchantUserParam);

    /**
     * @Description 判断邮箱是否被使用
     * @Date 21:07 2020/2/24
     **/
    Integer countEmail(String email);

    /**
     * @Description 判断手机号是否被使用
     * @Date 21:07 2020/2/24
     **/
    Integer countPhoneNumber(String phoneNumber);

    /**
     * @Description 激活冻结用户
     * @Date 21:34 2020/2/25
     **/
    void updateSwitchUser(@Param("userId") String userId, @Param("state") Integer state);

    /**
     * @Description 更新用户昵称
     * @Date 23:10 2020/3/3
     **/
    void updateUserNickName(@Param("nickName") String nickName, @Param("id") String id);

    /**
     * @Description 重置密码
     * @Date 23:10 2020/3/3
     **/
    void updatePassword(@Param("password") String password, @Param("id") String id);

}

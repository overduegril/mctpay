package com.mctpay.manager.mapper.system;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.manager.model.entity.system.UserEntity;
import com.mctpay.manager.model.param.UserParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * @Description 注册管理员
     * @Date 20:45 2020/2/24
     **/
    void insertUser(UserParam userParam);

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
     * @Description 判断用户名是否被使用
     * @Date 21:07 2020/2/24
     **/
    Integer countUserName(String userName);

    /**
     * @Description 激活冻结用户
     * @Date 21:34 2020/2/25
     **/
    void updateSwitchUser(@Param("userId") Long userId,@Param("state") Integer state);

    /**
     * @Description 根据账号获取用户
     * @Date 10:12 2020/2/26
     **/
    UserEntity getByUserName(String userName);
}

package com.mctpay.pos.mapper.system;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.pos.model.entity.system.UserEntity;
import com.mctpay.pos.model.param.UserParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {

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
     * @Description 根据邮箱获取用户
     * @Date 10:12 2020/2/26
     **/
    List<UserEntity>  listByEmail(@Param("email") String email, @Param("limited")  Boolean limited);

    /**
     * @Description 根据手机号获取用户
     * @Date 10:12 2020/2/26
     **/
     List<UserEntity> listByPhone(@Param("phoneNumber") String phoneNumber, @Param("limited")  Boolean limited);

    /**
     * @Description 判断用户名是否被使用
     * @Date 21:07 2020/2/24
     **/
    Integer countUserName(String userName);

    /**
     * @Description 激活冻结用户
     * @Date 21:34 2020/2/25
     **/
    void updateSwitchUser(@Param("userId") Long userId, @Param("state") Integer state);

    /**
     * @Description 分页查询管理员列表
     * @Date 19:51 2020/2/26
     **/
    List<UserEntity> listUser();

    /**
     * @Description 根据输入查询管理员信息
     * @Date 10:30 2020/2/27
     **/
    List<UserEntity> listUserByInput(@Param("inputContent") String inputContent);
}

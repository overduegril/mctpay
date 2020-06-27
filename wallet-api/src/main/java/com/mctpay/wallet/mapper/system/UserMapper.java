package com.mctpay.wallet.mapper.system;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.wallet.model.entity.system.UserEntity;
import com.mctpay.wallet.model.param.UserParam;
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
    void updateSwitchUser(@Param("userId") Long userId, @Param("state") Integer state);

    /**
     * @Description 根据账号获取用户
     * @Date 10:12 2020/2/26
     **/
    UserEntity getByPhoneNumber(String userName);

    /**
     * @Description 根据账号获取用户
     * @Date 10:12 2020/2/26
     **/
    UserEntity getByEmail(String userName);

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

    /**
     * @Description 修改昵称
     * @Date 13:59 2020/5/25
     **/
    void updateNickname(@Param("userId") String userId,@Param("newNickname")  String newNickname);
    /**
     * @Description 修改头像
     * @Date 13:59 2020/5/25
     **/
    void updateHeadpicture(@Param("businessLicenseUrl") String businessLicenseUrl,@Param("userId")   String userId);
    /**
     * @Description 修改密码
     * @Date 15:04 2020/5/25
     **/
    void updatePassword(@Param("newPwd") String newPwd, @Param("userId") String userId);

    /**
     * 通过邮箱更新密码
     * @param newPwd
     * @param email
     */
    void updatePasswordByEmail(@Param("newPwd") String newPwd, @Param("email") String email);

    /**
     * @Description 根据用户ID获取积分
     * @Date 17:39 2020/6/8
     **/
    Integer getUserPointById(String userId);

    /**
     * 根据id修改用户手机号
     * @param phoneNumber
     * @param id
     */
    void updatePhoneNumber(@Param("phoneNumber")String phoneNumber, @Param("id") String id);
}

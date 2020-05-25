package com.mctpay.wallet.service.system;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.wallet.model.dto.system.UserDTO;
import com.mctpay.wallet.model.param.UserParam;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 用户相关接口
 * @Date: 2020/2/24 20:42
 */
public interface UserService {

    /**
     * @Description 注册用户
     * @Date 15:03 2020/3/2
     **/
    ResponseData insertUser(UserParam userParam);

    /**
     * @Description 激活冻结用户
     * @Date 21:30 2020/2/25
     **/
    ResponseData switchUser(Long userId, Integer state);

    /**
     * @Description 分页查询会员
     * @Date 19:45 2020/2/26
     **/
    List<UserDTO> listUser();

    /**
     * @Description 根据输入内容查询会员
     * @Date 10:29 2020/2/27
     **/
    List<UserDTO> listUserByInput(String inputContent);

    /**
     * @Description 验证邮箱是否重复
     * @Date 19:30 2020/3/2
     **/
    Integer countEmail(String email);
    /**
     * @Description修改昵称
     * @Date 13:57 2020/5/25
     **/
    ResponseData updateNickname(String userId, String newNickname);

    /**
     * @Description修改头像
     * @Date 14:19 2020/5/25
     **/
    void updateHeadpicture(String businessLicenseUrl, String userId);
    /**
     * @Description修改密码
     * @Date 14:50 2020/5/25
     **/
    ResponseData updatePassword(String newPassword, String oldPassword,String userId);
}

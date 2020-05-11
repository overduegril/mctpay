package com.mctpay.merchant.service.system;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.merchant.model.dto.system.UserDTO;
import com.mctpay.merchant.model.param.UserParam;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 用户相关接口
 * @Date: 2020/2/24 20:42
 */
public interface UserService {

    /**
     * @Description 修改密码
     * @Date 17:03 2020/3/7
     **/
    void updatePassword(String newPassword);

    /**
     * @Description 注册管理员
     * @Date 20:45 2020/2/24
     **/
    ResponseData insertUser(UserParam userParam);

    /**
     * @Description 激活冻结用户
     * @Date 21:30 2020/2/25
     **/
    ResponseData switchUser(String userId, Integer state);

    /**
     * @Description 根据输入内容查询会员
     * @Date 10:29 2020/2/27
     **/
    List<UserDTO> listUserByInput(String inputContent);

    /**
     * @Description 重置用户密码
     * @Date 17:23 2020/3/7
     **/
    void resetPassword(String userId);

}

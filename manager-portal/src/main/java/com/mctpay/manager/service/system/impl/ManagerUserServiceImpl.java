package com.mctpay.manager.service.system.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.SecureUtils;
import com.mctpay.manager.mapper.system.UserMapper;
import com.mctpay.manager.model.param.UserParam;
import com.mctpay.manager.service.system.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mctpay.common.constants.ErrorCode.EMAIL_HAS_BEEN_USED;
import static com.mctpay.common.constants.ErrorCode.PHONENUM_HAS_BEEN_USED;
import static com.mctpay.common.constants.ErrorCode.USERNAME_HAS_BEEN_USED;

/**
 * @Author: guodongwei
 * @Description: 用户相关
 * @Date: 2020/2/24 20:18
 */
@Service
public class ManagerUserServiceImpl implements ManagerUserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * @Description 注册管理员
     * @Date 20:45 2020/2/24
     **/
    @Override
    public ResponseData insertUser(UserParam userParam) {
        // 验证手机号，邮箱是否重复
        Integer emailCount = userMapper.countEmail(userParam.getEmail());
        if (emailCount != 0) {
            return new ResponseData<>().fail(EMAIL_HAS_BEEN_USED.getCode(), EMAIL_HAS_BEEN_USED.getMessage());
        }
        Integer phoneNumberCount = userMapper.countPhoneNumber(userParam.getPhoneNumber());
        if (phoneNumberCount != 0) {
            return new ResponseData<>().fail(PHONENUM_HAS_BEEN_USED.getCode(), PHONENUM_HAS_BEEN_USED.getMessage());
        }
        Integer userName = userMapper.countUserName(userParam.getUsername());
        if (userName != 0) {
            return new ResponseData<>().fail(USERNAME_HAS_BEEN_USED.getCode(), USERNAME_HAS_BEEN_USED.getMessage());
        }
        userParam.setPassword(SecureUtils.MD5Encrypt(userParam.getPassword()));
        userMapper.insertUser(userParam);
        return new ResponseData().success(null);
    }

    /**
     * @Description 激活冻结用户
     * @Date 21:31 2020/2/25
     **/
    @Override
    public ResponseData switchUser(Long userId, Integer state) {
        userMapper.updateSwitchUser(userId, state);
        return new ResponseData().success(null);
    }
}

package com.mctpay.manager.service.system.impl;

import com.mctpay.manager.mapper.system.ManagerUserMapper;
import com.mctpay.manager.model.param.UserParam;
import com.mctpay.manager.service.system.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: guodongwei
 * @Description: 用户相关
 * @Date: 2020/2/24 20:18
 */
@Service
public class ManagerUserServiceImpl implements ManagerUserService{

    @Autowired
    private ManagerUserMapper managerUserMapper;

    /**
     * @Description 注册管理员
     * @Date 20:45 2020/2/24
     **/
    @Override
    public void insertUser(UserParam userParam) {
        // 验证手机号，邮箱是否重复

        managerUserMapper.insertUser(userParam);
    }
}

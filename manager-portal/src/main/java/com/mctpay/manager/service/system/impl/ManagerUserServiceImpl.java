package com.mctpay.manager.service.system.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.SecureUtils;
import com.mctpay.manager.mapper.system.UserMapper;
import com.mctpay.manager.model.dto.system.UserDTO;
import com.mctpay.manager.model.entity.system.UserEntity;
import com.mctpay.manager.model.param.UserParam;
import com.mctpay.manager.service.system.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mctpay.common.constants.ErrorCode.EMAIL_HAS_BEEN_USED;
import static com.mctpay.common.constants.ErrorCode.PHONENUM_HAS_BEEN_USED;
import static com.mctpay.common.constants.ErrorCode.USERNAME_HAS_BEEN_USED;

/**
 * @Author: guodongwei
 * @Description: 用户相关
 * @Date: 2020/2/24 20:18
 */
@Service
public class ManagerUserServiceImpl implements UserService {

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
    public ResponseData switchUser(String userId, Integer state) {
        userMapper.updateSwitchUser(userId, state);
        return new ResponseData().success(null);
    }

    /**
     * @Description 分页查询会员列表
     * @Date 19:58 2020/2/26
     **/
    @Override
    public List<UserDTO> listUser() {
        List<UserEntity> userEntities = userMapper.listUser();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    /**
     * @Description 根据输入内容查询会员
     * @Date 10:29 2020/2/27
     **/
    @Override
    public List<UserDTO> listUserByInput(String inputContent) {
        List<UserEntity> userEntities = userMapper.listUserByInput(inputContent);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }
}

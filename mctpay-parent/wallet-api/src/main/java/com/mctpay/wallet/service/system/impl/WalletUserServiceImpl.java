package com.mctpay.wallet.service.system.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.SecureUtils;
import com.mctpay.wallet.mapper.point.SummaryPointMapper;
import com.mctpay.wallet.mapper.point.UseabelPointMapper;
import com.mctpay.wallet.mapper.system.UserMapper;
import com.mctpay.wallet.model.dto.system.UserDTO;
import com.mctpay.wallet.model.entity.system.UserEntity;
import com.mctpay.wallet.model.param.SummaryPointParam;
import com.mctpay.wallet.model.param.UseabelPointParam;
import com.mctpay.wallet.model.param.UserParam;
import com.mctpay.wallet.service.system.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mctpay.common.constants.ErrorCode.EMAIL_HAS_BEEN_USED;

/**
 * @Author: guodongwei
 * @Description: 用户相关
 * @Date: 2020/2/24 20:18
 */
@Service
public class WalletUserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SummaryPointMapper summaryPointMapper;

    @Autowired
    private UseabelPointMapper useabelPointMapper;

    /**
     * @Description 注册管理员
     * @Date 20:45 2020/2/24
     **/
    @Override
    @Transactional
    public ResponseData insertUser(UserParam userParam) {
        // 验证邮箱是否重复
        Integer emailCount = countEmail(userParam.getEmail());
        if (emailCount != 0) {
            return new ResponseData<>().fail(EMAIL_HAS_BEEN_USED.getCode(), EMAIL_HAS_BEEN_USED.getMessage());
        }
        userParam.setPassword(SecureUtils.MD5Encrypt(userParam.getPassword()));
        userMapper.insertUser(userParam);
        // 初始化积分设置
        UseabelPointParam useabelPointParam = new UseabelPointParam();
        useabelPointParam.setPoint(0);
        useabelPointParam.setUserId(userParam.getId());
        useabelPointParam.setStatus(1);
        useabelPointParam.setCreateTime(new Date());
        useabelPointParam.setUpdateTime(new Date());
        useabelPointMapper.initUseabelPoint(useabelPointParam);

        SummaryPointParam summaryPointParam = new SummaryPointParam();
        summaryPointParam.setPoint(0);
        summaryPointParam.setUserId(userParam.getId());
        summaryPointParam.setStatus(1);
        summaryPointParam.setCreateTime(new Date());
        summaryPointParam.setUpdateTime(new Date());
        summaryPointMapper.initUserSummaryPoint(summaryPointParam);
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

    /**
     * @Description 验证邮箱是否重复
     * @Date 19:30 2020/3/2
     **/
    public Integer countEmail(String email) {
        // 验证邮箱是否重复
        Integer emailCount = userMapper.countEmail(email);
        return emailCount;
    }
}

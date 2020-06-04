package com.mctpay.merchant.service.system.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.SecureUtils;
import com.mctpay.merchant.mapper.merchant.MerchantMapper;
import com.mctpay.merchant.mapper.system.UserMapper;
import com.mctpay.merchant.model.dto.merchant.AccessibleMerchantDTO;
import com.mctpay.merchant.model.dto.system.LoginedUserDTO;
import com.mctpay.merchant.model.dto.system.UserDTO;
import com.mctpay.merchant.model.entity.merchant.MerchantEntity;
import com.mctpay.merchant.model.entity.system.UserEntity;
import com.mctpay.merchant.model.param.UserParam;
import com.mctpay.merchant.service.system.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.mctpay.common.constants.ErrorCode.USERNAME_HAS_BEEN_USED;

/**
 * @Author: guodongwei
 * @Description: 用户相关
 * @Date: 2020/2/24 20:18
 */
@Service
public class MerchantUserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MerchantMapper merchantMapper;
    /**
     * @Description 注册管理员
     * @Date 20:45 2020/2/24
     **/
    @Override
    public ResponseData insertUser(UserParam userParam) {
        // 验证手机号，邮箱是否重复
//        Integer emailCount = userMapper.countEmail(userParam.getEmail());
//        if (emailCount != 0) {
//            return new ResponseData<>().fail(EMAIL_HAS_BEEN_USED.getCode(), EMAIL_HAS_BEEN_USED.getMessage());
//        }
//        Integer phoneNumberCount = userMapper.countPhoneNumber(userParam.getPhoneNumber());
//        if (phoneNumberCount != 0) {
//            return new ResponseData<>().fail(PHONENUM_HAS_BEEN_USED.getCode(), PHONENUM_HAS_BEEN_USED.getMessage());
//        }
//        Integer userName = userMapper.countUserName(userParam.getUsername());
//        if (userName != 0) {
//            return new ResponseData<>().fail(USERNAME_HAS_BEEN_USED.getCode(), USERNAME_HAS_BEEN_USED.getMessage());
//        }
        userParam.setPassword(SecureUtils.MD5Encrypt("123456"));
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
     * @Description 根据输入内容查询管理员
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
     * @Description 修改密码
     * @Date 17:03 2020/3/7
     **/
    @Override
    public void updatePassword(String newPassword) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userMapper.updatePassword(newPassword, userEntity.getId(), 1);
    }

    /**
     * @Description 重置用户密码
     * @Date 17:23 2020/3/7
     **/
    public void resetPassword(String userId) {
        String password = SecureUtils.MD5Encrypt("123456");
        userMapper.updatePassword(password, userId, 2);
    }

    public List<AccessibleMerchantDTO> listAccounts(String userName) {
        List<MerchantEntity> merchantEntities = merchantMapper.listMerchantsByUserName(userName);
        List<AccessibleMerchantDTO> accessibleMerchantDTOs = new ArrayList<>();
        for (MerchantEntity merchantEntity : merchantEntities) {
            AccessibleMerchantDTO accessibleMerchantDTO = new AccessibleMerchantDTO();
            BeanUtils.copyProperties(merchantEntity, accessibleMerchantDTO);
            accessibleMerchantDTO.setMerchantId(merchantEntity.getId());
            accessibleMerchantDTOs.add(accessibleMerchantDTO);
        }
        return accessibleMerchantDTOs;
    }

    @Override
    public void selectAccount(String id, HttpServletRequest request) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userEntity.setMerchantId(id);
        // 从HttpServletRequest中获取SecurityContextImpl对象
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        // 从SecurityContextImpl中获取Authentication对象
        Authentication authentication = securityContextImpl.getAuthentication();
        // 初始化UsernamePasswordAuthenticationToken实例 ，这里的参数user就是我们要更新的用户信息
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userEntity, authentication.getCredentials());
        auth.setDetails(authentication.getDetails());
        // 重新设置SecurityContextImpl对象的Authentication
//         securityContextImpl.setAuthentication(auth);

    }
}

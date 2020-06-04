package com.mctpay.pos.service.system.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.pos.mapper.merchant.MerchantMapper;
import com.mctpay.pos.mapper.system.UserMapper;
import com.mctpay.pos.model.dto.system.UserDTO;
import com.mctpay.pos.model.dto.system.AccessibleMerchantDTO;
import com.mctpay.pos.model.entity.merchant.MerchantEntity;
import com.mctpay.pos.model.entity.system.UserEntity;
import com.mctpay.pos.service.system.UserService;
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

/**
 * @Author: guodongwei
 * @Description: 用户相关
 * @Date: 2020/2/24 20:18
 */
@Service
public class PosUserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MerchantMapper merchantMapper;

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

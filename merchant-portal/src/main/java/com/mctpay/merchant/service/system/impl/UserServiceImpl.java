package com.mctpay.merchant.service.system.impl;

import com.mctpay.merchant.mapper.system.RoleMapper;
import com.mctpay.merchant.mapper.system.UserMapper;
import com.mctpay.merchant.model.entity.system.RoleEntity;
import com.mctpay.merchant.model.entity.system.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 用户权限密码设置
 * @Date: 2020/2/26 10:03
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userMapper.getByUserName(userName);
        // 验证账户为username的用户是否存在
        if (null == userEntity){
            throw new UsernameNotFoundException("username:  " + userName + "is not exist!");
        }
        List<RoleEntity> roles = roleMapper.getByUserId(userEntity.getId());
        userEntity.setRoles(roles);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        // 返回认证用户
        return userEntity;
    }

}

package com.mctpay.pos.service.system.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.mctpay.pos.mapper.system.RoleMapper;
import com.mctpay.pos.mapper.system.UserMapper;
import com.mctpay.pos.model.entity.system.RoleEntity;
import com.mctpay.pos.model.entity.system.UserEntity;
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
        List<UserEntity> userEntities = userMapper.listByEmail(userName, false);
        if (CollectionUtil.isEmpty(userEntities)) {
            userEntities = userMapper.listByPhone(userName, false);
        }

        // 验证账户为username的用户是否存在
        if (CollectionUtil.isEmpty(userEntities)){
            throw new UsernameNotFoundException("username:  " + userName + "is not exist!");
        }
        UserEntity userEntity = userEntities.get(0);
        List<RoleEntity> roles = roleMapper.getByUserId(userEntity.getId());
        userEntity.setRoles(roles);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        // 返回认证用户
        return userEntity;
    }

}

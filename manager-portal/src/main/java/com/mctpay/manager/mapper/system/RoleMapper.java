package com.mctpay.manager.mapper.system;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.manager.model.entity.system.RoleEntity;
import com.mctpay.manager.model.param.RoleParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:22:59
 */
@Repository
public interface RoleMapper extends BaseMapper<RoleEntity> {

    /**
     * @Description 获取用户角色
     * @Date 10:46 2020/2/26
     **/
    List<RoleEntity> getByUserId(String userId);

    /**
     * @Description 根据角色名获取
     * @Date 10:46 2020/2/26
     **/
    RoleEntity getByRoleName(String roleName);

    /**
     * @Description 创建用户角色
     * @Date 10:46 2020/2/26
     **/
    void insertRole(RoleParam roleParam);
}

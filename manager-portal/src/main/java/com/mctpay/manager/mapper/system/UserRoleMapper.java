package com.mctpay.manager.mapper.system;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.manager.model.entity.system.UserRoleEntity;
import com.mctpay.manager.model.param.UserRoleParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:00
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

    /**
     * @Description 插入用户角色关系
     * @Date 12:19 2020/5/19
     **/
    void insert(UserRoleParam userRoleParam);

}

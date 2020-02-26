package com.mctpay.manager.mapper.system;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.manager.model.entity.system.RoleEntity;
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
    List<RoleEntity> getByUserId(Long userId);
}

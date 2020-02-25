package com.mctpay.manager.mapper.system;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.manager.model.entity.system.ManagerUserEntity;
import com.mctpay.manager.model.param.UserParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Repository
public interface ManagerUserMapper extends BaseMapper<ManagerUserEntity> {

    /**
     * @Description 注册管理员
     * @Date 20:45 2020/2/24
     **/
    void insertUser(UserParam userParam);

    /**
     * @Description 判断邮箱是否被使用
     * @Date 21:07 2020/2/24
     **/
    Integer countEmail(String email);
}

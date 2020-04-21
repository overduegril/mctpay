package com.mctpay.manager.mapper.member;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.manager.model.entity.member.MemberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:02
 */
@Repository
public interface MemberMapper extends BaseMapper<MemberEntity> {

    /**
     * @Description 查询会员列表
     * @Date 15:44 2020/3/1
     **/
    List<MemberEntity> listMemberByInput(@Param("inputContent") String inputContent);
}


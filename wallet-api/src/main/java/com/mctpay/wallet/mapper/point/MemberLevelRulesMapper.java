package com.mctpay.wallet.mapper.point;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.wallet.model.entity.point.MemberLevelRulesEntity;
import com.mctpay.wallet.model.param.MemberLevelRulesParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @Date 22:57 2020/05/28
 */
@Repository
public interface MemberLevelRulesMapper extends BaseMapper<MemberLevelRulesEntity> {

    /**
     * @Description 积分等级设置
     * @Date 22:57 2020/05/28
     **/
    List<MemberLevelRulesEntity> listMemberLevelRules();

    /**
     * @Description 根据id获取最低会员所需积分
     * @Date 19:20 2020/6/8
     **/
    Integer getPointByMemberCode(String memberCode);
}

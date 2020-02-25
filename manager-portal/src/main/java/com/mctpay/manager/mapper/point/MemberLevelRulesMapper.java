package com.mctpay.manager.mapper.point;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.manager.model.entity.point.MemberLevelRulesEntity;
import org.springframework.stereotype.Repository;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Repository
public interface MemberLevelRulesMapper extends BaseMapper<MemberLevelRulesEntity> {

    /**
     * @Description 积分等级设置
     * @Date 15:49 2020/2/24
     **/
    MemberLevelRulesEntity listMemberLevelRules();
}

package com.mctpay.manager.service.point.impl;

import com.mctpay.common.config.UidGeneratorConfig;
import com.mctpay.manager.mapper.point.MemberLevelRulesMapper;
import com.mctpay.manager.model.dto.point.MemberLevelRulesDTO;
import com.mctpay.manager.model.entity.point.MemberLevelRulesEntity;
import com.mctpay.manager.service.point.MemberLevelRulesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: guodongwei
 * @Description: 积分等级设置业务
 * @Date: 2020/2/24 15:55
 */
@Service
public class MemberLevelRulesServiceImpl implements MemberLevelRulesService {

    @Autowired
    private MemberLevelRulesMapper memberLevelRulesMapper;

    /**
     * @Description 积分等级设置
     * @Date 15:49 2020/2/24
     **/
    @Override
    public MemberLevelRulesDTO listMemberLevelRules() {
        MemberLevelRulesEntity memberLevelRulesEntity = memberLevelRulesMapper.listMemberLevelRules();
        MemberLevelRulesDTO memberLevelRulesDTO = new MemberLevelRulesDTO();
        if (memberLevelRulesEntity != null) {
            BeanUtils.copyProperties(memberLevelRulesEntity, memberLevelRulesDTO);
        }
        return memberLevelRulesDTO;
    }
}

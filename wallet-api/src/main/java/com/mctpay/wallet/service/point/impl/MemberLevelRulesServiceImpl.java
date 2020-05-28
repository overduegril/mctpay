package com.mctpay.wallet.service.point.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.wallet.mapper.point.MemberLevelRulesMapper;
import com.mctpay.wallet.model.dto.point.MemberLevelRulesDTO;
import com.mctpay.wallet.model.entity.point.MemberLevelRulesEntity;
import com.mctpay.wallet.model.param.MemberLevelRulesParam;
import com.mctpay.wallet.service.point.MemberLevelRulesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mctpay.common.constants.ErrorCode.LEVELNAME_HAS_BEEN_USED;
import static com.mctpay.common.constants.ErrorCode.POINT_HAS_BEEN_USED;

/**
 * @Author: guodongwei
 * @Description: 积分等级设置业务
 * @Date: 22::56 2020/05/28
 */
@Service
public class MemberLevelRulesServiceImpl implements MemberLevelRulesService {

    @Autowired
    private MemberLevelRulesMapper memberLevelRulesMapper;

    /**
     * @Description 积分等级设置
     * @Date 22:57 2020/05/28
     **/
    @Override
    public List<MemberLevelRulesDTO> listMemberLevelRules() {
        List<MemberLevelRulesEntity> memberLevelRulesEntitys = memberLevelRulesMapper.listMemberLevelRules();
        List<MemberLevelRulesDTO> memberLevelRulesDTOs = new ArrayList<>();
        for (MemberLevelRulesEntity memberLevelRulesEntity : memberLevelRulesEntitys) {
            MemberLevelRulesDTO memberLevelRulesDTO = new MemberLevelRulesDTO();
            BeanUtils.copyProperties(memberLevelRulesEntity, memberLevelRulesDTO);
            memberLevelRulesDTOs.add(memberLevelRulesDTO);
        }
        return memberLevelRulesDTOs;
    }


}

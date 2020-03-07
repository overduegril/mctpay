package com.mctpay.manager.service.point.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.mapper.point.MemberLevelRulesMapper;
import com.mctpay.manager.model.dto.point.MemberLevelRulesDTO;
import com.mctpay.manager.model.entity.point.MemberLevelRulesEntity;
import com.mctpay.manager.model.param.MemberLevelRulesParam;
import com.mctpay.manager.service.point.MemberLevelRulesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mctpay.common.constants.ErrorCode.LEVELNAME_HAS_BEEN_USED;
import static com.mctpay.common.constants.ErrorCode.POINT_HAS_BEEN_USED;

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

    /**
     * @Description 插入会员等级积分设置
     * @Date 10:36 2020/3/2
     **/
    @Override
    public ResponseData<Object> insertMemberLevelRules(MemberLevelRulesParam memberLevelRulesParam) {
        // 判断该积分以及等级名称是否存在
        Integer nameCount = memberLevelRulesMapper.countMemberLevelName(memberLevelRulesParam.getMemberLevelName());
        if (nameCount != 0) {
            return
            new ResponseData<>().fail(LEVELNAME_HAS_BEEN_USED.getCode(), LEVELNAME_HAS_BEEN_USED.getMessage());
        }
        Integer pointCount = memberLevelRulesMapper.countPoint(memberLevelRulesParam.getPoint());
        if (pointCount != 0) {
            return
            new ResponseData<>().fail(POINT_HAS_BEEN_USED.getCode(), POINT_HAS_BEEN_USED.getMessage());
        }
        memberLevelRulesMapper.insertMemberLevelRules(memberLevelRulesParam);
        return new ResponseData<>().success(null);
    }

    /**
     * @Description 冻结，激活会员积分等级设置
     * @Date 11:12 2020/3/2
     **/
    @Override
    public void switchMemberLevelRules(Long id, Integer state) {
        memberLevelRulesMapper.switchMemberLevelRules(id, state);
    }
}

package com.mctpay.merchant.service.point.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.merchant.mapper.point.MemberLevelRulesMapper;
import com.mctpay.merchant.model.dto.point.MemberLevelRulesDTO;
import com.mctpay.merchant.model.entity.point.MemberLevelRulesEntity;
import com.mctpay.merchant.model.entity.system.UserEntity;
import com.mctpay.merchant.model.param.MemberLevelRulesParam;
import com.mctpay.merchant.service.point.MemberLevelRulesService;
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
    public List<MemberLevelRulesDTO> listMemberLevelRules(String merchantId) {
        List<MemberLevelRulesEntity> memberLevelRulesEntitys = memberLevelRulesMapper.listMemberLevelRules(merchantId);
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
        Integer nameCount = memberLevelRulesMapper.countMemberLevelName(memberLevelRulesParam.getMemberLevelName(), memberLevelRulesParam.getMerchantId());
        if (nameCount != 0) {
            return new ResponseData<>().fail(LEVELNAME_HAS_BEEN_USED.getCode(), LEVELNAME_HAS_BEEN_USED.getMessage());
        }
        Integer pointCount = memberLevelRulesMapper.countPoint(memberLevelRulesParam.getPoint(), memberLevelRulesParam.getMerchantId());
        if (pointCount != 0) {
            return new ResponseData<>().fail(POINT_HAS_BEEN_USED.getCode(), POINT_HAS_BEEN_USED.getMessage());
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
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        memberLevelRulesMapper.switchMemberLevelRules(id, state, userEntity.getMerchantId());
    }

    /**
     * @Description 更新积分等级规则
     * @Date 19:37 2020/3/23
     **/
    @Override
    public ResponseData updateMemberLevelRules(MemberLevelRulesParam memberLevelRulesParam) {
        // 判断该积分以及等级名称是否存在
        Integer nameCount = memberLevelRulesMapper.countMemberLevelName(memberLevelRulesParam.getMemberLevelName(), memberLevelRulesParam.getMerchantId());
        if (nameCount != 0) {
            return new ResponseData<>().fail(LEVELNAME_HAS_BEEN_USED.getCode(), LEVELNAME_HAS_BEEN_USED.getMessage());
        }
        Integer pointCount = memberLevelRulesMapper.countPoint(memberLevelRulesParam.getPoint(), memberLevelRulesParam.getMerchantId());
        if (pointCount != 0) {
            return new ResponseData<>().fail(POINT_HAS_BEEN_USED.getCode(), POINT_HAS_BEEN_USED.getMessage());
        }
        memberLevelRulesMapper.updateMemberLevelRules(memberLevelRulesParam);
        return new ResponseData().success(null);
    }
}

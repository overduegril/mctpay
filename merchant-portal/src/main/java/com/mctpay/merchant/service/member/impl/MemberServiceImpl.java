package com.mctpay.merchant.service.member.impl;

import com.mctpay.merchant.mapper.member.MemberMapper;
import com.mctpay.merchant.mapper.point.MemberLevelRulesMapper;
import com.mctpay.merchant.model.dto.member.MemberDTO;
import com.mctpay.merchant.model.dto.member.MemberLevelDTO;
import com.mctpay.merchant.model.entity.member.MemberEntity;
import com.mctpay.merchant.model.entity.point.MemberLevelRulesEntity;
import com.mctpay.merchant.model.entity.system.UserEntity;
import com.mctpay.merchant.service.member.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 会员业务
 * @Date: 2020/5/11 9:39
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberLevelRulesMapper memberLevelRulesMapper;
    /**
     * @Description 查询会员列表
     * @Date 15:44 2020/3/1
     **/
    @Override
    public List<MemberDTO> listMember(String inputContent) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<MemberEntity> memberEntities = memberMapper.listMemberByInput(inputContent, userEntity.getMerchantId());
        List<MemberDTO> memberDTOs = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntities) {
            // 判断会员所属等级
            String memberLevel = memberLevelRulesMapper.getLevelByPoint(memberEntity.getPoint(), userEntity.getMerchantId());
            // 如果查询不到。
            MemberDTO memberDTO = new MemberDTO();
            BeanUtils.copyProperties(memberEntity, memberDTO);
            memberDTO.setMemberLevelName(memberLevel);
            memberDTOs.add(memberDTO);
        }
        return memberDTOs;
    }

    @Override
    public List<MemberLevelDTO> listMemberLevel(String merchantId) {
        List<MemberLevelRulesEntity> memberLevelRulesEntities = memberLevelRulesMapper.listMemberLevel(merchantId);
        List<MemberLevelDTO> memberLevelDTOs = new ArrayList<>();
        for (MemberLevelRulesEntity memberLevelRulesEntity : memberLevelRulesEntities) {
            MemberLevelDTO memberLevelDTO = new MemberLevelDTO();
            BeanUtils.copyProperties(memberLevelRulesEntity, memberLevelDTO);
            memberLevelDTOs.add(memberLevelDTO);
        }
        return memberLevelDTOs;
    }

}

package com.mctpay.manager.service.member.impl;

import com.mctpay.manager.mapper.member.MemberMapper;
import com.mctpay.manager.mapper.point.MemberLevelRulesMapper;
import com.mctpay.manager.model.dto.member.MemberDTO;
import com.mctpay.manager.model.entity.member.MemberEntity;
import com.mctpay.manager.service.member.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 会员业务
 * @Date: 2020/3/1 16:02
 */
@Service
public class MemberServiceimpl implements MemberService {

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
        List<MemberEntity> memberEntities = memberMapper.listMemberByInput(inputContent);
        List<MemberDTO> memberDTOs = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntities) {
            // 判断会员所属等级
            String memberLevel = memberLevelRulesMapper.getLevelByPoint(memberEntity.getPoint());
            MemberDTO memberDTO = new MemberDTO();
            BeanUtils.copyProperties(memberEntity, memberDTO);
            memberDTO.setMemberLevelName(memberLevel);
            memberDTOs.add(memberDTO);
        }
        return memberDTOs;
    }
}

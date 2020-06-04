package com.mctpay.merchant.service.member;

import com.mctpay.merchant.model.dto.member.MemberDTO;
import com.mctpay.merchant.model.dto.member.MemberLevelDTO;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 会员业务接口
 * @Date: 2020/3/1 16:01
 */
public interface MemberService {

    /**
     * @Description 查询会员列表
     * @Date 15:44 2020/3/1
     **/
    List<MemberDTO> listMember(String inputContent);

    /**
     * @Description 获取会员等级列表
     * @Date 15:00 2020/6/2
     **/
    List<MemberLevelDTO> listMemberLevel(String merchantId);

}

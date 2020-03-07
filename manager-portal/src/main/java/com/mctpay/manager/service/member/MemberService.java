package com.mctpay.manager.service.member;

import com.mctpay.manager.model.dto.member.MemberDTO;

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

}

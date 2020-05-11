package com.mctpay.merchant.controller.member;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.merchant.model.dto.member.MemberDTO;
import com.mctpay.merchant.service.member.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: ${description}
 * @Date: 2020/3/1 16:00
 */
@Api(value = "会员相关", tags = "会员")
@RestController
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "分页查询会员", notes = "分页查询会员",  httpMethod = "POST", consumes = "application/json")
    @PostMapping(value = "/listMemberByInput", consumes = "application/json")
    public ResponseData<List<MemberDTO>> listMemberByInput(String inputContent, String merchantId, @RequestBody PageParam pageParam){
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        }
        List<MemberDTO> memberDTOs = memberService.listMember(inputContent, merchantId);
        return new ResponsePageInfo<List<MemberDTO>>().success(memberDTOs, pageInfo);
    }

}

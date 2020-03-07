package com.mctpay.wallet.controller.member;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.wallet.model.entity.system.UserEntity;
import com.mctpay.wallet.model.param.MerchantMemberParam;
import com.mctpay.wallet.service.member.MerchantMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: guodongwei
 * @Description: 商户管理员控制层
 * @Date: 2020/3/1 15:20
 */
@Api(value = "商户会员", tags = "会员")
@RestController
@RequestMapping("merchant-member")
public class MerchantMemberController {

    @Autowired
    private MerchantMemberService merchantMemberService;

    @ApiOperation(value = "扫码成为会员", notes = "扫码成为会员", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/insertMember")
    public ResponseData insertMember(@RequestBody MerchantMemberParam merchantMemberParam) {
        String id = UIdUtils.getUid().toString();
        merchantMemberParam.setId(id);
        merchantMemberParam.setStatus(1);
        merchantMemberParam.setCreateTime(new Date());
        merchantMemberParam.setUpdateTime(new Date());
        // 获取此时登陆的用户ID作为真实会员ID
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        merchantMemberParam.setMemberId(userEntity.getId());
        return merchantMemberService.insertMember(merchantMemberParam);
    }

}

package com.mctpay.wallet.controller.point;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.wallet.model.dto.point.MemberLevelRulesDTO;
import com.mctpay.wallet.model.entity.system.UserEntity;
import com.mctpay.wallet.service.point.MemberLevelRulesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 积分等级设置控制层
 * @Date: 2020/2/24 16:02
 */
@Api(value = "积分等级设置", tags = "积分")
@RestController
@RequestMapping("member-level-rules")
public class MemberLevelRulesController {

    @Autowired
    private MemberLevelRulesService memberLevelRulesService;

    @ApiOperation(value = "查询积分等级设置(未分页)", notes = "查询积分等级设置", httpMethod = "GET")
    @RequestMapping("/listMemberLevelRules")
    public ResponseData<List<MemberLevelRulesDTO>> listMemberLevelRules() {
        List<MemberLevelRulesDTO> memberLevelRulesDTO = memberLevelRulesService.listMemberLevelRules();
        ResponseData responseData = new ResponseData();
        return responseData.success(memberLevelRulesDTO);
    }
}

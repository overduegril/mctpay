package com.mctpay.manager.controller.point;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.model.dto.point.MemberLevelRulesDTO;
import com.mctpay.manager.service.point.MemberLevelRulesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "积分等级设置", notes = "积分等级设置", httpMethod = "GET")
    @RequestMapping("/listMemberLevelRules")
    public ResponseData<MemberLevelRulesDTO> listMemberLevelRules() {
        MemberLevelRulesDTO memberLevelRulesDTO = memberLevelRulesService.listMemberLevelRules();
        ResponseData responseData = new ResponseData();
        return responseData.success(memberLevelRulesDTO);
    }

}

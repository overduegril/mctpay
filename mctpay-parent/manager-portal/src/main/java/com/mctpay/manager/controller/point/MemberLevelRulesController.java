package com.mctpay.manager.controller.point;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.model.dto.point.MemberLevelRulesDTO;
import com.mctpay.manager.model.entity.system.UserEntity;
import com.mctpay.manager.model.param.MemberLevelRulesParam;
import com.mctpay.manager.service.point.MemberLevelRulesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    // @PreAuthorize("hasRole('MANAGER')")
    @ApiOperation(value = "查询积分等级设置(未分页)", notes = "查询积分等级设置", httpMethod = "GET")
    @RequestMapping("/listMemberLevelRules")
    public ResponseData<List<MemberLevelRulesDTO>> listMemberLevelRules() {
        List<MemberLevelRulesDTO> memberLevelRulesDTO = memberLevelRulesService.listMemberLevelRules();
        ResponseData responseData = new ResponseData();
        return responseData.success(memberLevelRulesDTO);
    }

    @ApiOperation(value = "创建积分等级规则", notes = "创建积分等级规则", httpMethod = "POST")
    @RequestMapping("/insertMemberLevelRules")
    public ResponseData insertMemberLevelRules(@RequestBody MemberLevelRulesParam memberLevelRulesParam) {
        memberLevelRulesParam.setStatus(1);
        memberLevelRulesParam.setCreateTime(new Date());
        memberLevelRulesParam.setUpdateTime(new Date());
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        memberLevelRulesParam.setLastOperator(userEntity.getUsername());
        ResponseData<Object> responseData = memberLevelRulesService.insertMemberLevelRules(memberLevelRulesParam);
        return responseData;
    }

    @ApiOperation(value = "修改积分等级规则", notes = "修改积分等级规则", httpMethod = "POST")
    @RequestMapping("/updateMemberLevelRules")
    public ResponseData updateMemberLevelRules(@RequestBody MemberLevelRulesParam memberLevelRulesParam) {
        memberLevelRulesParam.setUpdateTime(new Date());
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        memberLevelRulesParam.setLastOperator(userEntity.getUsername());
        ResponseData responseData = memberLevelRulesService.updateMemberLevelRules(memberLevelRulesParam);
        return responseData;
    }

    @ApiOperation(value = "冻结，激活会员积分等级设置", notes = "冻结，激活会员积分等级设置", httpMethod = "POST")
    @RequestMapping("/switchMemberLevelRules")
    public ResponseData switchMemberLevelRules(@RequestParam Long id, @RequestParam Integer state) {
        memberLevelRulesService.switchMemberLevelRules(id, state);
        ResponseData responseData = new ResponseData();
        return responseData.success(null);
    }
}

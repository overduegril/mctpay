package com.mctpay.manager.controller.point;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.model.dto.point.ManagerGiftDTO;
import com.mctpay.manager.model.dto.point.ManagerMemberLevelRulesDTO;
import com.mctpay.manager.service.point.ManagerMemberLevelRulesService;
import com.mctpay.manager.service.point.impl.ManagerGiftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guodongwei
 * @Description: 礼品控制层
 * @Date: 2020/2/24 10:32
 */
@Api(value = "兑换礼物", tags = "积分")
@RestController
@RequestMapping("manager-gift")
public class ManagerGiftController {

    @Autowired
    private ManagerGiftService managerGiftService;

}

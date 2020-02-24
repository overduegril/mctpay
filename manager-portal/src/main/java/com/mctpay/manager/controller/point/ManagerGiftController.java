package com.mctpay.manager.controller.point;

import com.mctpay.manager.service.point.impl.ManagerGiftService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

package com.mctpay.wallet.controller.template;

import com.mctpay.wallet.model.param.ReserveParam;
import com.mctpay.wallet.service.template.ReserveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guodongwei
 * @Description: 预约控制
 * @Date: 2020/5/30 10:03
 */
@Api(value = "预约", tags = "预约")
@RestController
@RequestMapping("reserve")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    /**
     * @Description 分页查询商户模板
     * @Date 10:56 2020/04/28
     **/
    @ApiOperation(value = "预约", notes = "预约", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/reserve")
    public void reserve(@RequestBody ReserveParam reserveParam) {
        reserveService.reserve(reserveParam);
    }

}

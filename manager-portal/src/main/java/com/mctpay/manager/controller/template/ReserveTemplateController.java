package com.mctpay.manager.controller.template;

import com.mctpay.manager.model.param.ReserveTemplateParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guodongwei
 * @Description: 预定模板控制层
 * @Date: 2020/3/12 10:34
 */
@Api(value = "预定模板", tags = "预定模板")
@RestController
@RequestMapping("reserve-template")
public class ReserveTemplateController {

    /**
     * @Description 插入模板
     * @Date 10:39 2020/3/12
     **/
    @ApiOperation(value = "插入模板", notes = "插入模板", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/insertReserveTemplate")
    public void insertReserveTemplate(@RequestBody ReserveTemplateParam reserveTemplateParam) {
        // TODO 完成list集合拆解

    }

}

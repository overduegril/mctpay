package com.mctpay.manager.controller.template;

import cn.hutool.json.JSONUtil;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.model.param.ReserveTemplateParam;
import com.mctpay.manager.service.template.ReserveTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: guodongwei
 * @Description: 预定模板控制层
 * @Date: 2020/3/12 10:34
 */
@Api(value = "预定模板", tags = "预定模板")
@RestController
@RequestMapping("reserve-template")
public class ReserveTemplateController {

    @Autowired
    private ReserveTemplateService reserveTemplateService;

    /**
     * @Description 插入模板
     * @Date 10:39 2020/3/12
     **/
    @ApiOperation(value = "插入模板", notes = "插入模板", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/insertReserveTemplate")
    public ResponseData insertReserveTemplate(@RequestBody ReserveTemplateParam reserveTemplateParam) {
        // 完成list集合拆解
        reserveTemplateParam.setStatus(1);
        reserveTemplateParam.setCreateTime(new Date());
        reserveTemplateParam.setUpdateTime(new Date());
        if (reserveTemplateParam.getDynamicFields() != null && !reserveTemplateParam.getDynamicFields().isEmpty()) {
            String dynamicField = JSONUtil.toJsonStr(reserveTemplateParam);
            reserveTemplateParam.setDynamicField(dynamicField);
        }
        reserveTemplateService.insertReserveTemplate(reserveTemplateParam);
        return new ResponseData().success(null);
    }

}

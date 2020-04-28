package com.mctpay.manager.controller.template;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.manager.model.dto.template.ReserveTemplateDTO;
import com.mctpay.manager.model.param.ReserveTemplateParam;
import com.mctpay.manager.service.template.ReserveTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
            System.out.println(reserveTemplateParam.getDynamicFields());
            JSONObject dynamicFields = JSONUtil.parseObj(reserveTemplateParam.getDynamicFields());
            reserveTemplateParam.setDynamicField(dynamicFields);
        }
        reserveTemplateService.insertReserveTemplate(reserveTemplateParam);
        return new ResponseData().success(null);
    }

    /**
     * @Description 修改模板
     * @Date 14:39 2020/4/27
     **/
    @ApiOperation(value = "修改模板", notes = "修改模板", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/updateReserveTemplate")
    public ResponseData updateReserveTemplate(@RequestBody ReserveTemplateParam reserveTemplateParam){
        reserveTemplateParam.setUpdateTime(new Date());
        ResponseData responseData = reserveTemplateService.updateReserveTemplate(reserveTemplateParam);
        return new ResponseData().success(null);
    }

    /**
     * @Description
     * @Date 10:24 2020/04/28
     **/
    @ApiOperation(value = "删除模板", notes = "删除模板", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/deleteReserveTemplate")
    public ResponseData deleteReserveTemplate(Long reserveTemplateId){
        ResponseData responseData = reserveTemplateService.deleteReserveTemplate(reserveTemplateId);
        return new ResponseData().success(null);
    }

    /**
     * @Description
     * @Date 10:56 2020/04/28
     **/
    @ApiOperation(value = "分页查询模板", notes = "分页查询模板",httpMethod = "POST",  consumes = "application/json")
    @PostMapping("/listReserveTemplate")
    public ResponsePageInfo<List<ReserveTemplateDTO>> listReserveTemplate(@RequestBody PageParam pageParam){
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        } else {
            PageHelper.orderBy("id desc");
        }
        List<ReserveTemplateDTO> reserveTemplateDTOs = reserveTemplateService.listReserveTemplate();
        return new ResponsePageInfo<List<ReserveTemplateDTO>>().success(reserveTemplateDTOs, pageInfo);
    }

}

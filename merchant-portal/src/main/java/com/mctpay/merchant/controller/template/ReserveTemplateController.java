package com.mctpay.merchant.controller.template;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.merchant.model.dto.template.ReserveTemplateDTO;
import com.mctpay.merchant.model.param.MerchantTemplateParam;
import com.mctpay.merchant.service.template.ReserveTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 预约模板
 * @Date: 2020/5/11 17:29
 */
@Api(value = "预约模板", tags = "预约模板")
@RestController
@RequestMapping("template")
public class ReserveTemplateController {

    @Autowired
    private ReserveTemplateService reserveTemplateService;

    /**
     * @Description
     * @Date 10:56 2020/04/28
     **/
    @ApiOperation(value = "分页查询模板", notes = "分页查询模板",httpMethod = "POST",  consumes = "application/json")
    @PostMapping("/listTemplate")
    public ResponseData listReserveTemplate(@RequestBody(required = false) PageParam pageParam){
        if (pageParam != null) {
            Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
            if (!StringUtils.isEmpty(pageParam.getOrder())) {
                PageHelper.orderBy(pageParam.getOrder());
            } else {
                PageHelper.orderBy("id desc");
            }
            List<ReserveTemplateDTO> reserveTemplateDTOs = reserveTemplateService.listReserveTemplate();
            return new ResponsePageInfo<List<ReserveTemplateDTO>>().success(reserveTemplateDTOs, pageInfo);
        } else {
            List<ReserveTemplateDTO> reserveTemplateDTOs = reserveTemplateService.listReserveTemplate();
            return new ResponseData().success(reserveTemplateDTOs);
        }
    }

    /**
     * @Description 分页查询商户模板
     * @Date 10:56 2020/04/28
     **/
    @ApiOperation(value = "分页查询商户模板", notes = "分页查询商户模板",httpMethod = "POST",  consumes = "application/json")
    @PostMapping("/listMerchantTemplate")
    public ResponseData<List<ReserveTemplateDTO>> listMerchantReserveTemplate(@RequestBody(required = false) PageParam pageParam){
        if (pageParam != null) {
            Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
            if (!StringUtils.isEmpty(pageParam.getOrder())) {
                PageHelper.orderBy(pageParam.getOrder());
            } else {
                PageHelper.orderBy("id desc");
            }
            List<ReserveTemplateDTO> reserveTemplateDTOs = reserveTemplateService.listMerchantReserveTemplate();
            return new ResponsePageInfo<List<ReserveTemplateDTO>>().success(reserveTemplateDTOs, pageInfo);
        } else {
            List<ReserveTemplateDTO> reserveTemplateDTOs = reserveTemplateService.listMerchantReserveTemplate();
            return new ResponseData<List<ReserveTemplateDTO>>().success(reserveTemplateDTOs);
        }
    }

    /**
     * @Description 更新商户模板
     * @Date 10:56 2020/04/28
     **/
    @ApiOperation(value = "更新商户模板", notes = "更新商户模板",httpMethod = "POST",  consumes = "application/json")
    @PostMapping("/updateMerchantTemplate")
    public ResponseData updateMerchantReserveTemplate(@RequestBody List<MerchantTemplateParam> templates) {
        reserveTemplateService.updateMerchantReserveTemplate(templates);
        return new ResponseData().success(null);
    }
}

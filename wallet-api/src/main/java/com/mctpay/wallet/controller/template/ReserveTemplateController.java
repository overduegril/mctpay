package com.mctpay.wallet.controller.template;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.wallet.model.dto.template.ReserveTemplateDTO;
import com.mctpay.wallet.model.param.PayPageParam;
import com.mctpay.wallet.service.template.ReserveTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
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
     * @Description 分页查询商户模板
     * @Date 10:56 2020/04/28
     **/
    @ApiOperation(value = "分页查询商户模板", notes = "分页查询商户模板", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/listMerchantTemplate")
    public ResponseData<List<ReserveTemplateDTO>> listMerchantReserveTemplate(@RequestBody(required = false) PageParam pageParam, String merchantId) {
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        } else {
            PageHelper.orderBy("id desc");
        }
        List<ReserveTemplateDTO> reserveTemplateDTOs = reserveTemplateService.listMerchantReserveTemplate(merchantId);
        return new ResponsePageInfo<List<ReserveTemplateDTO>>().success(reserveTemplateDTOs, pageInfo);
    }

    @ApiOperation(value = "调起支付页面", notes = "调起支付页面", httpMethod = "GET")
    @PostMapping("/pay-page")
    public ResponseData<List<ReserveTemplateDTO>> getPayPage(@RequestBody @Validated PayPageParam payPageParam) {
        return null;
    }

}

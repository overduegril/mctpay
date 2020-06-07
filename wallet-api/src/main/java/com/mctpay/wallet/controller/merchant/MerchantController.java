package com.mctpay.wallet.controller.merchant;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.wallet.model.dto.merchant.MerchantDtO;
import com.mctpay.wallet.service.merchant.MerchantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 商户控制层
 * @Date: 2020/2/27 17:10
 */
@Api(value = "商户相关", tags = "商户")
@RestController
@RequestMapping("manager-merchant")
public class MerchantController {


    @Autowired
    private MerchantService merchantService;


    @ApiOperation(value = "分页查询商户", notes = "分页查询商户 ;status值为1||2，表示激活商户，-1||-2为冻结商户",  httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/listMerchanttByInput")
    public ResponseData<List<MerchantDtO>> listMerchanttByInput( @RequestParam(required = false) Double lat,  @RequestParam(required = false) Double lon,  @RequestParam(required = false) String inputContent, @RequestBody PageParam pageParam){
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        }
        List<MerchantDtO> merchantDtOs = merchantService.listMerchantByInput(lat,lon,inputContent);
        return new ResponsePageInfo<List<MerchantDtO>>().success(merchantDtOs, pageInfo);
    }

}

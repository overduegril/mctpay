package com.mctpay.wallet.controller.merchant;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.wallet.model.dto.merchant.MerchantDtO;
import com.mctpay.wallet.model.dto.merchant.TradeRecordDTO;
import com.mctpay.wallet.model.entity.system.UserEntity;
import com.mctpay.wallet.service.merchant.MctTradeRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: chenshubiao
 * @Description: 操作记录控制层
 * @Date: 2020/6/17 15:01
 */
@Api(value = "操作记录相关", tags = "操作记录")
@RestController
@RequestMapping("wallet-mctTradeRecord")
public class MctTradeRecordController {

    @Autowired
    private MctTradeRecordService mctTradeRecordService;
    @ApiOperation(value = "分页查询商户", notes = "分页查询商户 ;",  httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/listTradeRecord")
    public ResponseData<List<MerchantDtO>> listTradeRecord(String input, @RequestBody PageParam pageParam){
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        }
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<TradeRecordDTO> tradeRecordDTOs = mctTradeRecordService.listTradeRecord(userEntity.getId(),input);
        return new ResponsePageInfo<List<TradeRecordDTO>>().success(tradeRecordDTOs, pageInfo);
    }


}

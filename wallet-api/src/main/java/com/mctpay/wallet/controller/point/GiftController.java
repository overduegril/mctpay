package com.mctpay.wallet.controller.point;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.wallet.model.dto.point.GiftDTO;
import com.mctpay.wallet.service.point.GiftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 礼品控制层
 * @Date: 2020/2/24 10:32
 */
@Api(value = "兑换礼物", tags = "积分")
@RestController
@RequestMapping("manager-gift")
public class GiftController {

    @Autowired
    private GiftService giftService;

    @ApiOperation(value = "分页查询积分礼品", notes = "分页查询积分礼品 ;status值为1|，表示激活积分商品，-1为冻结积分商品",  httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/listGiftByInput")
    public ResponseData<List<GiftDTO>> listGiftByInput(@RequestBody PageParam pageParam){
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        }
        List<GiftDTO> giftDTOs = giftService.listGiftByInput();
        return new ResponsePageInfo<List<GiftDTO>>().success(giftDTOs, pageInfo);
    }



}

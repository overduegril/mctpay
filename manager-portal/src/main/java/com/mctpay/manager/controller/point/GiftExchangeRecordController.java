package com.mctpay.manager.controller.point;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.model.dto.point.GiftExchangeRecordDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 礼物兑换记录
 * @Date: 2020/4/13 11:32
 */
@Api(value = "礼物兑换记录", tags = "积分")
@RestController
@RequestMapping(" gift-exchange-record")
public class GiftExchangeRecordController {

    @ApiOperation(value = "分页查询礼物兑换记录", notes = "分页查询礼物兑换记录",  httpMethod = "POST", consumes = "application/json")
    @PostMapping("/listGiftExchangeRecordByInput")
    public ResponseData insertGift(@RequestParam(required = false) String inputContent, @RequestBody PageParam pageParam){
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        }
        List<GiftExchangeRecordDTO> giftExchangeRecordDTOs = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            GiftExchangeRecordDTO giftExchangeRecordDTO = new GiftExchangeRecordDTO();
            giftExchangeRecordDTO.setGoodsName("xxx");
            giftExchangeRecordDTO.setId("1");
            giftExchangeRecordDTO.setExchangeCount(11);
            giftExchangeRecordDTO.setExchangeDate(DateUtil.formatDate(new Date()));
            giftExchangeRecordDTO.setGoodsTypeName("实物");
            giftExchangeRecordDTO.setPhoneNumber("18434395476");
            giftExchangeRecordDTO.setReceiveAdderess("北京天安门");
            giftExchangeRecordDTO.setReceivePesrsonName("孟姜女");
            giftExchangeRecordDTO.setReceiveWayName("到店自取");
            giftExchangeRecordDTO.setSendStatus(0);
            giftExchangeRecordDTOs.add(giftExchangeRecordDTO);
        }
        return new ResponseData< List<GiftExchangeRecordDTO>>().success(giftExchangeRecordDTOs);
    }


}

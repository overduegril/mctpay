package com.mctpay.manager.controller.point;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.manager.model.dto.point.GiftDTO;
import com.mctpay.manager.model.param.GiftParam;
import com.mctpay.manager.service.point.GiftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
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

    @ApiOperation(value = "添加积分礼品", notes = "积分礼品",  httpMethod = "POST", consumes = "application/json")
    @PostMapping("/insertGift")
    public ResponseData insertGift(GiftParam giftParam){
            Long id = UIdUtils.getUid();
            giftParam.setId(id);
            giftParam.setStatus(1);
            giftParam.setUpdateTime(new Date());
            giftParam.setCreateTime(new Date());
            return giftService.insertGift(giftParam);
    }

    @ApiOperation(value = "分页查询积分礼品", notes = "分页查询积分礼品 ;status值为1||2，表示激活积分商品，-1||-2为冻结积分商品",  httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/listGiftByInput")
    public ResponseData listGiftByInput(@RequestParam(required = false) String inputContent, @RequestBody PageParam pageParam){
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        }
        List<GiftDTO> giftDTOs = giftService.listGiftByInput(inputContent);
        return new ResponsePageInfo<List<GiftDTO>>().success(giftDTOs, pageInfo);
    }

    @ApiOperation(value = "冻结/激活积分产品", notes = "冻结/激活积分产品；status传值为正数则是激活的状态，负数为冻结状态，传该积分产品status的相反数", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/switchGift")
    public ResponseData switchGift(@RequestParam Long giftId, @RequestParam Integer state){
        return giftService.switchGift(giftId, state);
    }


}

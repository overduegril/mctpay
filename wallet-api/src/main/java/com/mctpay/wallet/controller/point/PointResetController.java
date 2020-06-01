package com.mctpay.wallet.controller.point;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.wallet.model.dto.point.PointResetDTO;
import com.mctpay.wallet.service.point.PointResetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 积分重置控制层
 * @Date: 2020/4/2 19:55
 */
@Api(value = "积分重置", tags = "积分")
@RestController
@RequestMapping("member-rules-reset")
public class PointResetController {

    @Autowired
    private PointResetService pointResetService;



    @ApiOperation(value = "分页查询积分重置设置", notes = "分页查询积分重置设置 ;status值为1|，表示激活，-1为冻结", httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/listPointReset")
    public ResponseData<List<PointResetDTO>> listPointReset(@RequestBody PageParam pageParam) {
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        } else {
            PageHelper.orderBy("id desc");
        }
        List<PointResetDTO> pointResetDTOs = pointResetService.listPointReset();
        return new ResponsePageInfo<List<PointResetDTO>>().success(pointResetDTOs, pageInfo);
    }


}

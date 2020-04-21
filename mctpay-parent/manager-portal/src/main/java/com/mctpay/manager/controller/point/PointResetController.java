package com.mctpay.manager.controller.point;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.manager.model.dto.point.GiftDTO;
import com.mctpay.manager.model.dto.point.PointResetDTO;
import com.mctpay.manager.model.entity.system.UserEntity;
import com.mctpay.manager.model.param.GiftParam;
import com.mctpay.manager.model.param.PointResetParam;
import com.mctpay.manager.service.point.PointResetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @ApiOperation(value = "添加积分重置设置", notes = "添加积分重置设置", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/insertPointReset")
    public ResponseData insertPointReset(PointResetParam pointResetParam) {
        DateUtil.parseDate(pointResetParam.getResetTime());
        String id = UIdUtils.getUid().toString();
        pointResetParam.setId(id);
        pointResetParam.setStatus(1);
        pointResetParam.setUpdateTime(new Date());
        pointResetParam.setCreateTime(new Date());
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        pointResetParam.setOperator(userEntity.getUsername());
        pointResetService.insertPointReset(pointResetParam);
        return new ResponseData().success(null);
    }

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

    @ApiOperation(value = "冻结/激活/删除积分重置设置", notes = "冻结/激活积分产品；status传值为正数则是激活的状态，负数为冻结状态，传该积分产品status的相反数，0为删除", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/switchGift")
    public ResponseData switchPointReset(@RequestParam String pointResetId, @RequestParam Integer state) {
        pointResetService.switchPointReset(pointResetId, state);
        return new ResponseData().success(null);
    }


}

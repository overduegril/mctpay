package com.mctpay.manager.controller.template;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.model.dto.system.UserDTO;
import com.mctpay.manager.model.dto.template.ShowPicturerDTO;
import com.mctpay.manager.model.param.ShowPicturerParam;
import com.mctpay.manager.model.param.UserParam;
import com.mctpay.manager.service.template.ShowPictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 用户相关接口
 * @Date: 2020/2/29 23:15
 */
@Api(value = "轮播图", tags = "轮播图")
@RestController
@RequestMapping("manager-showPicture")
public class ShowPictureController {

    @Autowired
    private ShowPictureService showPictureService;

    @ApiOperation(value = "添加轮播广告", notes = "添加轮播广告", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/insertShowPicturer")
    public ResponseData insertShowPicturer(@RequestBody ShowPicturerParam showPicturerParam) {
        showPicturerParam.setStatus(1);
        showPicturerParam.setCreateTime(new Date());
        showPicturerParam.setUpdateTime(new Date());
        return showPictureService.insertShowPicturer(showPicturerParam);
    }

    @ApiOperation(value = "分页查询轮播图", notes = "分页查询轮播图", httpMethod = "GET", consumes = "application/json")
    @GetMapping("/listShowPicturer")
    public ResponseData listShowPicturer(String useTypeCode) {
        List<ShowPicturerDTO> showPicturerDTOs = showPictureService.listShowPicturer(useTypeCode);
        return new ResponseData().success(showPicturerDTOs);
    }

    @ApiOperation(value = "删除轮播图", notes = "删除轮播图", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/deleteShowPicturer")
    public ResponseData deleteShowPicturer(@RequestParam Long showPicturerId) {
       return  showPictureService.updateSwitchShowPicturer(showPicturerId,0);
    }

}

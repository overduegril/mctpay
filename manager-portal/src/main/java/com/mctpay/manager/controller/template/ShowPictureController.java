package com.mctpay.manager.controller.template;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.manager.model.dto.system.UserDTO;
import com.mctpay.manager.model.dto.template.ShowPicturerDTO;
import com.mctpay.manager.model.param.ShowPicturerParam;
import com.mctpay.manager.model.param.UserParam;
import com.mctpay.manager.service.template.ShowPictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    // @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "添加轮播广告", notes = "添加轮播广告", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/insertShowPicturer")
    public ResponseData insertShowPicturer(@RequestBody ShowPicturerParam showPicturerParam) {
        // 设置会员ID
        Long id = UIdUtils.getUid();
        showPicturerParam.setId(id);
        showPicturerParam.setStatus(2);
        showPicturerParam.setCreateTime(new Date());
        showPicturerParam.setUpdateTime(new Date());
        return showPictureService.insertShowPicturer(showPicturerParam);
    }

    @ApiOperation(value = "分页查询轮播图", notes = "分页查询轮播图", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/listShowPicturer")
    public ResponseData listShowPicturer() {
        List<ShowPicturerDTO> showPicturerDTOs = showPictureService.listShowPicturer();
        return new ResponseData().success(showPicturerDTOs);
    }

    @ApiOperation(value = "删除轮播图", notes = "删除轮播图", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/deleteShowPicturer")
    public ResponseData deleteShowPicturer(@RequestParam Long showPicturerId) {
       return  showPictureService.updateSwitchShowPicturer(showPicturerId,0);
    }

}

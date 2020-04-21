package com.mctpay.manager.controller.system;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.OSSUtils;
import com.mctpay.manager.config.OSSProperties;
import com.mctpay.manager.model.enums.FileUseTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Author: guodongwei
 * @Description: 通用接口
 * @Date: 2020/3/7 23:17
 */
@Api(value = "通用接口", tags = "通用接口")
@RestController
@RequestMapping("common")
@CrossOrigin
public class CommonController {

    @Autowired
    private OSSProperties oSSProperties;

    @ApiOperation(value = "文件上传", notes = "文件上传", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "userType",
            value = "使用类型,SHOP_PHOTO：门头照;",
            required = true,
            paramType = "insert",
            dataType = "String"
    )})
    @PostMapping(value = "/uploadFile")
    public ResponseData<String> uploadFile(MultipartFile file, @RequestParam String userType) throws Exception {
        InputStream inputStream = file.getInputStream();
        String fileUrl = "";
        if (FileUseTypeEnum.SHOP_PHOTO.toString().equals(userType)){
            fileUrl = OSSUtils.uploadFileInputStream(oSSProperties.getBucketName(), oSSProperties.getShopPhotoKeyPrefix() + file.getOriginalFilename(), inputStream);
        }
        // 将图片地址回传
        return new ResponseData<String>().success(fileUrl);
    }

}

package com.mctpay.manager.controller.merchant;

import com.mctpay.common.uitl.OSSUtils;
import com.mctpay.manager.config.OSSProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: guodongwei
 * @Description: 商户控制层
 * @Date: 2020/2/27 17:10
 */
@Api(value = "商户相关", tags = "商户")
@RestController
@RequestMapping("merchant")
public class MerchantController {

    @Autowired
    private OSSProperties oSSProperties;

    @ApiOperation(value = "测试图片上传", notes = "测试图片上传", httpMethod = "POST")
    @PostMapping(value = "/testFileUpload")
    public String uploadSalesEnterpriseInfos(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        String wosz = OSSUtils.uploadFileInputStream(oSSProperties.getBucketName(), oSSProperties.getKeyPrefix() + file.getOriginalFilename(), inputStream);
        return wosz;
    }


}

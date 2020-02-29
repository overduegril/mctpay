package com.mctpay.manager.controller.merchant;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.OSSUtils;
import com.mctpay.manager.config.OSSProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

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
    private MerchantService merchantService;

    @ApiOperation(value = "添加商户", notes = "添加商户",  httpMethod = "POST", consumes = "application/json")
    @PostMapping("/insertMerchant")
    public ResponseData insertMerchant(MerchantParam merchantParam){

        Long id = UIdUtils.getUid();
        merchantParam.setId(id);
        merchantParam.setStatus(2);
        merchantParam.setCreateTime(new Date());
        merchantParam.setUpdateTime(new Date());
        return merchantService.insertMerchant(merchantParam);
    }

    @ApiOperation(value = "修改商户", notes = "修改商户",  httpMethod = "POST", consumes = "application/json")
    @PostMapping("/updateMerchant")
    public ResponseData updateMerchant(MerchantParam merchantParam){
        merchantParam.setUpdateTime(new Date());
        return merchantService.updateMerchant(merchantParam);
    }

    @ApiOperation(value = "门头照图片上传", notes = "门头照图片上传", httpMethod = "POST")
    @PostMapping(value = "/uploadShopPhoto")
    public ResponseData<String> uploadShopPhoto(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        // TODO 文件存储路径需要优化
        String shopPhotoUrl = OSSUtils.uploadFileInputStream(oSSProperties.getBucketName(), oSSProperties.getKeyPrefix() + file.getOriginalFilename(), inputStream);
        // 将图片地址回传，最后统一入库
        return new ResponseData<String>().success(shopPhotoUrl);
    }

}

package com.mctpay.manager.controller.merchant;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.common.uitl.OSSUtils;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.manager.config.OSSProperties;
import com.mctpay.manager.model.dto.merchant.MerchantDtO;
import com.mctpay.manager.model.param.MerchantParam;
import com.mctpay.manager.service.merchant.MerchantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

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


    @ApiOperation(value = "分页查询商户", notes = "分页查询商户 ;status值为1||2，表示激活商户，-1||-2为冻结商户",  httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/listMerchanttByInput")
    public ResponseData listMerchanttByInput(@RequestParam(required = false) String inputContent, @RequestBody PageParam pageParam){
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        }
        List<MerchantDtO> merchantDtOs = merchantService.listMerchantByInput(inputContent);
        return new ResponsePageInfo<List<MerchantDtO>>().success(merchantDtOs, pageInfo);
    }

    @ApiOperation(value = "冻结/激活商户", notes = "冻结/激活商户；status传值为正数则是激活的状态，负数为冻结状态，传该管理原status的相反数", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/switchMerchant")
    public ResponseData switchMerchant(@RequestParam Long merchantId, @RequestParam Integer state) {
        return merchantService.switchMerchant(merchantId, state);
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

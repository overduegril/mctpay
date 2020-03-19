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
import com.mctpay.manager.model.enums.FileUseTypeEnum;
import com.mctpay.manager.model.param.MerchantParam;
import com.mctpay.manager.service.merchant.MerchantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static com.mctpay.common.constants.ErrorCode.FILE_FORMAT_NOT_CORRECT;

/**
 * @Author: guodongwei
 * @Description: 商户控制层
 * @Date: 2020/2/27 17:10
 */
@Api(value = "商户相关", tags = "商户")
@RestController
@RequestMapping("manager-merchant")
public class MerchantController {

    @Autowired
    private OSSProperties oSSProperties;

    @Autowired
    private MerchantService merchantService;

    @ApiOperation(value = "添加商户", notes = "添加商户",  httpMethod = "POST", consumes = "application/json")
    @PostMapping("/insertMerchant")
    public ResponseData insertMerchant(MerchantParam merchantParam){

        String id = UIdUtils.getUid().toString();
        merchantParam.setId(id);
        merchantParam.setStatus(2);
        merchantParam.setCreateTime(new Date());
        merchantParam.setUpdateTime(new Date());
        return merchantService.insertMerchant(merchantParam);
    }

    @ApiOperation(value = "修改商户", notes = "修改商户",  httpMethod = "POST", consumes = "application/json")
    @PostMapping("/updateMerchant")
    public ResponseData updateMerchant(@RequestBody MerchantParam updateMerchantParam){
        updateMerchantParam.setUpdateTime(new Date());
        return merchantService.updateMerchant(updateMerchantParam);
    }


    @ApiOperation(value = "分页查询商户", notes = "分页查询商户 ;status值为1||2，表示激活商户，-1||-2为冻结商户",  httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/listMerchanttByInput")
    public ResponseData<List<MerchantDtO>> listMerchanttByInput(@RequestParam(required = false) String inputContent, @RequestBody PageParam pageParam){
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        }
        List<MerchantDtO> merchantDtOs = merchantService.listMerchantByInput(inputContent);
        return new ResponsePageInfo<List<MerchantDtO>>().success(merchantDtOs, pageInfo);
    }

    @ApiOperation(value = "冻结/激活商户", notes = "冻结/激活商户；status传值为正数则是激活的状态，负数为冻结状态，传该管理原status的相反数", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/switchMerchant")
    public ResponseData switchMerchant(@RequestParam String merchantId, @RequestParam Integer state) {
        return merchantService.switchMerchant(merchantId, state);
    }

    @ApiOperation(value = "商户重置密码", notes = "商户重置密码", httpMethod = "POST")
    @PostMapping(value = "/resetPassword")
    public ResponseData resetPassword(String mrchantId) throws Exception {
        merchantService.resetPassword(mrchantId);
        return new ResponseData<String>().success(null);
    }

    @ApiOperation(value = "营业执照上传", notes = "营业执照上传", httpMethod = "POST")
    @PostMapping(value = "/uploadBusinessLicense")
    public ResponseData<String> uploadBusinessLicense(@RequestParam MultipartFile file,@RequestParam String merchantId) throws Exception {
        if (!file.getOriginalFilename().endsWith(".pdf")) {
            return new ResponseData<String>().fail(FILE_FORMAT_NOT_CORRECT.getCode(), FILE_FORMAT_NOT_CORRECT.getMessage());
        }
        InputStream inputStream = file.getInputStream();
        String businessLicenseUrl = OSSUtils.uploadFileInputStream(oSSProperties.getBucketName(), oSSProperties.getBusinessLicenseKeyPrefix() + file.getOriginalFilename(), inputStream);
        // 将文件路径保存
        merchantService.insertBusinessLicense(businessLicenseUrl, merchantId);
        return new ResponseData<String>().success(businessLicenseUrl);
    }
}

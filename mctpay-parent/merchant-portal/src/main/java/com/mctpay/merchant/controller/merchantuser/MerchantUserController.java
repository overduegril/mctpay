package com.mctpay.merchant.controller.merchantuser;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.common.config.GlobalConstant;
import com.mctpay.common.exception.BusinessException;
import com.mctpay.manager.convert.merchantuser.MerchantUserConvert;
import com.mctpay.manager.keyvalue.MerchantUserTypeEnum;
import com.mctpay.manager.model.dto.merchantuser.EditReqDtO;
import com.mctpay.manager.model.dto.merchantuser.ListMerchantUserByInputReqDtO;
import com.mctpay.manager.model.vo.merchantuser.EditReqVo;
import com.mctpay.manager.model.vo.merchantuser.ListMerchantUserByInputReqVo;
import com.mctpay.manager.model.vo.merchantuser.ListMerchantUserByInputResVo;
import com.mctpay.manager.service.merchantuser.MerchantUserService;
import com.mctpay.merchant.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liqiang
 * @date 2020/4/22 09:52
 * @Description: (what)
 * (why)
 * (how)
 */
@Api(value = "商户管理员相关", tags = "商户管理员")
@RestController
@RequestMapping("/merchant-user")
public class MerchantUserController extends BaseController {
    @Autowired
    MerchantUserConvert merchantUserConvert;
    @Autowired
    MerchantUserService merchantUserService;

    @ApiOperation(value = "分页查询商户管理员", notes = "分页查询商户管理员", httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/listMerchantUserByInput")
    @ResponseBody
    public ResponseData<List<ListMerchantUserByInputResVo>> listMerchantUserByInput(@RequestBody ListMerchantUserByInputReqVo listMerchantUserByInputReqVo) {
        Page<Object> pageInfo = PageHelper.startPage(listMerchantUserByInputReqVo.getPageNum(), listMerchantUserByInputReqVo.getPageSize());
        if (!StringUtils.isEmpty(listMerchantUserByInputReqVo.getOrder())) {
            PageHelper.orderBy(listMerchantUserByInputReqVo.getOrder());
        }
        ListMerchantUserByInputReqDtO listMerchantUserByInputReqVoDtO = merchantUserConvert.listMerchantUserByInputReqVoToListMerchantUserByInputReqDtO(listMerchantUserByInputReqVo);
        //商户绑定额账号 id和商户id一致
        listMerchantUserByInputReqVoDtO.setMerchantId(getCurrentUserInfo().getId());
        List<ListMerchantUserByInputResVo> listMerchantUserByInputResVos = merchantUserService.listMerchantUserByInput(listMerchantUserByInputReqVoDtO);
        return new ResponsePageInfo<List<ListMerchantUserByInputResVo>>().success(listMerchantUserByInputResVos, pageInfo);
    }

    @ApiOperation(value = "修改密码", notes = "修改密码", httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/updatePassword")
    @ResponseBody
    public ResponseData updatePassword(@RequestParam String newPassword) throws BusinessException {
        return merchantUserService.updatePassword(getCurrentUserInfo().getId(), newPassword) ? success() : fail(500, "修改失败");
    }

    @ApiOperation(value = "restPassword", notes = "重置密码", httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/restPassword")
    @ResponseBody
    public ResponseData restPassword(String userId) throws BusinessException {
        return merchantUserService.updatePassword(userId, GlobalConstant.defalutPassword) ? success() : fail(500, "重置密码失败");
    }

    @ApiOperation(value = "edit", notes = "新增/编辑商户信息", httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseData edit(@RequestBody EditReqVo editReqVo) throws BusinessException {
        EditReqDtO editReqDtO=merchantUserConvert.editReqVoToEditReqDtO(editReqVo);
        //前端没有使用 暂时都是男性
        editReqDtO.setGender(1);
        //这个字段没有赋值 暂时跟名字一样
        editReqDtO.setNickname(editReqVo.getName());
        //商户绑定额账号 id和商户id一致
        if(editReqVo.getId()==null){
            //默认普通管理员
            editReqDtO.setMerchantUserType(MerchantUserTypeEnum.manager);
            editReqDtO.setMerchantId(getCurrentUserInfo().getId());
            return merchantUserService.insert(editReqDtO) ? success() : fail(500, "新增失败");
        }else{
            return merchantUserService.update(editReqDtO)?success():fail(500, "修改失败");
        }

    }

    /**
     * 冻结激活用户
     * @param userId
     * @param state
     * @return
     * @throws BusinessException
     */
    @ApiOperation(value = "/switchUser", notes = "冻结激活用户", httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/switchUser")
    @ResponseBody
    public ResponseData switchUser(Long userId, Integer state) throws BusinessException {
        return  merchantUserService.switchUser(userId,state)?success():fail(500,"修改状态失败");
    }

}

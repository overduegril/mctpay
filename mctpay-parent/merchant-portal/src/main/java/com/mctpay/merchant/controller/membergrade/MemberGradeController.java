package com.mctpay.merchant.controller.membergrade;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.common.exception.BusinessException;
import com.mctpay.manager.convert.membergrade.MemberGradeConvert;
import com.mctpay.manager.keyvalue.StatusEnum;
import com.mctpay.manager.model.dto.membergrade.EditReqDtO;
import com.mctpay.manager.model.dto.membergrade.FindPageReqDto;
import com.mctpay.manager.model.dto.membergrade.ListMemberGradeByInputResVo;
import com.mctpay.manager.model.entity.membergrade.MemberGradeEntity;
import com.mctpay.manager.model.vo.membergrade.EditReqVo;
import com.mctpay.manager.model.vo.membergrade.ListMemberGradeByInputReqVo;
import com.mctpay.manager.model.vo.merchantuser.ListMerchantUserByInputResVo;
import com.mctpay.manager.service.membergrade.MemberGradeService;
import com.mctpay.merchant.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liqiang
 * @date 2020/4/22 16:24
 * @Description: (what)
 * (why)
 * (how)
 */
@Api(value = "会员等级相关", tags = "会员等级相关")
@RestController
@RequestMapping("/memberGrade")
public class MemberGradeController extends BaseController
{
    @Autowired
    MemberGradeConvert memberGradeConvert;
    @Autowired
    MemberGradeService memberGradeService;
    @ApiOperation(value = "edit", notes = "新增/编辑会员等级", httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/restPassword")
    @ResponseBody
    public ResponseData edit(@RequestBody EditReqVo editReqVo) throws BusinessException {
        EditReqDtO editReqDtO=memberGradeConvert.editReqVoToEditReqDtO(editReqVo);
        //默认冻结
        if(editReqDtO.getStatus()==null){
            editReqDtO.setStatus(StatusEnum.frozen);
        }
        editReqDtO.setOperationUserId(getCurrentUserInfo().getId());
        editReqDtO.setMerchantId(getCurrentUserInfo().getMerchantId());
        //商户绑定额账号 id和商户id一致
        if(editReqVo.getId()==null){
            return memberGradeService.insert(editReqDtO) ? success() : fail(500, "新增失败");
        }else{
            return memberGradeService.update(editReqDtO)?success():fail(500, "修改失" +
                    "败");
        }
    }

    @ApiOperation(value = "分页查询会员等级信息管理员", notes = "分页查询会员等级信息", httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/listMemberGradeByInput")
    @ResponseBody
    public ResponseData<List<ListMerchantUserByInputResVo>> listMemberGradeByInput(@RequestBody ListMemberGradeByInputReqVo listMemberGradeByInputReqVo) {
        if (!StringUtils.isEmpty(listMemberGradeByInputReqVo.getOrder())) {
            PageHelper.orderBy(listMemberGradeByInputReqVo.getOrder());
        }
        FindPageReqDto req = memberGradeConvert.listMemberGradeByInputReqVoToFindPageReqDto(listMemberGradeByInputReqVo);
        //商户绑定额账号 id和商户id一致
        req.setMerchantId(getCurrentUserInfo().getMerchantId());
        PageInfo<MemberGradeEntity> result = memberGradeService.findPage(req);
        List<ListMemberGradeByInputResVo> listMemberGradeByInputResVos=null;
        if(!CollectionUtil.isEmpty(result.getList())){
            listMemberGradeByInputResVos=memberGradeConvert.poToListMemberGradeByInputResVo(result.getList());
        }
        return new ResponsePageInfo<List<ListMemberGradeByInputResVo>>().success(listMemberGradeByInputResVos, PageHelper.getLocalPage());
    }
}

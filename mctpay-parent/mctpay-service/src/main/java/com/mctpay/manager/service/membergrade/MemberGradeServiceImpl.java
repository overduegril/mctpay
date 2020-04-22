package com.mctpay.manager.service.membergrade;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mctpay.common.exception.BusinessException;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.manager.convert.membergrade.MemberGradeConvert;
import com.mctpay.manager.mapper.membergrade.MemberGradeMapper;
import com.mctpay.manager.mapper.merchantuser.MerchantUserMapper;
import com.mctpay.manager.model.dto.membergrade.EditReqDtO;
import com.mctpay.manager.model.dto.membergrade.FindPageReqDto;
import com.mctpay.manager.model.entity.membergrade.MemberGradeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author liqiang
 * @date 2020/4/22 16:22
 * @Description: (what)
 * (why)
 * (how)
 */
@Service
public class MemberGradeServiceImpl implements MemberGradeService {
    @Autowired
    MemberGradeMapper memberGradeMapper;
    @Autowired
    MemberGradeConvert memberGradeConvert;
    @Autowired
    MerchantUserMapper merchantUserMapper;
    @Override
    public boolean insert(EditReqDtO editReqDtO) throws BusinessException {
        MemberGradeEntity memberGradeEntity= memberGradeConvert.editReqDtOToPo(editReqDtO);
        memberGradeEntity.setId(UIdUtils.getUid().toString());
        memberGradeEntity.setCreateTime(new java.util.Date());
        memberGradeEntity.setCreateUserId(editReqDtO.getOperationUserId());
//        MerchantUserEntity merchantUser=merchantUserMapper.selectByPrimaryKey(editReqDtO.getOperationUserId());
//        if(merchantUser==null){
//            throw new BusinessException(500,"未找到用户信息");
//        }
        memberGradeEntity.setMerchantId(editReqDtO.getMerchantId());
        return memberGradeMapper.insert(memberGradeEntity)>0;
    }

    @Override
    public boolean update(EditReqDtO editReqDtO) throws BusinessException {
        MemberGradeEntity memberGradeEntity= memberGradeMapper.selectByPrimaryKey(editReqDtO.getId());
        if(memberGradeEntity==null){
            throw new BusinessException(500,"未找到数据信息");
        }
        memberGradeConvert.updateMemberGradeEntityFromEditReqDtO(editReqDtO,memberGradeEntity);
        memberGradeEntity.setLastUpdateUserId(editReqDtO.getOperationUserId());
        memberGradeEntity.setUpdateTime(new java.util.Date());
        return memberGradeMapper.updateByPrimaryKey(memberGradeEntity)>0;
    }

    /**
     * 分页查询
     *
     * @param reqDto
     * @return
     */
    @Override
    public PageInfo<MemberGradeEntity> findPage(FindPageReqDto reqDto) {
        PageHelper.startPage(reqDto.getPageNum(), reqDto.getPageSize());
        Example example=new Example(MemberGradeEntity.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("merchantId",reqDto.getMerchantId());
        List<MemberGradeEntity> result=memberGradeMapper.selectByExample(example);
        PageInfo pageInfo=new PageInfo<MemberGradeEntity>(result);
        return pageInfo;
    }


}

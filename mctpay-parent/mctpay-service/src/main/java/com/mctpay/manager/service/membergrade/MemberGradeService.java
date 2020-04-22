package com.mctpay.manager.service.membergrade;

import com.github.pagehelper.PageInfo;
import com.mctpay.common.exception.BusinessException;
import com.mctpay.manager.model.dto.membergrade.EditReqDtO;
import com.mctpay.manager.model.dto.membergrade.FindPageReqDto;
import com.mctpay.manager.model.entity.membergrade.MemberGradeEntity;

/**
 * @author liqiang
 * @date 2020/4/22 16:22
 * @Description: (what)
 * (why)
 * (how)
 */
public interface MemberGradeService {
    /**
     * 新增等级
     * @param editReqDtO
     * @return
     * @throws BusinessException
     */
    public boolean insert(EditReqDtO editReqDtO) throws BusinessException;

    /**
     * 修改等级
     * @param editReqDtO
     * @return
     * @throws BusinessException
     */
    public boolean update(EditReqDtO editReqDtO) throws BusinessException;

    /**
     * 分页查询
     * @param reqDto
     * @return
     */
    public PageInfo<MemberGradeEntity> findPage(FindPageReqDto reqDto);
}

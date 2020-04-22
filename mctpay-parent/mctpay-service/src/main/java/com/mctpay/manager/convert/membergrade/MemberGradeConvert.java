package com.mctpay.manager.convert.membergrade;

import com.mctpay.manager.model.dto.membergrade.EditReqDtO;
import com.mctpay.manager.model.dto.membergrade.FindPageReqDto;
import com.mctpay.manager.model.dto.membergrade.ListMemberGradeByInputResVo;
import com.mctpay.manager.model.entity.membergrade.MemberGradeEntity;
import com.mctpay.manager.model.vo.membergrade.EditReqVo;
import com.mctpay.manager.model.vo.membergrade.ListMemberGradeByInputReqVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author liqiang
 * @date 2020/4/22 16:21
 * @Description: (what)
 * (why)
 * (how)
 */
@Mapper(componentModel = "spring")
public interface MemberGradeConvert {
    public MemberGradeEntity editReqDtOToPo(EditReqDtO editReqDtO);

    public EditReqDtO editReqVoToEditReqDtO(EditReqVo editReqVo);

    public void  updateMemberGradeEntityFromEditReqDtO(EditReqDtO editReqDtO,@MappingTarget MemberGradeEntity memberGradeEntity);

    public FindPageReqDto listMemberGradeByInputReqVoToFindPageReqDto(ListMemberGradeByInputReqVo listMemberGradeByInputReqVo);

    public List<ListMemberGradeByInputResVo> poToListMemberGradeByInputResVo(List<MemberGradeEntity> memberGradeEntities);
    public ListMemberGradeByInputResVo poToListMemberGradeByInputResVo(MemberGradeEntity memberGradeEntities);
}

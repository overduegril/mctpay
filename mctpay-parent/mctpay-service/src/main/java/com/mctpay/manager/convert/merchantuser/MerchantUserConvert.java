package com.mctpay.manager.convert.merchantuser;

import com.mctpay.manager.model.dto.merchantuser.EditReqDtO;
import com.mctpay.manager.model.dto.merchantuser.FindByEmailDtO;
import com.mctpay.manager.model.dto.merchantuser.FindByLoginNameResDtO;
import com.mctpay.manager.model.dto.merchantuser.ListMerchantUserByInputReqDtO;
import com.mctpay.manager.model.entity.merchantuser.MerchantUserEntity;
import com.mctpay.manager.model.vo.merchant.LoginedResVo;
import com.mctpay.manager.model.vo.merchantuser.EditReqVo;
import com.mctpay.manager.model.vo.merchantuser.ListMerchantUserByInputReqVo;
import com.mctpay.manager.model.vo.merchantuser.ListMerchantUserByInputResVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author liqiang
 * @date 2020/4/22 10:55
 * @Description: (what)
 * (why)
 * (how)
 */
@Mapper(componentModel = "spring")
public interface MerchantUserConvert {
    public ListMerchantUserByInputReqDtO listMerchantUserByInputReqVoToListMerchantUserByInputReqDtO(ListMerchantUserByInputReqVo listMerchantUserByInputReqVo);

    public List<ListMerchantUserByInputResVo> merchantUserEntityToListMerchantUserByInputResDtO(List<MerchantUserEntity> list);

    public ListMerchantUserByInputResVo merchantUserEntityToListMerchantUserByInputResDtO(MerchantUserEntity student);

    public FindByEmailDtO entityToFindByEmailDtO(MerchantUserEntity entity);
    public FindByLoginNameResDtO entityToFindByLoginNameResDtO(MerchantUserEntity entity);

    public LoginedResVo findByEmailDtOToLoginedResVo(FindByEmailDtO findByEmailDtO);
    public LoginedResVo findByLoginNameResDtOToLoginedResVo(FindByLoginNameResDtO findByLoginNameResDtO);

    public EditReqDtO editReqVoToEditReqDtO(EditReqVo editReqVo);

    public MerchantUserEntity editReqDtOToPo(EditReqDtO editReqDtO);
    @Mappings({
            @Mapping(target = "merchantId", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "merchantUserType", ignore = true)}
    )
    void updateMerchantUserEntityFromEditReqDtO(EditReqDtO editReqDtO,
                                          @MappingTarget MerchantUserEntity merchantUserEntity);
}

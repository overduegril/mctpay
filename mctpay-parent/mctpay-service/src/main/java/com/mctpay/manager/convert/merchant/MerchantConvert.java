package com.mctpay.manager.convert.merchant;

import com.mctpay.manager.model.dto.merchantuser.EditReqDtO;
import com.mctpay.manager.model.param.MerchantParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author liqiang
 * @date 2020/4/21 19:54
 * @Description: (what)
 * (why)
 * (how)
 */
@Mapper(componentModel = "spring")
public interface MerchantConvert {
    @Mappings({
            @Mapping(target = "name", source ="merchantName" ),
            @Mapping(target = "phoneNumber", source ="phoneNumber"),
            @Mapping(target = "email",source = "email"),
            @Mapping(target = "loginName",source = "email")
    }
    )
   public EditReqDtO merchantParamToEditReqDtO(MerchantParam merchantParam);
}

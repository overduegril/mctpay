package com.mctpay.manager.mapper.merchant;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.manager.model.entity.merchant.IndustryDictionaryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-04-24 09:03:03
 */
@Mapper
public interface IndustryDictionaryMapper extends BaseMapper<IndustryDictionaryEntity> {
    /**
     * @Description 行业列表
     * @Date 10:30 2019/04/29
     **/
    List<IndustryDictionaryEntity> listIndustryDictionary();
}

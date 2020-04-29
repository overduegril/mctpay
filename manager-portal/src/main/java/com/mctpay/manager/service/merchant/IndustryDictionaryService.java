package com.mctpay.manager.service.merchant;

import com.mctpay.manager.model.dto.merchant.IndustryDictionaryDTO;

import java.util.List;

/**
 * @Author: chenshubiao
 * @Description: 行业service
 * @Date: 2020/04/29 10:14
 */
public interface IndustryDictionaryService  {

    /**
     * @Description 行业列表
     * @Date 10:24 2019/04/29
     **/

    List<IndustryDictionaryDTO> listIndustryDictionary();
}

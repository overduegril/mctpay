package com.mctpay.manager.controller.merchant;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.model.dto.merchant.IndustryDictionaryDTO;
import com.mctpay.manager.service.merchant.IndustryDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: chenshubiao
 * @Description: 行业控制层
 * @Date: 2020/4/29 10:02
 */
@Api(value = "行业相关", tags = "行业")
@RestController
@RequestMapping("industry_dictionary")
public class IndustryDictionaryController {

    @Autowired
    private IndustryDictionaryService industryDictionaryService;

    @ApiOperation(value = "行业列表", notes = "行业列表",  httpMethod = "POST", consumes = "application/json")
    @PostMapping("/listIndustryDictionary")
    public ResponseData<List<IndustryDictionaryDTO>> listIndustryDictionary(){

        List<IndustryDictionaryDTO> industryDictionaryDTOLists = industryDictionaryService.listIndustryDictionary();
        return new ResponseData<List<IndustryDictionaryDTO>>().success(industryDictionaryDTOLists);
    }
}

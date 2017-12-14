package com.smyw.basemodule.web;

import com.smyw.basemodule.common.model.dictionary.DicType;
import com.smyw.basemodule.service.dictionary.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value="/dictionary")
@Api(value = "/dictionary",description = "字典请求")
public class DictionaryController {
    private Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    @Resource
    private DictionaryService dictionaryService;

    @ApiOperation(value="获取字典类型列表", notes="获取字典类型列表！",httpMethod = "POST")
    @RequestMapping(value="/list", method= RequestMethod.POST)
    public Page<DicType> getTypeList(String tag, String name, int page, int size) {
        return dictionaryService.findTypeCutsom(tag,name,page,size);
    }
}

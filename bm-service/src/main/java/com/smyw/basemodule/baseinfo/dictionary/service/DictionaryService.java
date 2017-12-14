package com.smyw.basemodule.baseinfo.dictionary.service;

import com.smyw.basemodule.baseinfo.dictionary.dao.DictDataRepository;
import com.smyw.basemodule.baseinfo.dictionary.dao.DicTypeRepository;
import com.smyw.basemodule.baseinfo.dictionary.model.DicType;
import com.smyw.core.api.DictionaryComService;
import com.smyw.core.api.model.DictionaryData;
import com.smyw.core.api.model.DictionaryType;
import com.smyw.core.repo.specification.SimpleSpecificationBuilder;
import com.smyw.core.repo.kit.SimplePageBuilder;
import com.smyw.core.repo.kit.SimpleSortBuilder;
import com.smyw.core.repo.specification.SpecificationOperator;
import com.smyw.core.service.BaseService;
import com.smyw.core.utils.BeanUtils;
import com.smyw.core.utils.LogUtils;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:songwenke
 * @Description:数据字典服务
 * @Date:create in 9:54 2017/11/17
 * @Modified By:
 **/
@Service
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=DictionaryComService.class)
public class DictionaryService extends BaseService implements DictionaryComService {

    private static final LogUtils logger = LogUtils.getLog(DictionaryService.class);

    @Resource
    public void setDictionaryTypeRepository(DicTypeRepository repository){
        super.setRepository(repository);
    }
    private DictDataRepository dictDataRepository;
    @Resource
    public void setDictionaryDataRespository(DictDataRepository dictDataRepository){
        this.dictDataRepository = dictDataRepository;
    }
    @Override
    public DictionaryType findDictionaryTypeByTag(String tag,boolean isIncludeData) throws Exception {
        DictionaryType dictionaryType = (DictionaryType)BeanUtils.transform(
               ((DicTypeRepository)getRepository()).findDictionaryTypeByTag(tag),
               DictionaryType.class);
        if(isIncludeData){
            dictionaryType.setData(this.findDictionaryDataByTypeTag(tag));
        }
        return dictionaryType;
    }

    @Override
    public List<DictionaryData> findDictionaryDataByTypeTag(String tag) throws Exception {
        return (List<DictionaryData>)BeanUtils.transforms(
                dictDataRepository.findDictionaryDataByTag(tag),DictionaryData.class);
    }

    @Override
    public DictionaryData findDictionaryDataByKey(String tag, String key) throws Exception {
        return (DictionaryData)BeanUtils.transform(
                dictDataRepository.findDictionaryDataByKey(tag,key),DictionaryData.class);
    }

    @Override
    public String findDictionaryDataNameByKey(String tag, String key) {
        return  dictDataRepository.findDictionaryDataNameByKey(tag,key);
    }

    @Override
    public String findDictionaryDataMemoByKey(String tag, String key) {
        return  dictDataRepository.findDictionaryDataMemoByKey(tag,key);
    }

    public Page<DicType> findTypeCutsom(String tag, String name, int page, int size) {
        logger.info("根据标签或名称查询数据类型1312321");
        /**
         * 这里的查询表示id大于4或者name中包含a
         * 现在我们发现在SimpleSpecificationBuilder的add或者addOr方法中返回this的好处了
         */
        Page<DictionaryType> stus = (Page<DictionaryType>) ((DicTypeRepository)super.getRepository()).findAll(
                new SimpleSpecificationBuilder("id",SpecificationOperator.OPER.GT,4)
                        .and(new SimpleSpecificationBuilder("id",SpecificationOperator.OPER.GT,4).
                                        or("tag", SpecificationOperator.OPER.LIKE,"a"))
                        .generateSpecification(),
                SimplePageBuilder.generate(1, SimpleSortBuilder.generateSort("name_d")));
        return ((DicTypeRepository)getRepository()).findTypeCutsom(tag, name, new PageRequest(page-1,size,new Sort(Sort.DEFAULT_DIRECTION, "name")));
    }
}

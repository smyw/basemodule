package com.smyw.basemodule.baseinfo.dictionary.dao;

import com.smyw.basemodule.baseinfo.dictionary.model.DicType;
import com.smyw.core.repo.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Author:songwenke
 * @Description:数据字典类型的DAO
 * @Date:create in 9:54 2017/11/17
 * @Modified By:
 **/
public interface DicTypeRepository extends BaseRepository<DicType, Long> {
    /**
     * 根据标签获取类型
     * @param tag 标签
     * @return
     */
    DicType findDictionaryTypeByTag(String tag);

    /**
     * 根据标签和名称查询数据类型
     * @param tag
     * @param name
     * @return
     */
    @Query(value="select a.* from sys_dictionary_type a where (a.tag=:tag or :tag is null) and (a.name=:name or :name is null) /*#pageable*/",
            countQuery = "select count(*) from sys_dictionary_type a where (a.tag=:tag or :tag is null) and (a.name=:name or :name is null)",nativeQuery=true)
    Page<DicType> findTypeCutsom(@Param("tag") String tag, @Param("name") String name, Pageable pageable);
}

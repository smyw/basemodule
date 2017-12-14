package com.smyw.basemodule.dao.dictionary;

import com.smyw.basemodule.common.model.dictionary.DicData;
import com.smyw.core.repo.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @Author:songwenke
 * @Description:数据字典数据的DAO
 * @Date:create in 9:54 2017/11/17
 * @Modified By:
 **/
public interface DictDataRepository extends BaseRepository<DicData, Long> {
    /**
     * 根据类型标签查询字典数据
     * @param tag
     * @return 字典数据集
     */
    @Query(value="select b.* from sys_dictionary_data b,sys_dictionary_type a where a.type_id=a.id and a.tag=?1)",nativeQuery=true)
    List<DicData> findDictionaryDataByTag(String tag);

    @Query(value="select b.* from sys_dictionary_data b,sys_dictionary_type a where a.type_id=a.id and a.tag=?1 and b.key=?2)",nativeQuery=true)
    DicData findDictionaryDataByKey(String tag, String key);

    @Query(value="select b.name from sys_dictionary_data b,sys_dictionary_type a where a.type_id=a.id and a.tag=?1 and b.key=?2)",nativeQuery=true)
    String findDictionaryDataNameByKey(String tag, String key);

    @Query(value="select b.memo from sys_dictionary_data b,sys_dictionary_type a where a.type_id=a.id and a.tag=?1 and b.key=?2)",nativeQuery=true)
    String findDictionaryDataMemoByKey(String tag, String key);
}

package com.sinosteel.repository;

import com.sinosteel.domain.ZjCompany;
import com.sinosteel.domain.dictionary.ZjDictCommon;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ZjDictCommonRepository extends BaseRepository<ZjDictCommon>{

    ZjDictCommon findById(String id) throws Exception;

    ZjDictCommon findByDicTypeAndCode(String dictType,String code) throws Exception;

    List<ZjDictCommon> findByDicType(String dictType) throws Exception;

}

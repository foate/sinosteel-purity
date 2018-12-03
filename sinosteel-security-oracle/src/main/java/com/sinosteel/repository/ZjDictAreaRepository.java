package com.sinosteel.repository;

import com.sinosteel.domain.dictionary.ZjDictArea;
import com.sinosteel.domain.dictionary.ZjDictCommon;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZjDictAreaRepository extends BaseRepository<ZjDictArea>{

    ZjDictArea findById(String id) throws Exception;

    ZjDictArea findByTypeAndCode(String type, String code) throws Exception;

}

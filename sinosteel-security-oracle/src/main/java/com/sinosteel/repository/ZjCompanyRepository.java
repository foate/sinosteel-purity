package com.sinosteel.repository;

import com.sinosteel.domain.ZjCompany;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ZjCompanyRepository extends BaseRepository<ZjCompany>{

    @Modifying
    @Transactional
    @Query("update ZjCompany zjCompany set zjCompany.companyName=:param_name WHERE zjCompany.id =:id")
    void update(@Param("param_name") String name, @Param("id") int id);

    ZjCompany findById(String id) throws Exception;

    @Query("select zjCompany.id,zjCompany.companyName from ZjCompany zjCompany  WHERE zjCompany.id >=:startId and zjCompany.id <=:endId")
    List<Object[]> findDataByRangeId(@Param("startId") String startId, @Param("endId") String endId) throws Exception;

    ZjCompany findByZzjgdm(String zzjgdm) throws Exception;

    List<ZjCompany> findByLegalPersonNo(String cardId) throws Exception;

    List<ZjCompany> findByCompanyName(String companyName) throws Exception;
}

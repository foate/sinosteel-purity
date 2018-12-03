package com.sinosteel.repository;

import com.sinosteel.domain.Company;
import com.sinosteel.domain.IndustryCompany;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IndustryCompanyRepository extends BaseRepository<IndustryCompany>{

    @Modifying
    @Transactional
    @Query("update IndustryCompany industryCompany set industryCompany.name=:param_name WHERE industryCompany.id =:id")
    void update(@Param("param_name") String name, @Param("id") int id);

    IndustryCompany findById(int id) throws Exception;

    IndustryCompany findByName(String name) throws Exception;


}

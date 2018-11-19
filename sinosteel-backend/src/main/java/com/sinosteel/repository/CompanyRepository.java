package com.sinosteel.repository;

import com.sinosteel.domain.Company;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompanyRepository extends BaseRepository<Company>{

    @Modifying
    @Transactional
    @Query("update Company company set company.name=:param_name WHERE company.id =:id")
    void update(@Param("param_name") String name, @Param("id") int id);

    Company findById(int id) throws Exception;

    Company findByName(String name) throws Exception;


}

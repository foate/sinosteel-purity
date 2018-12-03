package com.sinosteel.repository;

import com.sinosteel.domain.ZjPerson;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ZjPersonRepository extends BaseRepository<ZjPerson>{

    @Modifying
    @Transactional
    @Query("update ZjPerson zjPerson set zjPerson.name=:param_name WHERE zjPerson.id =:id")
    void update(@Param("param_name") String name, @Param("id") int id);

    ZjPerson findById(String id) throws Exception;

    ZjPerson findByZjhm(String zjhm) throws Exception;

    List<ZjPerson> findByOrganName(String organName) throws Exception;
}

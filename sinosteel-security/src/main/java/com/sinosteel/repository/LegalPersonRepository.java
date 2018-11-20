package com.sinosteel.repository;

import com.sinosteel.domain.LegalPerson;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalPersonRepository extends BaseRepository<LegalPerson>{


    LegalPerson findById(int id) throws Exception;

    LegalPerson findByName(String name) throws Exception;


}

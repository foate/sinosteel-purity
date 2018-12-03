package com.sinosteel.repository;

import com.sinosteel.domain.ZjPerson;
import com.sinosteel.domain.ZjProduct;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ZjProductRepository extends BaseRepository<ZjProduct>{

    @Modifying
    @Transactional
    @Query("update ZjProduct zjProduct set zjProduct.productName=:param_name WHERE zjProduct.id =:id")
    void update(@Param("param_name") String name, @Param("id") int id);

    ZjProduct findById(int id) throws Exception;

    List<ZjProduct> findByProductName(String productName) throws Exception;

    List<ZjProduct> findByOrganName(String organName) throws Exception;

    List<ZjProduct> findByZzjgdm(String zzjgdm) throws Exception;
}

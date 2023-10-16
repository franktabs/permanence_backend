package com.afriland.dsi.permanence.repositories;

import com.afriland.dsi.permanence.entities.Personnel;
import com.afriland.dsi.permanence.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PersonnelRepository extends ModelRepository<Personnel, Long> {
    Personnel findByUserId(Long userId);

    @Override
    @Transactional
    @Modifying
    @Query("delete from Personnel p where p.id = ?1")
    int deleteModel(Long id);

    @Query("select p from Personnel p where p.userId = (select MIN(p2.userId) from  Personnel p2 )")
    Personnel findMinUserId();

//    List<Personnel> findByFirstnameLikeIgnoreCase(String firstname);

    List<Personnel> findByFirstnameContainsIgnoreCase(String firstname);





}

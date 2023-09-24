package com.example.demo.repositories;

import com.example.demo.entities.Planning;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlanningRepository extends ModelRepository<Planning, Long> {

    @Override
    @Transactional
    @Modifying
    @Query("delete from Planning p where p.id = ?1")
    int deleteModel(Long aLong);

    @Query(
            value = "select id, sum(apparition) as apparition from ( " +
                    "(select pn.personnel_id as id, count(*) as apparition " +
                    "from (select * from perm_Planning pl where pl.id = ?1 ) as p " +
                    "join perm_Month as m on m.planning_id = p.id " +
                    "join perm_Permanence as per on per.month_id = m.id " +
                    "join perm_Personnel_Nuit as pn on pn.permanence_id = per.id " +
                    "group by pn.personnel_id )" +
                    "union " +
                    "(select pj.personnel_id as id, count(*) as apparition " +
                    "from (select * from perm_Planning pl where pl.id = ?1 ) as p  " +
                    "join perm_Month as m on m.planning_id = p.id " +
                    "join perm_Permanence as per on per.month_id = m.id " +
                    "join perm_Personnel_Jour as pj on pj.permanence_id = per.id " +
                    "group by pj.personnel_id " +
                    ") " +
                    ") as personne " +
                    "group by id ",
            nativeQuery = true
    )
    List<Object[]> countAllPersonnelsPlanning(Long id);


}

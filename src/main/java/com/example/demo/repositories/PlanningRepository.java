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
            value = "select idPerson, sum(apparition) as apparition from ( " +
                    "select pn.personnel as idPerson, count(*) as apparition " +
                    "from Planning as p " +
                    "join Month as m on m.planning_id = p.id " +
                    "join Permanence as per on per.month_id = m.id " +
                    "join Personnel_Nuit as pn on pn.permanence_id = per.id " +
                    "where p.id = ?1 " +
                    "group by pn.personnel " +
                    "union " +
                    "select pj.personnel as idPerson, count(*) as apparition " +
                    "from Planning as p " +
                    "join Month as m on m.planning_id = p.id " +
                    "join Permanence as per on per.month_id = m.id " +
                    "join Personnel_Jour as pj on pj.permanence_id = per.id " +
                    "where p.id = ?1 " +
                    "group by pj.personnel " +
                    ") " +
                    "group by idPerson ",
            nativeQuery = true
    )
    List<Object[]> countAllPersonnels(Long id);


}

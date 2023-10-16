package com.afriland.dsi.permanence.repositories.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@NoRepositoryBean
public interface ModelRepository<T, ID> extends JpaRepository<T, ID> {

    int deleteModel(ID id);

}

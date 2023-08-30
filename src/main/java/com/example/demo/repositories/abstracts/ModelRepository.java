package com.example.demo.repositories.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ModelRepository<T, ID> extends JpaRepository<T, ID> {


    @Transactional
    @Modifying
    int deleteModel(ID id);

}

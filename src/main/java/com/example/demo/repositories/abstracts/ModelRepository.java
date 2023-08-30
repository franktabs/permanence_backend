package com.example.demo.repositories.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository<T, ID> extends JpaRepository<T, ID> {

    int deleteModel(ID id);

}

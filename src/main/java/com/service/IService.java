package com.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<T> {
    Page<T> findAll(Pageable pageable);

    void save(T model);

    void remove(Long id);

    T findById (Long id);
}

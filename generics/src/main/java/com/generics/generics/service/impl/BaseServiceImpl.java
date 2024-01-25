package com.generics.generics.service.impl;

import com.generics.generics.repository.BaseRepository;
import com.generics.generics.service.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseServiceImpl<T, ID> implements BaseService<T, ID> {


    private final JpaRepository<T, ID> baseRepository;

    public BaseServiceImpl(JpaRepository<T, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public T save(T t) {
        return baseRepository.save(t);
    }

    @Override
    public T findById(ID id) {
        return baseRepository.findById(id).get();
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public String deleteById(ID id) {
        baseRepository.deleteById(id);
        return "Deleted";
    }

    @Override
    public String delete(T t) {
        baseRepository.delete(t);
        return "Deleted";
    }
}

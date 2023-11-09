package com.example.demoapi.model.dao;

import com.example.demoapi.model.entity.CatPaises;
import org.springframework.data.repository.CrudRepository;

public interface CatPaisDao extends CrudRepository<CatPaises,Integer> {
}

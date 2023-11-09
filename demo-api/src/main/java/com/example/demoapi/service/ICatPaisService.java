package com.example.demoapi.service;

import com.example.demoapi.model.dto.CatPaisDto;
import com.example.demoapi.model.entity.CatPaises;

import java.util.List;
public interface ICatPaisService {
    List<CatPaises> listAll();
    CatPaises save(CatPaisDto catPais);
    CatPaises findById(Integer id);
    void delete(CatPaises catPaises);
    boolean existsById(Integer id);

}
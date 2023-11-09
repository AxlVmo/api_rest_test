package com.example.demoapi.service.impl;
import com.example.demoapi.model.dao.CatPaisDao;
import com.example.demoapi.model.dto.CatPaisDto;
import com.example.demoapi.model.entity.CatPaises;
import com.example.demoapi.service.ICatPaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CatPaisImplService implements ICatPaisService {

    @Autowired
    private CatPaisDao catPaisDao;

    @Override
    public List listAll() {
        return (List) catPaisDao.findAll();
    }

    @Transactional()
    @Override
    public CatPaises save(CatPaisDto catPaisDto) {
        CatPaises catPais = CatPaises.builder()
                .IdPais(catPaisDto.getIdPais())
                .IdCnbv(catPaisDto.getIdCnbv())
                .IdConsar(catPaisDto.getIdConsar())
                .Descripcion(catPaisDto.getDescripcion())
                .build();
        return catPaisDao.save(catPais);
    }
    @Transactional(readOnly = true)
    @Override
    public CatPaises findById(Integer id) {
        return catPaisDao.findById(id).orElse(null);
    }
    @Transactional()
    @Override
    public void delete(CatPaises catPaises) {
        catPaisDao.delete(catPaises);
    }
    @Override
    public boolean existsById(Integer id) {
        return catPaisDao.existsById(id);
    }
}

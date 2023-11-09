package com.example.demoapi.controller;

import com.example.demoapi.model.MensajeResponse;
import com.example.demoapi.model.dto.CatPaisDto;
import com.example.demoapi.model.entity.CatPaises;
import com.example.demoapi.service.ICatPaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CatPaisController {

    @Autowired
    private ICatPaisService catPaisService;

    @GetMapping("/paises")
    public ResponseEntity<?> showAll() {
        List<CatPaises> getList = catPaisService.listAll();
        if (getList == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .build()
                    , HttpStatus.OK);
        }

        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(getList)
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping("/paises")
    public ResponseEntity<?> create(@RequestBody CatPaisDto catPaisDto) {
        CatPaises catPaisSave = null;
        try {
            catPaisSave = catPaisService.save(catPaisDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(CatPaisDto.builder()
                            .IdPais(catPaisSave.getIdPais())
                            .IdCnbv(catPaisSave.getIdCnbv())
                            .IdConsar(catPaisSave.getIdConsar())
                            .Descripcion(catPaisSave.getDescripcion())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/pais/{id}")
    public ResponseEntity<?> update(@RequestBody CatPaisDto catPaisDto, @PathVariable Integer id) {
        CatPaises catPaisUpdate = null;
        try {
            if (catPaisService.existsById(id)) {
                catPaisDto.setIdPais(id);
                catPaisUpdate = catPaisService.save(catPaisDto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Actualizado correctamente")
                        .object(CatPaisDto.builder()
                                .IdPais(catPaisUpdate.getIdPais())
                                .IdCnbv(catPaisUpdate.getIdCnbv())
                                .IdConsar(catPaisUpdate.getIdConsar())
                                .Descripcion(catPaisUpdate.getDescripcion())
                                .build())
                        .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                                .object(null)
                                .build()
                        , HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/pais/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            CatPaises CatPaisDelete = catPaisService.findById(id);
            catPaisService.delete(CatPaisDelete);
            return new ResponseEntity<>(CatPaisDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/pais/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        CatPaises catPaises = catPaisService.findById(id);

        if (catPaises == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta buscar, no existe!!")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(CatPaisDto.builder()
                                .IdPais(catPaises.getIdPais())
                                .IdCnbv(catPaises.getIdCnbv())
                                .IdConsar(catPaises.getIdConsar())
                                .Descripcion(catPaises.getDescripcion())
                                .build())
                        .build()
                , HttpStatus.OK);
    }
}

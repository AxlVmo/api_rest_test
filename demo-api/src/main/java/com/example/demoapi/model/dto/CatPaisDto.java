package com.example.demoapi.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
@Builder
public class CatPaisDto implements Serializable {

    private Integer IdPais;
    private Integer IdCnbv;
    private Integer IdConsar;
    private String Descripcion;
}

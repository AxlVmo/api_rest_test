package com.example.demoapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "cat_paises" )
public class CatPaises implements Serializable {
    @Id
    @Column(name = "id_pais")
    @GeneratedValue(strategy = GenerationType.AUTO, generator= "native")
    private Integer IdPais;
    @Column(name = "id_cnbv")
    private Integer IdCnbv;
    @Column(name = "id_consar")
    private Integer IdConsar;
    @Column(name = "descripcion")
    private String Descripcion;
}

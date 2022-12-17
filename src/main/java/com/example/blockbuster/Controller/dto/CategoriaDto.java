package com.example.blockbuster.Controller.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.blockbuster.Model.Categoria} entity
 */
@Data
public class CategoriaDto implements Serializable {
    private Long id;
    private String titulo;
    private String cor;

}
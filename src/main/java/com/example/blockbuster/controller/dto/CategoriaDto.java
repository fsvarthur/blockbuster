package com.example.blockbuster.controller.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.blockbuster.model.Categoria} entity
 */
@Data
public class CategoriaDto implements Serializable {
    private Long id;
    private String titulo;
    private String cor;

}
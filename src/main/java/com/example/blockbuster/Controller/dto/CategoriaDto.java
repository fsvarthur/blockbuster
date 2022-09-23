package com.example.blockbuster.Controller.dto;

import com.example.blockbuster.Model.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaDto {
    private Long id;
    private String titulo;
    private String cor;

    private CategoriaDto(Categoria categoria){
        this.id = categoria.getId();
        this.titulo = categoria.getTitulo();
        this.cor = categoria.getCor();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCor() {
        return cor;
    }

    public static List<CategoriaDto> converter(List<Categoria> categoria){
        return categoria.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }
}

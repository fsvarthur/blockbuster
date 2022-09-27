package com.example.blockbuster.Controller.dto;

import com.example.blockbuster.Model.Categoria;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class CategoriaResDto {

    private Long id;
    private String titulo;
    private String cor;

    public CategoriaResDto(Long id, String titulo, String cor) {
        this.id = id;
        this.titulo = titulo;
        this.cor = cor;
    }

    public CategoriaResDto(Categoria categoria){
        this.id = categoria.getId();
        this.titulo = categoria.getTitulo();
        this.cor = categoria.getCor();
    }

    public static List<CategoriaResDto> listar(List<Categoria> all) {
        return all.stream().map(CategoriaResDto::new).collect(Collectors.toList());
    }

}

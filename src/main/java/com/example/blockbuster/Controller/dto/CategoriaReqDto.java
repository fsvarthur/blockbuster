package com.example.blockbuster.Controller.dto;

import com.example.blockbuster.Model.Categoria;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoriaReqDto {

    @NotNull @NotEmpty
    private String titulo;

    @NotNull @NotEmpty
    private String cor;

    public CategoriaReqDto(String titulo, String cor) {
        this.titulo = titulo;
        this.cor = cor;
    }

    public CategoriaReqDto(Categoria categoria){
        this.titulo = categoria.getTitulo();
        this.cor = categoria.getCor();
    }

    public Categoria toEntity() {
        Categoria categoria = new Categoria(this.titulo, this.cor);
        return categoria;
    }

    private List<CategoriaReqDto> listDto(List<Categoria> categoria){
        return categoria.stream().map(CategoriaReqDto::new).collect(Collectors.toList());
    }
}

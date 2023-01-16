package com.example.blockbuster.service;

import com.example.blockbuster.controller.dto.CategoriaDto;
import com.example.blockbuster.model.Categoria;

import java.util.Optional;

public interface CategoriaService {


    public Iterable<Categoria> findAll();

    public Optional<Categoria> findById(String id);

    public Optional<Categoria> createCategoria(CategoriaDto catDto);

    public void deleteCategoriaById(String id);

    public Optional<Categoria> updateCategoria(String id, CategoriaDto categoriaDto);


}

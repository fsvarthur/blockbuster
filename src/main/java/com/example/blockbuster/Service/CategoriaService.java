package com.example.blockbuster.Service;

import com.example.blockbuster.Controller.dto.CategoriaDto;
import com.example.blockbuster.Model.Categoria;

import java.util.Optional;

public interface CategoriaService {


    public Iterable<Categoria> findAll();

    public Optional<Categoria> findById(String id);

    public Optional<Categoria> createCategoria(CategoriaDto catDto);

    public void deleteCategoriaById(String id);

    public void updateCategoria(String id, CategoriaDto categoriaDto);


}

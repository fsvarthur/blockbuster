package com.example.blockbuster.Service;

import com.example.blockbuster.Controller.dto.CategoriaDto;
import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Repository.CategoriaRepository;
import com.example.blockbuster.Repository.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {


    public Iterable<Categoria> findAll();

    public Optional<Categoria> findById(String id);

    public Optional<Categoria> createCategoria(CategoriaDto catDto) ;

    public void deleteCategoriaById(String id) ;
    public void updateCategoria(String id, CategoriaDto categoriaDto);



}

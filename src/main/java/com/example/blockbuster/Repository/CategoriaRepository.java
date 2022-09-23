package com.example.blockbuster.Repository;

import com.example.blockbuster.Model.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
    List<Categoria> findByTitulo(String search);
}

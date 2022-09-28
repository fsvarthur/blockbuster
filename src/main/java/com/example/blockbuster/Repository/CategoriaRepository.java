package com.example.blockbuster.Repository;

import com.example.blockbuster.Model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Page<Categoria> findByTitulo(String query, Pageable paginacao);
}

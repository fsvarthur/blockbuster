package com.example.blockbuster.Repository;

import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Model.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideoRepository extends CrudRepository<Video, Long> {
    <U> U findByCategoria(Categoria categoria);
}

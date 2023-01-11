package com.example.blockbuster.repository;

import com.example.blockbuster.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {

    /*@Query("select o from Video o join o.categoria u where u.id = :id")
    List<Video> findByCategoriaId(@Param("categoria_id") Long id);*/


}

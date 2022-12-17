package com.example.blockbuster.Repository;

import com.example.blockbuster.Model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends CrudRepository<Video, Long> {

    @Query("select o from Video o join o.categoria u where u.id = :id")
    List<Video> findByCategoriaId(@Param("categoria_id") Long id);


}

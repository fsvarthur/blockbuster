package com.example.blockbuster.repository;

import com.example.blockbuster.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends CrudRepository<Video, Long> {

    /*@Query("select o from Video o join o.categoria u where u.id = :id")
    List<Video> findByCategoriaId(@Param("categoria_id") Long id);*/

    @Query(
            value = "SELECT * FROM Video u WHERE u.titulo = ?titulo",
        nativeQuery = true)
    List<Video> findVideo_byQuery( String titulo);


}

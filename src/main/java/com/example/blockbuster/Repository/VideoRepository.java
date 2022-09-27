package com.example.blockbuster.Repository;

import com.example.blockbuster.Model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    //Make the query automatic
    List<Video> findByCategoriaId(Long id);
}

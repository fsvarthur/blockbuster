package com.example.blockbuster.Service;

import com.example.blockbuster.Model.Video;
import com.example.blockbuster.Repository.CategoriaRepository;
import com.example.blockbuster.Repository.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private static final Logger log = LoggerFactory.getLogger(VideoService.class);

    private CategoriaRepository categoriaRepository;
    private VideoRepository videoRepository;

    public VideoService(CategoriaRepository categoriaRepository, VideoRepository videoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.videoRepository = videoRepository;
    }

    public List<Video> findByCategoriaId(Long id) {
        return null;
    }

    List<Video> findByCategoriaId(Long id){}

    public Page<Video> findAll(){}

    public List<Video> findByTitulo(String query){}

    public Page<Video> findByTitulo(String query, Pageable paginacao){}

    public Optional<Video> findById(Long id) {
    }

    public void save(Video video) {
    }

    public void deleteById(Long id) {

    }
}

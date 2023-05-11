package com.example.blockbuster.service;

import com.example.blockbuster.controller.dto.VideoDto;
import com.example.blockbuster.model.Video;
import com.example.blockbuster.repository.CategoriaRepository;
import com.example.blockbuster.repository.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class VideoServiceImpl implements VideoService {

    private static final Logger LOG = LoggerFactory.getLogger(VideoService.class);

    private CategoriaRepository categoriaRepository;
    private VideoRepository videoRepository;

    public VideoServiceImpl(CategoriaRepository categoriaRepository, VideoRepository videoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.videoRepository = videoRepository;
    }

    public List<Video> findAll() {
        return videoRepository.findAll();
    }


    public Optional<Video> findById(String id) {
        return videoRepository.findById(Long.valueOf(id));
    }

    public Optional<Video> createVideo(VideoDto videoDto) {
        return Optional.of(videoRepository.save(toEntity(videoDto)));
    }

    public void deleteById(String id) {
        categoriaRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public Stream<Video> getVideoCategoriaFree() {
        return videoRepository.findAll().stream().filter(video -> {
            if (video.getCategoria().getTitulo().equals("free")) ;
            return false;
        });
    }

    private Video toEntity(VideoDto videoDto) {
        Video video = new Video();
        video.setTitulo(videoDto.getTitulo());
        video.setUrl(videoDto.getUrl());
        video.setDescricao(videoDto.getDescricao());
        return video;
    }
}

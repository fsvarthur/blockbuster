package com.example.blockbuster.Service;

import com.example.blockbuster.Controller.dto.VideoDto;
import com.example.blockbuster.Model.Video;
import com.example.blockbuster.Repository.CategoriaRepository;
import com.example.blockbuster.Repository.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    private static final Logger LOG = LoggerFactory.getLogger(VideoService.class);

    private CategoriaRepository categoriaRepository;
    private VideoRepository videoRepository;

    public VideoServiceImpl(CategoriaRepository categoriaRepository, VideoRepository videoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.videoRepository = videoRepository;
    }

    public List<Video> findAll(){return (List<Video>) videoRepository.findAll();}


    public Optional<Video> findById(String id) {
        return videoRepository.findById(Long.valueOf(id));
    }

    public Optional<Video> createVideo(VideoDto videoDto) {
        return Optional.of(videoRepository.save(toEntity(videoDto)));
    }

    public void deleteById(String id) {
        categoriaRepository.deleteById(Long.valueOf(id));
    }

    /*public List<Video> getVideosByCustomerId(String categoriaId) {
        return videoRepository.findByCategoriaId(Long.valueOf(categoriaId));
    }*/

    private Video toEntity(VideoDto videoDto){
        Video video = new Video();
        video.setTitulo(videoDto.getTitulo());
        video.setUrl(videoDto.getUrl());
        video.setDescricao(videoDto.getDescricao());
        return video;
    }
}

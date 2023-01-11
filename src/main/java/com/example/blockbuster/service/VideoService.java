package com.example.blockbuster.service;

import com.example.blockbuster.controller.dto.VideoDto;
import com.example.blockbuster.model.Video;

import java.util.List;
import java.util.Optional;


public interface VideoService {

    public List<Video> findAll();

    public Optional<Video> findById(String id);

    public Optional<Video> createVideo(VideoDto videoDto);

    public void deleteById(String id);
    //public List<Video> getVideosByCustomerId(String categoriaId);

}

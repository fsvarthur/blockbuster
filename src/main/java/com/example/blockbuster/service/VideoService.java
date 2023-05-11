package com.example.blockbuster.service;

import com.example.blockbuster.controller.dto.VideoDto;
import com.example.blockbuster.model.Video;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public interface VideoService {

    List<Video> findAll();

    Optional<Video> findById(String id);

    Optional<Video> createVideo(VideoDto videoDto);

    void deleteById(String id);

    Stream<Video> getVideoCategoriaFree();
}

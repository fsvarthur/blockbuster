package com.example.blockbuster.Service;

import com.example.blockbuster.Controller.dto.VideoDto;
import com.example.blockbuster.Model.Video;

import java.util.List;
import java.util.Optional;


public interface VideoService {

    public List<Video> findAll();

    public Optional<Video> findById(String id);

    public Optional<Video> createVideo(VideoDto videoDto);

    public void deleteById(String id);
    //public List<Video> getVideosByCustomerId(String categoriaId);

}

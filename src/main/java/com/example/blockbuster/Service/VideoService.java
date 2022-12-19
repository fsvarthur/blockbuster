package com.example.blockbuster.Service;

import com.example.blockbuster.Model.Video;
import com.example.blockbuster.Controller.dto.VideoDto;
import com.example.blockbuster.Repository.CategoriaRepository;
import com.example.blockbuster.Repository.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface VideoService {

    public List<Video> findAll();
    public Optional<Video> findById(String id);
    public Optional<Video> createVideo(VideoDto videoDto);
    public void deleteById(String id);
    //public List<Video> getVideosByCustomerId(String categoriaId);

}

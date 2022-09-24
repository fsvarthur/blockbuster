package com.example.blockbuster.Controller;

import com.example.blockbuster.Model.Video;
import com.example.blockbuster.Repository.*;
import com.example.blockbuster.VideoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
class VideosController {

    @Autowired
    private VideoRepository videoRepository;

    public VideosController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @GetMapping
    public Iterable<Video> getAllVideos(){
         return videoRepository.findAll();
    }


    @GetMapping("/{id}")
    public Video getVideoById(@PathVariable Long id){
        return videoRepository.findById(id).orElseThrow(() -> new VideoNotFoundException(id));
    }

    @PostMapping
    public Video addNewVideo( @RequestBody Video video){
        return videoRepository.save(video);
    }

    @PutMapping("/{id}")
    public Video updateNewVideo(@RequestBody Video newVideo, @PathVariable Long id){
        return videoRepository.findById(id)
                .map( video ->{
                    video.setTitulo(newVideo.getTitulo());
                    video.setUrl(newVideo.getUrl());
                    video.setCategoria(newVideo.getCategoria());
                    video.setDescricao(newVideo.getDescricao());
                    return videoRepository.save(video);
                }).orElseGet(()->{
                    newVideo.setId(id);
                    return videoRepository.save(newVideo);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteVideo(@PathVariable Long id){
        videoRepository.deleteById(id);
    }

}

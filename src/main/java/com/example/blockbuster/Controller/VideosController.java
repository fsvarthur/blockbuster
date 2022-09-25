package com.example.blockbuster.Controller;

import com.example.blockbuster.Controller.dto.VideoReqDto;
import com.example.blockbuster.Controller.dto.VideoResDto;
import com.example.blockbuster.Model.Video;
import com.example.blockbuster.Repository.*;
import com.example.blockbuster.VideoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/videos")
class VideosController {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public VideosController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @GetMapping
    public List<VideoResDto> getAllVideos(){
        return VideoResDto.listar(videoRepository.findAll());
    }

    @GetMapping("/{id}")
    public Video getVideoById(@PathVariable Long id){
        return videoRepository.findById(id).orElseThrow(() -> new VideoNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<VideoReqDto> addNewVideo(@RequestBody @Valid VideoReqDto formVideo, UriComponentsBuilder builder) {
        Video video = formVideo.toEntity(categoriaRepository);
        videoRepository.save(video);

        URI uri = builder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoReqDto(video));
    }

    @PutMapping("/{id}")
    public Video updateNewVideo(@RequestBody Video newVideo, @PathVariable Long id){
        return videoRepository.findById(id)
                .map( video ->{
                    video.setTitulo(newVideo.getTitulo());
                    video.setUrl(newVideo.getUrl());
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

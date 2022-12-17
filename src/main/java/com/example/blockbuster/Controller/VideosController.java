package com.example.blockbuster.Controller;

import com.example.blockbuster.Model.Video;
import com.example.blockbuster.Controller.dto.VideoDto;
import com.example.blockbuster.Service.CategoriaService;
import com.example.blockbuster.Service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;


@RestController
@RequestMapping("/videos/v1/videos")
class VideosController {
    private static final Logger LOG = LoggerFactory.getLogger(VideosController.class);
    private VideoService videoService;
    private CategoriaService categoriaService;

    public VideosController(VideoService videoService, CategoriaService categoriaService) {
        this.videoService = videoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Video>> getAllVideos() {
        return ok(videoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideoById(@Valid String id){
        LOG.debug("/video returned the found video for videoId={}",id);
        return videoService.findById(id).map(ResponseEntity::ok).orElse(notFound().build());
    }

    //TODO get free videos
    /*@GetMapping("/free")
    public List<VideoDto> getVideosFree(){
        List<Video> video = (List<Video>) videoService.findAll();
        return VideoDto.listar(video);
    }*/

    //TODO get with query titulo
    /*@GetMapping(params = "titulo")
    public List<VideoDto> getVideoByName(@RequestParam(required = true) String query){
        return VideoDto.listar(videoService.findByTitulo(query));
    }*/

    @PostMapping
    public ResponseEntity<Video> addNewVideo(@RequestBody @Valid VideoDto videoDto) {
        return status(HttpStatus.CREATED).body(videoService.createVideo(videoDto).get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDto> updateVideo(String id,@RequestBody @Valid VideoDto newVideo){
        Optional<Video> video = videoService.findById(id);
        if(video.isPresent()){
            videoService.createVideo(newVideo);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(String id){
        videoService.deleteById(id);
        return accepted().build();
    }

}

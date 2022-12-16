package com.example.blockbuster.Controller;

import com.example.blockbuster.Controller.dto.VideoDto;
import com.example.blockbuster.Exception.VideoNotFoundException;
import com.example.blockbuster.Model.Video;
import com.example.blockbuster.Service.CategoriaService;
import com.example.blockbuster.Service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


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
    public Page<VideoDto> getAllVideos(@RequestParam(required = false) String video,
                                       @PageableDefault(sort="titulo", direction = Direction.DESC,
                                                  page=0,size =10) Pageable paginacao) {

        if(video == null){
            try{
                Page<Video> videos = videoService.findAll();
                return VideoDto.converter(videos);
            } catch (VideoNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Video not found");
            }
        }else{
            Page<Video> videos = videoService.findByTitulo(video, paginacao);
            return VideoDto.converter(videos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getVideoById(@PathVariable Long id){
        LOG.debug("/video returned the found video for videoId={}",id);
        Optional<Video> video = videoService.findById(id);
        if(video.isPresent()){
            return new ResponseEntity<>(new VideoDto(video.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/free")
    public List<VideoDto> getVideosFree(){
        List<Video> video = (List<Video>) videoService.findAll();
        return VideoDto.listar(video);
    }

    //to_do Get mapping with search param
    @GetMapping(params = "titulo")
    public List<VideoDto> getVideoByName(@RequestParam(required = true) String query){
        return VideoDto.listar(videoService.findByTitulo(query));
    }

    @PostMapping
    public ResponseEntity<VideoDto> addNewVideo(@RequestBody @Valid VideoDto formVideo, UriComponentsBuilder builder) {
        Video video = formVideo.toEntity(categoriaService);
        videoService.save(video);

        URI uri = builder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoDto(video));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDto> updateVideo(@PathVariable Long id,@RequestBody @Valid VideoDto newVideo){
        Optional<Video> video = videoService.findById(id);
        if(video.isPresent()){
            Video entity = video.get();
            entity.setDescricao(newVideo.getDescricao());
            entity.setTitulo(newVideo.getTitulo());
            entity.setUrl(newVideo.getUrl());
            videoService.save(entity);
            return new ResponseEntity<>(new VideoDto(entity), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long id){
        Optional<Video> video = videoService.findById(id);
        if(video.isPresent()){
            videoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

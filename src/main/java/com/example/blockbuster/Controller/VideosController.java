package com.example.blockbuster.Controller;

import com.example.blockbuster.Controller.dto.VideoReqDto;
import com.example.blockbuster.Controller.dto.VideoResDto;
import com.example.blockbuster.Model.Video;
import com.example.blockbuster.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videos")
class VideosController {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<VideoResDto> getAllVideos(){
        return VideoResDto.listar(videoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoResDto> getVideoById(@PathVariable Long id){
        Optional<Video> video = videoRepository.findById(id);
        if(video.isPresent()){
            return new ResponseEntity<>(new VideoResDto(video.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //to_do Get mapping with search param

    @PostMapping
    public ResponseEntity<VideoReqDto> addNewVideo(@RequestBody @Valid VideoReqDto formVideo, UriComponentsBuilder builder) {
        Video video = formVideo.toEntity(categoriaRepository);
        videoRepository.save(video);

        URI uri = builder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoReqDto(video));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoReqDto> updateVideo(@PathVariable Long id,@RequestBody @Valid VideoReqDto newVideo){
        Optional<Video> video = videoRepository.findById(id);
        if(video.isPresent()){
            Video entity = video.get();
            entity.setDescricao(newVideo.getDescricao());
            entity.setTitulo(newVideo.getTitulo());
            entity.setUrl(newVideo.getUrl());
            videoRepository.save(entity);
            return new ResponseEntity<>(new VideoReqDto(entity), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long id){
        Optional<Video> video = videoRepository.findById(id);
        if(video.isPresent()){
            videoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

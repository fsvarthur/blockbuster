package com.example.blockbuster.Controller;

import com.example.blockbuster.Controller.dto.VideoReqDto;
import com.example.blockbuster.Controller.dto.VideoResDto;
import com.example.blockbuster.Exception.VideoNotFoundException;
import com.example.blockbuster.Model.Video;
import com.example.blockbuster.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/videos")
class VideosController {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public Page<VideoResDto> getAllVideos(@RequestParam(required = false) String video,
                                          @PageableDefault(sort="titulo", direction = Direction.DESC,
                                                  page=0,size =10) Pageable paginacao) {
        if(video == null){
            try{
                Page<Video> videos = videoRepository.findAll(paginacao);
                return VideoResDto.converter(videos);
            } catch (VideoNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Video not found");
            }
        }else{
            Page<Video> videos = videoRepository.findByTitulo(video, paginacao);
            return VideoResDto.converter(videos);
        }
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

    @GetMapping("/free")
    public List<VideoResDto> getVideosFree(){
        List<Video> video = videoRepository.findAll();
        return VideoResDto.listar(video);
    }

    //to_do Get mapping with search param
    @GetMapping(params = "titulo")
    public List<VideoResDto> getVideoByName(@RequestParam(required = true) String query){
        return VideoResDto.listar(videoRepository.findByTitulo(query));
    }

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

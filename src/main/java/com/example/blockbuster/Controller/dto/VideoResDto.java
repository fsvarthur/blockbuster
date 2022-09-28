package com.example.blockbuster.Controller.dto;

import com.example.blockbuster.Model.Video;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class VideoResDto {

    private Long id;

    private String titulo;
    private String descricao;
    private String url;

    private String categoria;

    public VideoResDto(Long id, String titulo, String descricao, String url, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.categoria = categoria;
    }

    public VideoResDto(Video video){
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.categoria = video.getCategoria().getTitulo();
    }

    public static List<VideoResDto> listar(List<Video> videos) {
        return videos.stream().map(VideoResDto::new).collect(Collectors.toList());
    }

    public static Page<VideoResDto> converter(Page<Video> videos){
        return videos.map(VideoResDto::new);
    }
}

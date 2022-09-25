package com.example.blockbuster.Controller.dto;

import com.example.blockbuster.Model.Video;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class VideoResDto {

    private Long id;

    private String titulo;
    private String descricao;
    private String url;

    private Long categoriaId;

    public VideoResDto(String titulo, String descricao, String url, Long categoriaId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.categoriaId = categoriaId;
    }

    public VideoResDto(Video video){
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.categoriaId = video.getCategoria().getId();
    }

    public static List<VideoResDto> listar(List<Video> videos) {
        return videos.stream().map(VideoResDto::new).collect(Collectors.toList());
    }
}

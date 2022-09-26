package com.example.blockbuster.Controller.dto;

import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Model.Video;
import com.example.blockbuster.Repository.CategoriaRepository;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class VideoReqDto {

    private static final Long CATEGORIA_LIVRE = 1L;

    @NotNull @NotEmpty
    private String titulo;

    @NotNull @NotEmpty
    private String descricao;

    @NotNull @NotEmpty
    private String url;

    private Long categoriaId;

    public VideoReqDto(String titulo, String descricao, String url, Long categoriaId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.categoriaId = categoriaId;
    }

    public VideoReqDto(Video video){
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.categoriaId = video.getCategoria().getId();
    }

    public Video toEntity(CategoriaRepository categoriaRepository){
        if(categoriaId == null){
            categoriaId = CATEGORIA_LIVRE;
        }
        Categoria categoria = categoriaRepository.getReferenceById(categoriaId);
        Video video = new Video(this.titulo, this.descricao, this.url);
        video.setCategoria(categoria);
        return video;
    }

    private List<VideoReqDto> listDto(List<Video> video){
        return video.stream().map(VideoReqDto::new).collect(Collectors.toList());
    }
}

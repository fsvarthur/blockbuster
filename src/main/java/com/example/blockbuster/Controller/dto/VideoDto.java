package com.example.blockbuster.Controller.dto;

import com.example.blockbuster.Model.Video;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Video} entity
 */
@Data
public class VideoDto implements Serializable {
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private CategoriaDto categoria;
}
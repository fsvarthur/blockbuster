package com.example.blockbuster.controller.dto;

import com.example.blockbuster.model.Video;
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
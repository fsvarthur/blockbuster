package com.example.blockbuster.Controller.dto;

import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Model.Video;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Video} entity
 */
@Data
public class VideoDto implements Serializable {
    private final Long id;
    private final String titulo;
    private final String descricao;
    private final String url;
    private final CategoriaDto categoria;

}
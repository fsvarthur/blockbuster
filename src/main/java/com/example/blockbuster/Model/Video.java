package com.example.blockbuster.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;
    private String url;

    @OneToOne
    @JoinColumn(name = "categoriaId", referencedColumnName = "id")
    private Categoria categoria;

    public Video() {
    }

    public Video(String titulo, String descricao, String url, Categoria categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video) o;

        if (!Objects.equals(id, video.id)) return false;
        if (!Objects.equals(titulo, video.titulo)) return false;
        if (!Objects.equals(descricao, video.descricao)) return false;
        if (!Objects.equals(url, video.url)) return false;
        return Objects.equals(categoria, video.categoria);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

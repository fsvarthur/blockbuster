package com.example.blockbuster.Controller.form;

import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Repository.CategoriaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizarForm {

    @NotNull @NotEmpty
    private String titulo;

    @NotNull @NotEmpty
    private String cor;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Categoria atualizar(Long id, CategoriaRepository categoriaRepository){
        Categoria categoria = categoriaRepository.getReferenceById(id);

        categoria.setTitulo(this.titulo);
        categoria.setCor(this.cor);
        return categoria;
    }
}

package com.example.blockbuster.Controller.form;

import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Repository.CategoriaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizarForm {

    @NotNull @NotEmpty
    private String titulo;

    @NotNull @NotEmpty
    private String url;





    public Video atualizar(Long id, CategoriaRepository categoriaRepository){
        Categoria categoria = categoriaRepository.getReferenceById(id);

        categoria.setTitulo(this.titulo);
        categoria.setCor(this.cor);
        return categoria;
    }
}

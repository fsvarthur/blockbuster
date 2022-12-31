package com.example.blockbuster.Repository;

import com.example.blockbuster.Model.Categoria;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoriaRepositoryTest {
    @Autowired
    private CategoriaRepository categoriaRepository;


    private final String TITULO = "Adventure";
    private final String COR = "#451525";

    @Test
    public void itShouldCheckWhenCategoriaSaves(){
        Categoria categoria = new Categoria();
        categoria.setTitulo(TITULO);
        categoria.setCor(COR);
        categoriaRepository.save(categoria);
        List<Categoria> returned = categoriaRepository.findAll();
        assertThat(returned).isNotNull();
    }
}
package com.example.blockbuster.repository;

import com.example.blockbuster.model.Categoria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoriaRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CategoriaRepository categoriaRepository;


    private final String TITULO = "Adventure";
    private final String COR = "#451525";

    @Test
    public void itShouldCheckWhenCategoriaSaves(){
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setTitulo(TITULO);
        categoria.setCor(COR);
        categoria = testEntityManager.persistAndFlush(categoria);
        assertThat(categoriaRepository.findById(categoria.getId()).get()).isEqualTo(categoria);
    }
}
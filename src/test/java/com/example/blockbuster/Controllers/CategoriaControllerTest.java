package com.example.blockbuster.Controllers;

import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Repository.CategoriaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoriaControllerTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private TestEntityManager em;

    private static Long ID_CATEGORIA_LIVRE = 1L;
    private static String TITULO_CATEGORIA = "Categoria Aventura";


    @Test
    public void existeCategoriaLivre(){
        Categoria categoria = categoriaRepository.getOne(ID_CATEGORIA_LIVRE);
        Assert.assertNotNull(categoria);
        Assert.assertTrue(categoria.getTitulo().equals("Livre"));
        Assert.assertTrue(categoria.getCor().equals("#FF0000"));
    }
}

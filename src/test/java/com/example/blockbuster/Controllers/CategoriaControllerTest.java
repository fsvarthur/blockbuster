package com.example.blockbuster.Controllers;


import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Repository.CategoriaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class CategoriaControllerTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private TestEntityManager em;

    private static Long ID_CATEGORIA_LIVRE = 1L;
    private static String TITULO_CATEGORIA = "Categoria teste";

    //existirCategoriaLivre
    /*@Test
    public void existirCategoriaLivre(){
        Categoria categoria = this.salvarCategoria();
        categoria.setId(ID_CATEGORIA_LIVRE);
        categoriaRepository.save(categoria);

        Assert.assertNotNull(categoria);
        Assert.assertTrue(categoria.getId() == ID_CATEGORIA_LIVRE);
    }

    //cadastrarCategoria
    @Test
    public void cadastrarCategoria(){
        Categoria categoria = this.salvarCategoria();

        Assert.assertNotNull(categoria);
        Assert.assertTrue(categoria.getId()>0);
        Long idCategoria = categoria.getId();

        Categoria categoriaConsultada = categoriaRepository.getReferenceById(idCategoria);
        Assert.assertNotNull(categoriaConsultada.getId());
        Assert.assertTrue(categoriaConsultada.getId().longValue() == categoria.getId().longValue());
    }

    //alterarCategoria
    @Test
    public void alterarCategoria(){
        Categoria categoria = this.salvarCategoria();
        categoria.setTitulo("Adventure");

        categoriaRepository.save(categoria);
        Assert.assertNotNull(categoria);

        Long idCategoria = categoria.getId();
        Categoria categoriaConsultada = categoriaRepository.getReferenceById(idCategoria);
        Assert.assertNotNull(categoriaConsultada.getId());
        Assert.assertTrue(categoriaConsultada.getTitulo() == TITULO_CATEGORIA);

    }

    //excluirCategoria
    @Test
    public void exluirCategoria(){
        Categoria categoria = this.salvarCategoria();
        Long ID_CATEGORIA = categoria.getId();

        Optional<Categoria> categoriaSalva = categoriaRepository.findById(ID_CATEGORIA);
        Assert.assertTrue(categoriaSalva.isPresent());
        Assert.assertNotNull(categoriaSalva.get());
        Assert.assertTrue(categoriaSalva.get().getId().equals(ID_CATEGORIA));

        categoriaRepository.delete(categoria);

        Optional<Categoria> categoriaProdurada = categoriaRepository.findById(ID_CATEGORIA);
        Assert.assertFalse(categoriaProdurada.isPresent());
    }

    //listCategoria
    @Test
    public void listarCategoria(){
        Categoria categoria = this.salvarCategoria();

        List<Categoria> categoriaList = categoriaRepository.findAll();
        Assert.assertTrue(categoriaList.size()>0);
    }

    public Categoria salvarCategoria(){
        Categoria categoria = new Categoria();
        categoria.setTitulo(TITULO_CATEGORIA);
        categoria.setCor("#FFF000");

        Categoria newCategoria = categoriaRepository.save(categoria);
        return newCategoria;
    }*/

}

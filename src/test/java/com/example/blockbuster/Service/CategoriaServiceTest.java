package com.example.blockbuster.Service;


import com.example.blockbuster.Controller.dto.CategoriaDto;
import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Repository.CategoriaRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoriaServiceTest {
    @Autowired
    CategoriaRepository categoriaRepository;
    CategoriaServiceImpl categoriaService;

    @BeforeEach
    public void setup(){
        categoriaService = new CategoriaServiceImpl(categoriaRepository);
    }

    @Test
    public void shouldReturnAllCategorias(){
        categoriaService = new CategoriaServiceImpl(categoriaRepository);
        Categoria categoria=new Categoria();
        categoria.setTitulo("Adventure");
        categoria.setCor("#451525");
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setTitulo(categoria.getTitulo());
        categoriaDto.setCor(categoria.getCor());
        categoriaService.createCategoria(categoriaDto);
        Iterable<Categoria> categoriaList = categoriaService.findAll();
        Categoria savedCat= categoriaList.iterator().next();
        assertThat(savedCat).isNotNull();
    }


    /*private static Categoria categoria;
    private static CategoriaDto categoriaDto;
    private static String ID_CATEGORIA = "1";
    private static String TITULO_CATEGORIA = "Categoria teste";
    @Mock
    private CategoriaRepository categoriaRepository;
    @Mock
    private VideoRepository videoRepository;
    @InjectMocks
    private CategoriaService categoriaService;

    @BeforeAll
    public static void setup() {
        categoria = new Categoria();
        categoria.setTitulo(TITULO_CATEGORIA);
        categoria.setId(Long.valueOf(ID_CATEGORIA));
        categoria.setCor("#451525");
        categoriaDto = new CategoriaDto();
        categoriaDto.setTitulo(categoria.getTitulo());
        categoriaDto.setCor(categoria.getCor());
        categoriaDto.setId(categoria.getId());
    }

    @Ignore
    @Test
    public void convertModelToEntity() {
        CategoriaService srvc = new CategoriaServiceImpl(categoriaRepository, videoRepository);
        Categoria cat = ReflectionTestUtils.invokeMethod(srvc, "toEntity", categoriaDto);
        then(cat).as("Check the nullity").isNotNull();
        then(cat.getCor()).as("Check the equality of cor").isEqualTo(categoria.getCor());
        then(cat.getTitulo()).as("Check the equality of titulo").isEqualTo(categoria.getTitulo());
        then(cat.getId()).as("Check the equality of id").isEqualTo(categoria.getId());
    }*/


}

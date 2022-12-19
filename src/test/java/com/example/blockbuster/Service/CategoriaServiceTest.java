package com.example.blockbuster.Service;


import com.example.blockbuster.Controller.dto.CategoriaDto;
import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Repository.CategoriaRepository;
import com.example.blockbuster.Repository.VideoRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {
    private static Categoria categoria;
    private static CategoriaDto categoriaDto;

    @Mock
    private CategoriaRepository categoriaRepository;
    @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private static String ID_CATEGORIA = "1";
    private static String TITULO_CATEGORIA = "Categoria teste";

    @BeforeAll
    public static void setup(){
        categoria = new Categoria();
        categoria.setTitulo(TITULO_CATEGORIA);
        categoria.setId(Long.valueOf(ID_CATEGORIA));
        categoria.setCor("#451525");
        categoriaDto = new CategoriaDto();
        categoriaDto.setTitulo(categoria.getTitulo());
        categoriaDto.setCor(categoria.getCor());
        categoriaDto.setId(categoria.getId());
    }

    @Test
    public void convertModelToEntity(){
        CategoriaService srvc = new CategoriaServiceImpl(categoriaRepository, videoRepository);
        Categoria cat = ReflectionTestUtils.invokeMethod(srvc, "toEntity", categoriaDto);
        then(cat).as("Check the nullity").isNotNull();
        then(cat.getCor()).as("Check the equality of cor").isEqualTo(categoria.getCor());
        then(cat.getTitulo()).as("Check the equality of titulo").isEqualTo(categoria.getTitulo());
        then(cat.getId()).as("Check the equality of id").isEqualTo(categoria.getId());
    }


}

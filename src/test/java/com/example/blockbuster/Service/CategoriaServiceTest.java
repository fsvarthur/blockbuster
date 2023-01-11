package com.example.blockbuster.Service;


import com.example.blockbuster.Controller.dto.CategoriaDto;
import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Repository.CategoriaRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoriaServiceTest {
    @Mock
    CategoriaRepository categoriaRepository;

    @InjectMocks
    CategoriaServiceImpl categoriaServiceImpl;

    CategoriaDto categoriaDto;
    Categoria categoria;

    @BeforeEach
    void setUp(){
        categoriaDto.setTitulo("Free");
        categoriaDto.setId(1L);

        categoria.setTitulo(categoriaDto.getTitulo());
        categoria.setId(categoriaDto.getId());
    }

    @Test
    public void givenCategoriaObject_whenSaveCategoria_thenReturnEmployeeObject(){
        given(categoriaRepository.findById(categoria.getId())).willReturn(Optional.empty());
        given(categoriaRepository.save(categoria)).willReturn(categoria);

        Optional<Categoria> savedCategoria = categoriaServiceImpl.createCategoria(categoriaDto);

        assertThat(savedCategoria).isNotNull();
    }

}

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoriaServiceTest {
    @Mock
    CategoriaRepository categoriaRepository;

    @InjectMocks
    CategoriaServiceImpl categoriaService;

    @Test
    public void when_save_categoria_should_return_categoria(){
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setTitulo("free");

        when(categoriaRepository.save(any(Categoria.class))).thenReturn(new Categoria());
        Optional<Categoria> x =  categoriaService.createCategoria(categoriaDto);

        assertThat(x.get().getTitulo()).isSameAs(categoriaDto.getTitulo());

    }

}

package com.example.blockbuster.controller;


import com.example.blockbuster.controller.dto.CategoriaDto;
import com.example.blockbuster.model.Categoria;
import com.example.blockbuster.service.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void should_return_created_categoria() throws Exception{
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setTitulo("free");
        categoriaDto.setCor("#154515");
        categoriaDto.setId(1L);

        Categoria categoria = new Categoria();
        categoria.setTitulo(categoriaDto.getTitulo());
        categoria.setCor(categoriaDto.getCor());
        categoria.setId(categoriaDto.getId());

        when(categoriaService.createCategoria(any(CategoriaDto.class))).thenReturn(Optional.of(categoria));

        mockMvc.perform(post("/categorias/v1/categorias")
                .content(objectMapper.writeValueAsString(categoriaDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value(categoriaDto.getTitulo()));
    }


    @Test
    public void should_get_created_categoria() throws Exception{
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setId(1L);
        categoriaDto.setTitulo("free");

        Categoria categoria = new Categoria();
        categoria.setId(categoriaDto.getId());
        categoria.setTitulo(categoriaDto.getTitulo());

        when(categoriaService.createCategoria(any(CategoriaDto.class))).thenReturn(Optional.of(categoria));

        mockMvc.perform(get("/categorias/v1/categorias")
                .content(objectMapper.writeValueAsString(categoriaDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_get_categoria_by_id() throws Exception {
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setId(1L);
        categoriaDto.setTitulo("free");

        Categoria categoria = new Categoria();
        categoria.setTitulo(categoriaDto.getTitulo());
        categoria.setId(categoriaDto.getId());

        doReturn(Optional.of(categoria)).when(categoriaService).findById(String.valueOf(1L));
        when(categoriaService.findById(String.valueOf(1L))).thenReturn(Optional.of(categoria));

        mockMvc.perform(get("/categorias/v1/categorias/1")
                .content(objectMapper.writeValueAsString(categoriaDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(categoriaDto.getId()));
    }

    @Test
    public void should_update_categoria() throws Exception{
        CategoriaDto adventure = new CategoriaDto();
        adventure.setTitulo("Adventure");
        adventure.setId(2L);

        Categoria categoria = new Categoria();
        categoria.setId(adventure.getId());
        categoria.setTitulo(adventure.getTitulo());

        when(categoriaService.createCategoria(any(CategoriaDto.class))).thenReturn(Optional.of(categoria));


        categoria.setTitulo("Action");

    }
}

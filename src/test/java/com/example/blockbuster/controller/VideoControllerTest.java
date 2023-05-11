package com.example.blockbuster.controller;

import com.example.blockbuster.model.Categoria;
import com.example.blockbuster.model.Video;
import com.example.blockbuster.service.VideoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VideoControllerTest.class)
public class VideoControllerTest {

    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VideoService videoService;

    @Test
    public void getVideosWithCategoriaFree() throws Exception {
        Categoria free = new Categoria();
        free.setTitulo("free");
        Categoria action = new Categoria();
        free.setTitulo("action");
        Video video = new Video();
        video.setCategoria(free);
        Video video1 = new Video();
        video1.setCategoria(action);

        when(videoService.getVideoCategoriaFree()).thenReturn(Stream.of(video));
        mockMvc.perform(get("/free")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoria.titulo").value("free"));
    }
}

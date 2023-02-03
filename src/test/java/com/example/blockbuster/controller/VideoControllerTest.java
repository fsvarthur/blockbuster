package com.example.blockbuster.controller;

import com.example.blockbuster.controller.dto.VideoDto;
import com.example.blockbuster.model.Video;
import com.example.blockbuster.service.CategoriaService;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VideosController.class)
public class VideoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoService videoService;
    @MockBean
    private CategoriaService categoriaService;

    private final String REQ = "/api/v1/videos";

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void should_return_created_video() throws Exception{
        VideoDto videoDto = new VideoDto();
        videoDto.setId(1L);

        Video video = new Video();
        video.setId(videoDto.getId());

        when(videoService.createVideo(any(VideoDto.class))).thenReturn(Optional.of(video));

        mockMvc.perform(post(REQ)
                .content(objectMapper.writeValueAsString(videoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(videoDto.getId()));

    }

    @Test
    public void should_get_all_videos() throws Exception{
        VideoDto videoDto = new VideoDto();
        videoDto.setId(1L);

        Video video = new Video();
        video.setId(videoDto.getId());

        when(videoService.createVideo(any(VideoDto.class))).thenReturn(Optional.of(video));

        mockMvc.perform(get(REQ)
                .content(objectMapper.writeValueAsString(videoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_get_video_by_id() throws Exception{
        VideoDto videoDto = new VideoDto();
        videoDto.setId(1L);

        Video video = new Video();
        video.setId(videoDto.getId());

        when(videoService.findById(String.valueOf(video.getId()))).thenReturn(Optional.of(video));

        mockMvc.perform(get(REQ+"/1")
                .content(objectMapper.writeValueAsString(videoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(videoDto.getId()));
    }

    //TODO update

    @Test
    public void deleteVideoById_sucess() throws Exception{
        VideoDto videoDto = new VideoDto();
        videoDto.setId(1L);

        Video video = new Video();
        video.setId(videoDto.getId());

        when(videoService.findById(String.valueOf(video.getId()))).thenReturn(Optional.of(video));

        mockMvc.perform(delete(REQ+"/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void deleteVideoById_notFound() throws Exception{
        Video video = new Video();
        video.setId(Long.valueOf(1));

        when(videoService.findById(String.valueOf(video.getId()))).thenReturn(Optional.of(video));

        mockMvc.perform(delete(REQ+"/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


}

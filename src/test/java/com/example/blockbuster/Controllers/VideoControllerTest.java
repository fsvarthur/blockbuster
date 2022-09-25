package com.example.blockbuster.Controllers;

import com.example.blockbuster.Controller.dto.VideoResDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class VideoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /*
    @Test
    public void postVideoEndpoint() throws Exception{
        URI uri = new URI("/videos");
        String json ="{\"titulo\":\"kimetsu\",\"descricao\":\"kimetsu e uma coisa estranha\",\"url\":\"localhost:8080/videos\"}";

        mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }*/

    @Test
    public void putVideoEndpoint() throws Exception{
        URI uri = new URI("/videos/1");
        String json ="{\"titulo\":\"AS\",\"descricao\":\"kimetsu e uma coisa estranha\",\"url\":\"localhost:8080/videos\"}";

        mockMvc.perform(
                MockMvcRequestBuilders.put(uri)
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public List<VideoResDto> getVideoEndpoint() throws Exception{
        URI uri = new URI("/videos");
        mockMvc.perform(
                MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}

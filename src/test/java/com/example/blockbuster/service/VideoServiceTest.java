package com.example.blockbuster.service;

import com.example.blockbuster.controller.dto.VideoDto;
import com.example.blockbuster.model.Video;
import com.example.blockbuster.repository.VideoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class VideoServiceTest {

    @Mock
    VideoRepository videoRepository;

    @InjectMocks
    VideoServiceImpl videoService;

    @Test
    public void when_save_video_should_return_video(){
        VideoDto videoDto = new VideoDto();
        videoDto.setTitulo("avengers");

        when(videoRepository.save(any(Video.class))).thenReturn(new Video());
        Optional<Video> x = videoService.createVideo(videoDto);

        assertThat(x.get().getTitulo()).isSameAs(videoDto.getTitulo());
    }
}

package com.example.blockbuster;

import com.example.blockbuster.Model.Video;
import com.example.blockbuster.Repository.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class BlockbusterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockbusterApplication.class, args);
	}

	@Bean
	CommandLineRunner init(VideoRepository videoRepository){
		return args -> {
			Stream.of("Attack","My Hero","Classroom","HxH","Chainsaw","SPY").forEach(name ->{
				Video video = new Video(name, "desc","desc");
				videoRepository.save(video);
			});
			videoRepository.findAll().forEach(System.out::println);
		};
	}

}

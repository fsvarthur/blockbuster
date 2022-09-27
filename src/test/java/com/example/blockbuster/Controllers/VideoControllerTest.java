package com.example.blockbuster.Controllers;

import com.example.blockbuster.Repository.VideoRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class VideoControllerTest {

    @Autowired
    private VideoRepository videoRepository;
}

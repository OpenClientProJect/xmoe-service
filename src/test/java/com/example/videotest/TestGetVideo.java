package com.example.videotest;

import com.example.mapper.VideoMapper;
import com.example.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestGetVideo {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoService videoService;

    @Test
    public void testGetVideo() {

        List<Map<String, Object>> videoInfo = videoMapper.getVideoInfo();
        System.out.println(videoInfo);
    }
    

}

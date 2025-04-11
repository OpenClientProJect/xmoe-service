package com.example.dramatest;

import com.example.mapper.DramaMapper;
import com.example.service.drama.DramaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestGetDrama {

    @Autowired
    private DramaMapper dramaMapper;

    @Autowired
    private DramaService dramaService;


    /**
     * 获取番剧详情
     */
    @Test
    public void testGetVideo() {
        Map<String, Object> videoInfo = dramaMapper.DramaInfo(1253);
        System.out.println(videoInfo);
    }


}

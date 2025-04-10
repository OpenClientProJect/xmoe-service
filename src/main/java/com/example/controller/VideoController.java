package com.example.controller;

import com.example.mapper.VideoMapper;
import com.example.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/videoInfo")
    public List<Map<String, Object>> getVideoInfo() {
        List<Map<String, Object>> videoInfo = videoService.getVideoInfo();
        return videoInfo;
    }
}

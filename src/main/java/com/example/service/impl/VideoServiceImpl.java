package com.example.service.impl;

import com.example.mapper.VideoMapper;
import com.example.service.VideoService;
import com.example.utils.RC4DUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Map<String, Object>> getVideoInfo() {
        return videoMapper.getVideoInfo();
    }
}

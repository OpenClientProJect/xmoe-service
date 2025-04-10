package com.example.service.anime.impl;

import com.example.mapper.anime.AnimeMapper;
import com.example.service.anime.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AnimeServiceImpl implements AnimeService {

    @Autowired
    private AnimeMapper animeMapper;


    @Override
    public List<Map<String, Object>> AnimeList() {
        return animeMapper.AnimeList();
    }
}

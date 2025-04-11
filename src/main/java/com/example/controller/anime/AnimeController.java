package com.example.controller.anime;

import com.example.service.anime.AnimeService;
import com.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/anime")
public class AnimeController {

    @Autowired
    private AnimeService animeService;

    @GetMapping("/animeList")
    public Result<List<Map<String,Object>>> getAnimeList(){
        List<Map<String, Object>> animeList = animeService.AnimeList();
        return Result.success(200,animeList);
    }
}

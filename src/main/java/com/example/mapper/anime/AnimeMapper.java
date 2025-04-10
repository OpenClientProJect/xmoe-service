package com.example.mapper.anime;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AnimeMapper {

    @MapKey("vod_id")
    List<Map<String,Object>> AnimeList();
}

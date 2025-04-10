package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface VideoMapper {

    // 随机查询mac_vod表五条指定数据数据
    List<Map<String, Object>> getVideoInfo();
}

package com.example.mapper;

import com.example.pojo.Drama;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DramaMapper {

    // 随机查询mac_vod表五条指定数据数据
    Map<String, Object> DramaInfo(Integer vodId);

    List<Drama> getDramaList();
}

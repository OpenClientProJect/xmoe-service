package com.example.service.drama;

import com.example.pojo.Drama;

import java.util.List;
import java.util.Map;

public interface DramaService {

    Map<String, Object> getParseDramaInfo(Integer vodId);

    /**
     * 获取番剧列表
     */
    List<Drama> getDramaList();
}

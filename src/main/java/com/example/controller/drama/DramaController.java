package com.example.controller.drama;

import com.example.pojo.Drama;
import com.example.service.drama.DramaService;
import com.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/drama")
public class DramaController {

    @Autowired
    private DramaService dramaService;

    @GetMapping("/dramaInfo")
    public Result<Map<String,Object>> getVideoInfo(Integer vodId) {
        Map<String, Object> parseDramaList = dramaService.getParseDramaInfo(vodId);
        return Result.success(200,parseDramaList);
    }

    /**
     * 获取番剧列表
     */
    @GetMapping("/dramaList")
    public Result<List<Drama>> getVideoList() {
        List<Drama> dramaList = dramaService.getDramaList();
        return Result.success(200,dramaList);
    }
}

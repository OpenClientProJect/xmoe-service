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
        List<Map<String, Object>> videoInfo = videoMapper.getVideoInfo();
        
        // 处理并解密 vod_play_url 字段
        for (Map<String, Object> video : videoInfo) {
            if (video.containsKey("vod_play_url") && video.get("vod_play_url") != null) {
                String vodPlayUrl = video.get("vod_play_url").toString();
                String decryptedUrl = decryptVodPlayUrl(vodPlayUrl);
                video.put("vod_play_url", decryptedUrl);
            }
        }
        
        return videoInfo;
    }
    
    /**
     * 解密 vod_play_url 字段
     * 需要将数据开头的"第XX集$id_MOE、$MOE、$id_XS"和结尾的"#"去掉后调用RC4DUtil.parseRC4D方法解密
     */
    private String decryptVodPlayUrl(String vodPlayUrl) {
        if (vodPlayUrl == null || vodPlayUrl.isEmpty()) {
            return vodPlayUrl;
        }
        
        // 分割多个播放源，以 $$$ 为分隔符
        String[] playSources = vodPlayUrl.split("\\$\\$\\$");
        StringBuilder decryptedUrl = new StringBuilder();
        
        for (int i = 0; i < playSources.length; i++) {
            if (i > 0) {
                decryptedUrl.append("$$$");
            }
            
            // 分割同一播放源中的多个剧集链接，以 # 为分隔符
            String[] episodes = playSources[i].split("#");
            StringBuilder decryptedSource = new StringBuilder();
            
            for (int j = 0; j < episodes.length; j++) {
                if (j > 0) {
                    decryptedSource.append(",");  // 将原来的 # 替换为逗号
                }
                
                String episode = episodes[j];
                // 提取剧集标题和加密URL
                int separatorIndex = episode.indexOf("$");
                if (separatorIndex > -1) {
                    String title = episode.substring(0, separatorIndex + 1); // 包含分隔符 $
                    String encryptedUrl = episode.substring(separatorIndex + 1);
                    
                    // 去除前缀
                    if (encryptedUrl.startsWith("MOE")) {
                        encryptedUrl = encryptedUrl.substring(3);
                    } else if (encryptedUrl.startsWith("id_MOE")) {
                        encryptedUrl = encryptedUrl.substring(6);
                    } else if (encryptedUrl.startsWith("id_XS")) {
                        encryptedUrl = encryptedUrl.substring(5);
                    }
                    
                    // 解密URL
                    String decrypted = RC4DUtil.parseRC4D(encryptedUrl);
                    decryptedSource.append(title).append(decrypted);
                } else {
                    decryptedSource.append(episode);
                }
            }
            
            decryptedUrl.append(decryptedSource);
        }
        
        return decryptedUrl.toString();
    }
}

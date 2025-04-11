package com.example.service.drama.impl;

import com.example.mapper.DramaMapper;
import com.example.pojo.Drama;
import com.example.service.drama.DramaService;
import com.example.utils.RC4DUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DramaServiceImpl implements DramaService {

    @Autowired
    private DramaMapper dramaMapper;

    /**
     * 获取番剧详情
     */
    @Override
    public Map<String, Object> getParseDramaInfo(Integer vodId) {
        Map<String, Object> videoInfo = dramaMapper.DramaInfo(vodId);
        
        // 处理并解密 vod_play_url 字段
            if (videoInfo.containsKey("vod_play_url") && videoInfo.get("vod_play_url") != null) {
                String vodPlayUrl = videoInfo.get("vod_play_url").toString();
                String decryptedUrl = decryptVodPlayUrl(vodPlayUrl);
                videoInfo.put("vod_play_url", decryptedUrl);
            }

        return videoInfo;
    }


    /**
     * 获取番剧集合
     */
    @Override
    public List<Drama> getDramaList() {
        return dramaMapper.getDramaList();
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
        // 遍历每个播放源
        for (int i = 0; i < playSources.length; i++) {
            if (i > 0) {
                decryptedUrl.append(",");  // 将原来的 $$$ 替换为逗号
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
                    String title = episode.substring(0, separatorIndex); // 不包含分隔符 $
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

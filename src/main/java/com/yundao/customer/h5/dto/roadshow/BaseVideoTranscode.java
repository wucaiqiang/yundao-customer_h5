package com.yundao.customer.h5.dto.roadshow;

import com.yundao.core.base.model.BaseModel;
import java.io.Serializable;

public class BaseVideoTranscode extends BaseModel implements Serializable {
    /**
	 * 原始视频关联ID
	 */
    private Long videoId;

    /**
	 * 大小
	 */
    private Long size;

    /**
	 * 转码类型
	 */
    private Integer definition;

    /**
	 * 文件格式
	 */
    private String type;

    /**
	 * 文件地址（转码后）
	 */
    private String url;

    private static final long serialVersionUID = 1L;

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getDefinition() {
        return definition;
    }

    public void setDefinition(Integer definition) {
        this.definition = definition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
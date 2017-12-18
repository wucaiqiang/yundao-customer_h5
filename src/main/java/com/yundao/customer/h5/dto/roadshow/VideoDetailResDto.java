
package com.yundao.customer.h5.dto.roadshow;

import java.util.List;

/**
 * Function: Reason: Date: 2017年11月6日 下午9:04:04
 * 
 * @author wucq
 * @version
 */
public class VideoDetailResDto extends BaseVideo {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 状态中文
	 */
	private String statusText;
	/**
	 * 视频转码的信息
	 */
	private List<BaseVideoTranscode> baseVideoTranscodes;

	public String getStatusText() {

		return statusText;
	}

	public void setStatusText(String statusText) {

		this.statusText = statusText;
	}

	public List<BaseVideoTranscode> getBaseVideoTranscodes() {

		return baseVideoTranscodes;
	}

	public void setBaseVideoTranscodes(List<BaseVideoTranscode> baseVideoTranscodes) {

		this.baseVideoTranscodes = baseVideoTranscodes;
	}

}

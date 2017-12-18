
package com.yundao.customer.h5.service.roadshow;

import org.springframework.web.bind.annotation.RequestParam;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.customer.h5.dto.roadshow.RoadshowDetailResDto;

/**
 * Function: Reason: Date: 2017年11月13日 下午5:36:06
 * 
 * @author wucq
 * @version
 */
public interface RoadShowService {
	public Result<RoadshowDetailResDto> get(@RequestParam Long id) throws BaseException;
}

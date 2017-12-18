
package com.yundao.customer.h5.service.roadshow.impl;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.customer.h5.constant.url.TenantUrl;
import com.yundao.customer.h5.dto.roadshow.RoadshowDetailResDto;
import com.yundao.customer.h5.service.roadshow.RoadShowService;
import com.yundao.customer.h5.util.ArgsUtils;
import com.yundao.customer.h5.util.HttpUtils;

/**
 * Function: Reason: Date: 2017年11月13日 下午5:36:33
 * 
 * @author wucq
 * @version
 */
@Service
public class RoadShowServiceImpl implements RoadShowService {

	@Override
	public Result<RoadshowDetailResDto> get(Long id) throws BaseException {

		Result<RoadshowDetailResDto> result = HttpUtils.get(TenantUrl.ROADSHOW_GET, ArgsUtils.toIdMap(id),
				new BaseTypeReference<Result<RoadshowDetailResDto>>() {
				});

		return result;

	}

}

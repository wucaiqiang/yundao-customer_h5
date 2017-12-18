
package com.yundao.customer.h5.controller.roadshow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yundao.core.code.Result;
import com.yundao.customer.h5.dto.roadshow.RoadshowDetailResDto;
import com.yundao.customer.h5.service.roadshow.RoadShowService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年11月13日 下午5:33:08
 * 
 * @author wucq
 * @version
 */
@RestController
@RequestMapping("/roadshow/")
@ResponseBody
@Api("路演管理")
public class RoadShowController {
	@Autowired
	private RoadShowService roadShowService;

	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ApiOperation(value = "获取路演表详细信息")
	public Result<RoadshowDetailResDto> get(@RequestParam Long id) throws Exception {
		return roadShowService.get(id);
	}
}

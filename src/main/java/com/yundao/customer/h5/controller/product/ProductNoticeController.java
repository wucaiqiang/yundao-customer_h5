
package com.yundao.customer.h5.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.customer.h5.constant.url.TenantUrl;
import com.yundao.customer.h5.dto.product.NoticeDetailDto;
import com.yundao.customer.h5.util.ArgsUtils;
import com.yundao.customer.h5.util.HttpUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年8月8日 上午10:20:55
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/product/notice")
@Api("产品公告相关")
@ResponseBody
public class ProductNoticeController {
	
	@ApiOperation(value = "查询公告详情", notes = "查询公告详情")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Result<NoticeDetailDto> getNoticeDetail(@RequestParam Long id) throws Exception {
		Result<NoticeDetailDto> result = HttpUtils.get(TenantUrl.GET_NOTICE_DETAIL, ArgsUtils.toIdMap(id),
				new BaseTypeReference<Result<NoticeDetailDto>>() {
				});
		return result;
	}
}


package com.yundao.customer.h5.controller.afp;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.customer.h5.dto.afp.UserCardResDto;
import com.yundao.customer.h5.service.afp.AfpService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年9月22日 下午4:17:47
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/afp")
@ResponseBody
@Api("理财师相关接口")
public class AfpController {
	@Autowired
	private AfpService afpService;

	@RequestMapping(value = "/get_card", method = RequestMethod.GET)
	@ApiOperation(value = "根据理财师uuid获取名片", notes = "根据理财师uuid获取名片")
	public Result<UserCardResDto> getCardByUUID(@RequestParam String uuid) throws Exception {
		return afpService.getCardByUUID(uuid);
	}

	@RequestMapping(value = "/get_qrcode", method = RequestMethod.GET)
	@ApiOperation(value = "根据理财师uuid生成名片二维码", notes = "根据理财师uuid生成名片二维码")
	public void getQRCode(HttpServletResponse response, @RequestParam String uuid) throws Exception {
		afpService.getQRCode(response, uuid);
	}
}

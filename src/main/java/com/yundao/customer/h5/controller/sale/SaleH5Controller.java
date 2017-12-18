package com.yundao.customer.h5.controller.sale;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yundao.core.code.Result;
import com.yundao.core.constant.CommonConstant;
import com.yundao.core.dto.HeaderUser;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.threadlocal.ThreadLocalUtils;
import com.yundao.customer.h5.constant.url.ScmUrl;
import com.yundao.customer.h5.constant.url.TenantUrl;
import com.yundao.customer.h5.dto.MyIndexDto;
import com.yundao.customer.h5.dto.sale.DeclarationH5ReqDto;
import com.yundao.customer.h5.dto.sale.DeclarationH5ResDto;
import com.yundao.customer.h5.dto.sale.ReservationH5ReqDto;
import com.yundao.customer.h5.dto.sale.ReservationH5ResDto;
import com.yundao.customer.h5.dto.tenant.TenantModel;
import com.yundao.customer.h5.util.ArgsUtils;
import com.yundao.customer.h5.util.HttpUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@ResponseBody
@Api("H5报单和预约")
public class SaleH5Controller {


	@RequestMapping(value = "/declaration/my/get_page", method = RequestMethod.GET)
	@ApiOperation(value = "分页查询我的报单")
	public Result<PaginationSupport<DeclarationH5ResDto>> getMyDeclaration(@ModelAttribute DeclarationH5ReqDto dto) throws Exception {
		return HttpUtils.get(TenantUrl.DECLARATION_MY_GET_PAGE, ArgsUtils.toMap(dto), new BaseTypeReference<Result<PaginationSupport<DeclarationH5ResDto>>>() {
        });
	}
	
	@RequestMapping(value = "/reservation/my/get_page", method = RequestMethod.GET)
	@ApiOperation(value = "分页查询我的预约")
	public Result<PaginationSupport<ReservationH5ResDto>> getMyReservation(@ModelAttribute ReservationH5ReqDto dto) throws Exception {
		return HttpUtils.get(TenantUrl.RESERVATION_MY_GET_PAGE, ArgsUtils.toMap(dto), new BaseTypeReference<Result<PaginationSupport<ReservationH5ResDto>>>() {
        });
	}

	
	@RequestMapping(value = "/get/my/index", method = RequestMethod.GET)
	@ApiOperation(value = "获取我的首页信息")
	public Result<MyIndexDto> getMyIndex() throws Exception {
		return HttpUtils.get(TenantUrl.GET_MY_INDEX, null, new BaseTypeReference<Result<MyIndexDto>>() {
        });
	}
	
	@RequestMapping(value = "/get/tenant/info", method = RequestMethod.GET)
	@ApiOperation(value = "获取租户信息")
	public Result<TenantModel> getTenant() throws Exception {
		HeaderUser headerUser =  (HeaderUser) ThreadLocalUtils.getFromRequest(CommonConstant.HEADER_USER);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("id", headerUser.getTenantId());
		return HttpUtils.get(ScmUrl.GET_TENANT, map, new BaseTypeReference<Result<TenantModel>>() {
        });
	}
}
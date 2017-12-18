

package com.yundao.customer.h5.controller.aa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.customer.h5.constant.url.TenantUrl;
import com.yundao.customer.h5.dto.aa.plan.AssetPlanDto;
import com.yundao.customer.h5.dto.aa.plan.PlanDescriptionDto;
import com.yundao.customer.h5.dto.aa.product.AssetAllocationProductPageReqDto;
import com.yundao.customer.h5.dto.product.ProductBaseResDto;
import com.yundao.customer.h5.dto.question.CustomerRiskQuestionResDto;
import com.yundao.customer.h5.dto.question.CustomerRiskResDto;
import com.yundao.customer.h5.service.aa.AssetPlanService;
import com.yundao.customer.h5.service.product.ProductService;
import com.yundao.customer.h5.util.HttpUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月8日 上午11:56:10 
 * @author   欧阳利
 * @version   
 */
@Controller
@RequestMapping(value = "/asset")
@Api(value = "资产配置管理")
@ResponseBody
public class AssetController {
   
	@Autowired
	AssetPlanService assetPlanService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/plan/get_description", method = RequestMethod.GET)
	@ApiOperation(value = "通过资产配置id获取方案说明")
	public Result<PlanDescriptionDto> getPlanDescriptionDto(@RequestParam Integer riskValue)throws Exception{
		return assetPlanService.getPlanDescriptionDto(riskValue);
	}
	
	
	@RequestMapping(value = "/plan/get", method = RequestMethod.GET)
	@ApiOperation(value = "获取配置方案")
	public Result<AssetPlanDto> getAssetAllocationDtoToUser(@RequestParam Long id)throws Exception{
		return assetPlanService.getAssetAllocationDtoToUser(id);
	}
	
	
	@RequestMapping(value = "/get/customer_plan", method = RequestMethod.GET)
	@ApiOperation(value = "获取客户(customerId)配置方案")
	public Result<AssetPlanDto> getCustomerPlan(@RequestParam Long customerId)throws Exception{
		return assetPlanService.getCustomerPlan(customerId);
	}

	@RequestMapping(value = "/get/current_user_plan", method = RequestMethod.GET)
	@ApiOperation(value = "获取当前登录用户的配置方案")
	public Result<AssetPlanDto> getCurrentUserPlan()throws Exception{
		return assetPlanService.getCurrentUserPlan();
	}
	
	
	
	@RequestMapping(value = "/product/get_page", method = RequestMethod.GET)
	@ApiOperation(value = "分页查询资产配置的产品列表")
	public Result<PaginationSupport<ProductBaseResDto>> getAssetAllocationProductPage(@ModelAttribute AssetAllocationProductPageReqDto reqDto)throws BaseException{
		return productService.getAssetAllocationProductPage(reqDto);
	}
	
	
	@RequestMapping(value = "/get/customer/risk_evaluation_result", method = RequestMethod.GET)
	@ApiOperation(value = "获取分析测评结果(客户id) 客户自己测评的")
	public Result<CustomerRiskResDto> getRiskEvaluationByCustomerId(@RequestParam Long customerId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();  
		map.put("customerId", customerId);
		Result<CustomerRiskResDto> result = HttpUtils.get(TenantUrl.QUESTION_GET_CUSTOMER_RISK_EVALUATION, map,
				new BaseTypeReference<Result<CustomerRiskResDto>>() {
				});
		return result;
	}
	
	
	@RequestMapping(value = "/get/risk_evaluation_result", method = RequestMethod.GET)
	@ApiOperation(value = "获取分析测评结果(客户id) 客户自己测评的")
	public Result<CustomerRiskResDto> getRiskEvaluation() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();  
		Result<List<CustomerRiskQuestionResDto>> result = HttpUtils.get(TenantUrl.QUESTION_GET_USER_RISK_EVALUATION, map,
				new BaseTypeReference<Result<List<CustomerRiskQuestionResDto>>>() {
				});
		CustomerRiskResDto dto = new CustomerRiskResDto();
		dto.setQuestionDtos(result.getResult());
		return Result.newSuccessResult(dto);
	}

}


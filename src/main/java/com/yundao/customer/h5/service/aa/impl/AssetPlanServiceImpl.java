

package com.yundao.customer.h5.service.aa.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.customer.h5.constant.url.TenantUrl;
import com.yundao.customer.h5.dto.aa.plan.AssetPlanDto;
import com.yundao.customer.h5.dto.aa.plan.PlanDescriptionDto;
import com.yundao.customer.h5.service.aa.AssetPlanService;
import com.yundao.customer.h5.util.ArgsUtils;
import com.yundao.customer.h5.util.HttpUtils;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月10日 下午3:44:41 
 * @author   欧阳利
 * @version   
 */
@Service
public class AssetPlanServiceImpl implements AssetPlanService {
	
	
	public Result<PlanDescriptionDto> getPlanDescriptionDto(Integer riskValue)throws Exception{
		 Map<String, Object> map = new HashMap<>(1);
		 map.put("riskValue", riskValue);
		return HttpUtils.get(TenantUrl.GETS_ASSET_PLAN_DESCRIPTION, map,
				new BaseTypeReference<Result<PlanDescriptionDto>>() {
				});
	}
	
	
	public Result<AssetPlanDto> getAssetAllocationDtoToUser(Long id)throws Exception{
		return HttpUtils.get(TenantUrl.GETS_ASSET_PLAN, ArgsUtils.toIdMap(id),
				new BaseTypeReference<Result<AssetPlanDto>>() {
				});
	}
	
	
	
	public Result<AssetPlanDto> getCustomerPlan(Long customerId)throws Exception{
		Map<String, Object> map = new HashMap<>(1);
		map.put("customerId", customerId);
		return HttpUtils.get(TenantUrl.GETS_ASSET_CUSTOMER_PLAN, map,
				new BaseTypeReference<Result<AssetPlanDto>>() {
				});
	}
	
	
	public Result<AssetPlanDto> getCurrentUserPlan()throws Exception{
		Map<String, Object> map = new HashMap<>(1);
		return HttpUtils.get(TenantUrl.GETS_ASSET_CURRENT_USER_PLAN, ArgsUtils.toIdMap(map),
				new BaseTypeReference<Result<AssetPlanDto>>() {
				});
	}
	

	public Result<PlanDescriptionDto> getCustomerPlanDescription(Long customerId)throws Exception{
		Map<String, Object> map = new HashMap<>(1);
		map.put("customerId", customerId);
		return HttpUtils.get(TenantUrl.GETS_ASSET_PLAN_DESCRIPTION, ArgsUtils.toIdMap(customerId),
				new BaseTypeReference<Result<PlanDescriptionDto>>() {
				});
	}
}


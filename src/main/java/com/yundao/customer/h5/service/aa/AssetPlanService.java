

package com.yundao.customer.h5.service.aa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yundao.core.code.Result;
import com.yundao.customer.h5.dto.aa.plan.AssetPlanDto;
import com.yundao.customer.h5.dto.aa.plan.PlanDescriptionDto;

import io.swagger.annotations.ApiOperation;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月10日 下午3:44:21 
 * @author   欧阳利
 * @version   
 */
public interface AssetPlanService {
	public Result<PlanDescriptionDto> getPlanDescriptionDto(Integer riskValue)throws Exception;
	
	
	public Result<AssetPlanDto> getAssetAllocationDtoToUser(Long id)throws Exception;
	
	

	public Result<AssetPlanDto> getCurrentUserPlan()throws Exception;
	
	
	/**
	 * 获取配置方案
	 * getCustomerPlan:
	 * @author: 欧阳利
	 * @param id
	 * @return
	 * @throws Exception
	 * @description:
	 */
	public Result<AssetPlanDto> getCustomerPlan(@RequestParam Long id)throws Exception;
	
	/**
	 * 通过资产配置id获取方案说明
	 * getCustomerPlanDescription:
	 * @author: 欧阳利
	 * @param customerId
	 * @return
	 * @throws Exception
	 * @description:
	 */
	public Result<PlanDescriptionDto> getCustomerPlanDescription(@RequestParam Long customerId)throws Exception;

}
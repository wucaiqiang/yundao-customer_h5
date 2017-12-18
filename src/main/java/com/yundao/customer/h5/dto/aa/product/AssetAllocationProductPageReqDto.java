

package com.yundao.customer.h5.dto.aa.product;

import com.yundao.customer.h5.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月8日 下午4:27:57 
 * @author   欧阳利
 * @version   
 */
public class AssetAllocationProductPageReqDto extends AbstractBasePageDto {

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("资产配置id")
	private Long assetId;

	public Long getAssetId() {
	
		return assetId;
	}

	public void setAssetId(Long assetId) {
	
		this.assetId = assetId;
	}

	
}


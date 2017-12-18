package com.yundao.customer.h5.dto.product;

import java.util.ArrayList;
import java.util.List;

import com.yundao.customer.h5.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

public class ProductBaseQueryReqDto extends AbstractBasePageDto{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("产品名称")
    private String name;
	
	/**
	 * 产品的发行状态列表
	 */
	private List<Integer> issuedStatusList;
	
	
	public void addIssuedStatus(Integer issuedStatus){
		if(issuedStatusList == null){
			issuedStatusList = new ArrayList<Integer>();
		}
		issuedStatusList.add(issuedStatus);
	}

	public String getName() {
	
		return name;
	}

	public void setName(String name) {
	
		this.name = name;
	}

	public List<Integer> getIssuedStatusList() {
	
		return issuedStatusList;
	}

	public void setIssuedStatusList(List<Integer> issuedStatusList) {
	
		this.issuedStatusList = issuedStatusList;
	}


    
}

package com.yundao.customer.h5.dto.product;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class ProductBaseResDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("产品id")
	private Long id;

	@ApiModelProperty("产品名称")
	private String name;
	
	@ApiModelProperty("产品类型")
	private Long typeId;
	
	@ApiModelProperty("产品类型名称")
	private String typeName;
	
	@ApiModelProperty("投资期限")
	private String timeLimit;
	
	@ApiModelProperty("投资期限")
	private String highlight;
	
	@ApiModelProperty("预期收益率")
	private String incomeRate;
	
	public String getHighlight() {
	
		return highlight;
	}
	public void setHighlight(String highlight) {
	
		this.highlight = highlight;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getTimeLimit() {
	
		return timeLimit;
	}
	public void setTimeLimit(String timeLimit) {
	
		this.timeLimit = timeLimit;
	}
	public String getIncomeRate() {
	
		return incomeRate;
	}
	public void setIncomeRate(String incomeRate) {
	
		this.incomeRate = incomeRate;
	}

}

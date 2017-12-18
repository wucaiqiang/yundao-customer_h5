package com.yundao.customer.h5.dto.question;

import io.swagger.annotations.ApiModelProperty;

public class RiskEvaluationResDto {
	
	@ApiModelProperty("投资者id")
	private Long userId;
	
	@ApiModelProperty("认定结果得分")
    private Integer grade;
    
	@ApiModelProperty("类型值")
    private Integer riskValue;
    
	@ApiModelProperty("类型名称")
    private String riskText;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getRiskValue() {
		return riskValue;
	}

	public void setRiskValue(Integer riskValue) {
		this.riskValue = riskValue;
	}

	public String getRiskText() {
		return riskText;
	}

	public void setRiskText(String riskText) {
		this.riskText = riskText;
	}
	
	
	
}

package com.yundao.customer.h5.dto.question;

import io.swagger.annotations.ApiModelProperty;

public class RiskEvaluationReqDto {
	
	@ApiModelProperty("提醒选项答案 [{ \"questionId(题目id)\":1 ,\"optionIdList(选项ids)\": [1] }]")
	private String questionAndOptionDtos;
	
	public String getQuestionAndOptionDtos() {
		return questionAndOptionDtos;
	}

	public void setQuestionAndOptionDtos(String questionAndOptionDtos) {
		this.questionAndOptionDtos = questionAndOptionDtos;
	}

}

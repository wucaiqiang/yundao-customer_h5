

package com.yundao.customer.h5.dto.question;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月11日 下午6:27:36 
 * @author   欧阳利
 * @version   
 */
public class CustomerRiskResDto {

//	@ApiModelProperty("投资者id")
//	private Long userId;
//	
//	@ApiModelProperty("客户id")
//	private Long customerId;
//	
//	@ApiModelProperty("认定结果得分")
//    private Integer grade;
//    
//	@ApiModelProperty("类型值")
//    private Integer riskValue;
//    
//	@ApiModelProperty("类型名称")
//    private String riskText;
//	
//	@ApiModelProperty("评测时间")
//	private Date evaluationTime;
//	
//	@ApiModelProperty("是否存在对应的用户")
//	private Boolean isExistUserId;
//	
//	@ApiModelProperty("是否测评")
//	private Boolean isEvaluation;
//	
	@ApiModelProperty("类型名称")
	private List<CustomerRiskQuestionResDto> questionDtos;

	public List<CustomerRiskQuestionResDto> getQuestionDtos() {
	
		return questionDtos;
	}

	public void setQuestionDtos(List<CustomerRiskQuestionResDto> questionDtos) {
	
		this.questionDtos = questionDtos;
	}

	
	
}


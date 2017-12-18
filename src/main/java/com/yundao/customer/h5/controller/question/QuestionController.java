package com.yundao.customer.h5.controller.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yundao.core.code.Result;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.customer.h5.constant.url.TenantUrl;
import com.yundao.customer.h5.dto.question.QuestionDto;
import com.yundao.customer.h5.dto.question.RiskEvaluationReqDto;
import com.yundao.customer.h5.dto.question.RiskEvaluationResDto;
import com.yundao.customer.h5.util.ArgsUtils;
import com.yundao.customer.h5.util.HttpUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/question/")
@ResponseBody
@Api("题目管理")
public class QuestionController {

	@RequestMapping(value = "gets_risk_question", method = RequestMethod.GET)
	@ApiOperation(value = "获取风险评估题目")
	public Result<List<QuestionDto>> getRiskQuestions() throws Exception {
		 return HttpUtils.get(TenantUrl.GETS_RISK_QUESTION, null, new BaseTypeReference<Result<List<QuestionDto>>>() {
	        });
		
	}

	
	@RequestMapping(value = "risk/evaluation", method = RequestMethod.POST)
	@ApiOperation(value = "风险测评")
	public Result<RiskEvaluationResDto> riskEvaluation(@Validated @ModelAttribute RiskEvaluationReqDto reqDto,
			BindingResult bindingResult) throws Exception {
		return HttpUtils.post(TenantUrl.RISK_EVALUATION, ArgsUtils.toMap(reqDto), new BaseTypeReference<Result<RiskEvaluationResDto>>() {
        });
	}
	

	@RequestMapping(value = "get/risk_evaluation_result", method = RequestMethod.GET)
	@ApiOperation(value = "获取分析测评结果(投资者id)")
	public Result<RiskEvaluationResDto> getRiskEvaluation(@RequestParam Long userId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();  
		map.put("userId", userId);
		return HttpUtils.get(TenantUrl.GET_RISK_EVALUATION_RESULT, map, new BaseTypeReference<Result<RiskEvaluationResDto>>() {
        });
	}
}

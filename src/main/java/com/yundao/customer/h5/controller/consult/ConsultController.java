package com.yundao.customer.h5.controller.consult;


import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.dto.HeaderUser;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.threadlocal.ThreadLocalUtils;
import com.yundao.customer.h5.constant.CodeConstant;
import com.yundao.customer.h5.constant.url.TenantUrl;
import com.yundao.customer.h5.dto.consult.BaseConsultProductReqDto;
import com.yundao.customer.h5.dto.consult.ConsultFPReqDto;
import com.yundao.customer.h5.dto.consult.ConsultProductReqDto;
import com.yundao.customer.h5.util.ArgsUtils;
import com.yundao.customer.h5.util.HttpUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * C端客户预约
 *
 * @author jan
 * @create 2017-09-07 AM11:28
 **/
@Controller
@ResponseBody
@RequestMapping("/consult")
@Api("客户预约")
public class ConsultController {

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ApiOperation("预约产品")
    public Result<Long> addForProduct(@ModelAttribute BaseConsultProductReqDto dto) throws BaseException {
        ConsultProductReqDto reqDto = new ConsultProductReqDto();
        BeanUtils.copyProperties(dto, reqDto);
        reqDto.setUserId(this.getUser().getUserId());
        return HttpUtils.post(TenantUrl.CONSULT_PRODUCT, ArgsUtils.toMap(reqDto), new BaseTypeReference<Result<Long>>() {
        });
    }
    
    
    @RequestMapping(value = "/fp", method = RequestMethod.POST)
    @ApiOperation("预约理财师")
    public Result<Long> addForFp(@ModelAttribute ConsultFPReqDto dto) throws BaseException {
    	 Map<String, Object> map = ArgsUtils.toMap(dto);
    	 map.put("userId", this.getUser().getUserId());
        return HttpUtils.post(TenantUrl.CONSULT_TP, map, new BaseTypeReference<Result<Long>>() {
        });
    }

    private HeaderUser getUser() throws BaseException {
        HeaderUser result = (HeaderUser) ThreadLocalUtils.getFromRequest("headerUser");
        if (result == null || result.getUserId() == null)
            throw new BaseException(CodeConstant.CODE_1300006);
        return result;
    }

}

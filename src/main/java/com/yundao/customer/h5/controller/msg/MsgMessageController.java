package com.yundao.customer.h5.controller.msg;


import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.utils.MapUtils;
import com.yundao.core.utils.RequestUtils;
import com.yundao.customer.h5.constant.url.TenantUrl;
import com.yundao.customer.h5.enums.ChannelEnum;
import com.yundao.customer.h5.util.HttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 客户动态
 *
 * @author gjl
 * @create 2017-08-16 PM3:35
 **/
@Controller
@RequestMapping("/msg/message/")
@ResponseBody
@Api("消息")
public class MsgMessageController {

    public static String H5_CHANNEL = "C_H5";
    @RequestMapping(value = "send_message", method = RequestMethod.POST)
    @ApiOperation(value = "发送消息")
    public Result<Long> getMyPage(HttpServletRequest request, @RequestParam String code, @RequestParam Long customerId,@RequestParam String pCode) throws BaseException {
        Map<String,Object> params = MapUtils.cast(RequestUtils.getParameterMap(request));
        params.put("code",code);
        params.put("customerId",customerId);
        params.put("channel", ChannelEnum.getByCode(pCode).getName());
        return HttpUtils.post(TenantUrl.SEND_MESSAGE, params, new BaseTypeReference<Result<Long>>() {});
    }
}

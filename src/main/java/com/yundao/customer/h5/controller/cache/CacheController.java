package com.yundao.customer.h5.controller.cache;


import com.yundao.core.cache.redis.JedisUtils;
import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.customer.h5.cache.CacheKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 缓存
 *
 * @author jan
 * @create 2017-09-11 PM7:45
 **/
@Controller
@ResponseBody
@RequestMapping("/cache")
@Api("缓存")
public class CacheController {

    @RequestMapping(value = "/remove_domains", method = RequestMethod.POST)
    @ApiOperation("移除域名缓存")
    public Result<Boolean> clearDomains() throws BaseException {
        String key = CacheKey.TENANT_DOMAINS;
        JedisUtils.remove(key);
        return Result.newSuccessResult(true);
    }
}


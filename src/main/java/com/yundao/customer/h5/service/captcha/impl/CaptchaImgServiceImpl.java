package com.yundao.customer.h5.service.captcha.impl;

import com.yundao.core.cache.redis.JedisUtils;
import com.yundao.core.code.Result;
import com.yundao.core.code.config.CommonCode;
import com.yundao.core.exception.BaseException;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.service.AbstractService;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.JsonUtils;
import com.yundao.core.utils.ValidateCode;
import com.yundao.customer.h5.cache.CacheKey;
import com.yundao.customer.h5.constant.CodeConstant;
import com.yundao.customer.h5.dto.captcha.CaptchaCheckReqDto;
import com.yundao.customer.h5.service.captcha.CaptchaImgService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 验证码服务实现
 *
 * @author jan
 * @create 2017-09-11 PM5:08
 **/
@Service
public class CaptchaImgServiceImpl extends AbstractService implements CaptchaImgService {

    private static Log log = LogFactory.getLog(CaptchaImgServiceImpl.class);


    @Override
    public Boolean remove(String mobile, String type) throws BaseException {
        String logPrefix = "验证码||图片验证码||移除";
        Long tenantId = super.getHeaderTenantId();
        log.info("%s mobile %s,type %s,tenantId %s", logPrefix, mobile, type, tenantId);
        if (BooleanUtils.isBlank(type))
            throw new BaseException(CodeConstant.CODE_1300002);
        JedisUtils.remove(CacheKey.getCaptcha(tenantId, type, mobile));
        return true;
    }

    @Override
    public Boolean checkCode(CaptchaCheckReqDto dto, String type) throws BaseException {
        String logPrefix = "验证码||图片验证码||校验";
        Long tenantId = super.getHeaderTenantId();
        log.info("%s dto %s,type %s,tenantId %s", logPrefix, JsonUtils.objectToJson(dto), type, tenantId);
        if (BooleanUtils.isBlank(type))
            throw new BaseException(CodeConstant.CODE_1300002);

        Object obj = JedisUtils.getObject(CacheKey.getCaptcha(tenantId, type, dto.getMobile()));
        log.info("%s obj %s", logPrefix, obj != null ? obj.toString() : "");
        if (obj == null)
            return false;
        return dto.getImageCaptcha().equals(obj.toString());
    }

    @Override
    public Boolean checkCode(String mobile, String imageCaptcha, String type) throws BaseException {
        CaptchaCheckReqDto dto = new CaptchaCheckReqDto();
        dto.setImageCaptcha(imageCaptcha);
        dto.setMobile(mobile);
        return this.checkCode(dto, type);
    }

    @Override
    public Result<String> getCode(String mobile, String type, HttpServletResponse response) throws BaseException {
        String logPrefix = "验证码||图片验证码||获取";
        Long tenantId = super.getHeaderTenantId();
        log.info("%s mobile %s,type %s,tenantId %s", logPrefix, mobile, type, tenantId);
        if (BooleanUtils.isBlank(type))
            throw new BaseException(CodeConstant.CODE_1300002);

        try {
            OutputStream out = response.getOutputStream();
            String imageCaptcha = ValidateCode.generatorValidateCode(out);
            log.info("%s imageCaptcha %s", logPrefix, imageCaptcha);
            out.flush();
            out.close();
            String key = CacheKey.getCaptcha(tenantId, type, mobile);
            JedisUtils.remove(key); //清楚旧的缓存
            JedisUtils.setObject(key, imageCaptcha);
        } catch (Exception e) {
            log.error("%s异常 %s", logPrefix, e.toString());
            throw new BaseException(CommonCode.COMMON_1007);
        }
        return Result.newSuccessResult();
    }
}

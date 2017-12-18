

package com.yundao.customer.h5.util;

import com.yundao.core.cache.redis.JedisUtils;
import com.yundao.core.code.Result;
import com.yundao.core.code.config.CommonCode;
import com.yundao.core.constant.CommonConstant;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.ConfigUtils;
import com.yundao.core.utils.JsonUtils;
import com.yundao.core.utils.RegularUtils;
import com.yundao.core.utils.RegularUtils.Regular;
import com.yundao.customer.h5.cache.CacheKey;
import com.yundao.customer.h5.constant.CodeConstant;
import com.yundao.customer.h5.constant.url.ScmUrl;
import com.yundao.customer.h5.dto.tenant.TenantDomainResDto;

import java.util.List;

/**
 * Function:
 * Reason:
 * Date:     2017年7月27日 下午5:27:39
 *
 * @author wucq
 */
public class TenantUtils {
    private static Log logger = LogFactory.getLog(TenantUtils.class);

    /**
     * 获取租户信息
     *
     * @param serverName url
     * @param port       端口
     * @return 租户信息
     * @throws BaseException 异常信息
     */
    public static TenantDomainResDto getTenant(String serverName, String port) throws BaseException {
        logger.info("serverName:%s", serverName);
        String domain = getDomain(serverName, port);
        logger.info("domain:%s", domain);
        String systemCode = ConfigUtils.getValue(CommonConstant.SYSTEM_CODE);
        List<TenantDomainResDto> domains = getTenantDomains();
        logger.info("domains:%s", JsonUtils.objectToJson(domains));
        if (BooleanUtils.isEmpty(domains))
            return null;
        TenantDomainResDto tenant = LambdaFilter.firstOrDefault(domains, m -> m.getSystemCode().equals(systemCode) && m.getUrl().equals(domain));
        logger.info("tenant:%s", JsonUtils.objectToJson(tenant));
        if (tenant == null)
            throw new BaseException(CodeConstant.CODE_1300001);
        return tenant;
    }

    /**
     * 获取域名
     *
     * @param serverName url
     * @param port       端口
     * @return 域名
     */
    public static String getDomain(String serverName, String port) {
        if (BooleanUtils.isEmpty(serverName)) {
            logger.info("serverName 为空");
            return serverName;
        }
        if ("localhost".equals(serverName) || isIpAddress(serverName)) {
            return serverName + (BooleanUtils.isEmpty(port) ? "" : ":" + port);
        }
        // 如果是域名，不需要端口
        return serverName;
    }

    /**
     * 获取租户域名数据
     */
    public static List<TenantDomainResDto> getTenantDomains() throws BaseException {
        String key = CacheKey.TENANT_DOMAINS;
        Object cacheObj = JedisUtils.getObject(key);
        if (cacheObj != null) {
            logger.info("从缓存获取 租户域名列表 成功");
            return (List<TenantDomainResDto>) cacheObj;
        }
        List<TenantDomainResDto> dtos = doGets();
        JedisUtils.setObject(key, dtos);
        return dtos;
    }

    /**
     * 获取所有租户域名信息
     */
    public static List<TenantDomainResDto> doGets() throws BaseException {
        logger.info("调用SCM获取租户域名数据 开始");
        Result<List<TenantDomainResDto>> result = HttpUtils.get(ScmUrl.GET_TENANT_DOMAIN, null,
                new BaseTypeReference<Result<List<TenantDomainResDto>>>() {
                });
        logger.info("调用scm获取租户域名 返回结果:%s", JsonUtils.objectToJson(result));
        if (!result.getSuccess()) {
            logger.info("调用scm获取租户域名 失败：%s", JsonUtils.objectToJson(result));
            throw new BaseException(CommonCode.COMMON_1007);
        }
        logger.info("调用SCM获取租户域名数据 结束");
        return result.getResult();
    }

    public static boolean isIpAddress(String serverName) {
        String[] array = serverName.split("\\.");
        for (String item : array) {
            if (!RegularUtils.isMatch(item, Regular.NUMBER)) {
                return false;
            }
        }
        return true;
    }
}


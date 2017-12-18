package com.yundao.customer.h5.service.login.impl;

import com.yundao.core.code.Result;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.utils.UUIDUtils;
import com.yundao.customer.h5.constant.url.ScmUrl;
import com.yundao.customer.h5.dto.login.KeyPairAreaEnum;
import com.yundao.customer.h5.dto.login.KeyPairResDto;
import com.yundao.customer.h5.dto.login.RsaPublicResDto;
import com.yundao.customer.h5.service.login.LoginService;
import com.yundao.customer.h5.util.HttpUtils;
import com.yundao.customer.h5.util.ObjectAndByteUtils;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gjl on 2017/9/11.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public Result<RsaPublicResDto> selectKeyPairPublic() throws Exception {
        Map<String, Object> methodParams = new HashMap<String, Object>();
        methodParams.put("tenantId", "-1");
        methodParams.put("area", KeyPairAreaEnum.TRAN.getValue());
        Result<KeyPairResDto> result = HttpUtils.get(ScmUrl.KEY_PAIR_GET_BY_TENANT_ID, methodParams,
                new BaseTypeReference<Result<KeyPairResDto>>() {
                });
        KeyPairResDto keyPairResDto = result.getResult();

        KeyPair keyPair = (KeyPair) ObjectAndByteUtils.toObject(keyPairResDto.getKeyPair());
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String uuid = UUIDUtils.getUUID();
        RsaPublicResDto dto = new RsaPublicResDto();
        dto.setExponent(new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray())));
        dto.setModulus(new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));
        dto.setRandom(uuid);

        return Result.newSuccessResult(dto);
    }
}

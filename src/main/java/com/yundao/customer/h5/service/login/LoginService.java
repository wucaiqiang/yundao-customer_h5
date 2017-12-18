

package com.yundao.customer.h5.service.login;

import com.yundao.core.code.Result;
import com.yundao.customer.h5.dto.login.RsaPublicResDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年7月27日 下午4:03:04 
 * @author   wucq
 * @version   
 */
public interface LoginService {
	public Result<RsaPublicResDto> selectKeyPairPublic()throws Exception;
}


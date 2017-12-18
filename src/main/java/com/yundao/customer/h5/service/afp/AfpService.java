
package com.yundao.customer.h5.service.afp;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;

import com.yundao.core.code.Result;
import com.yundao.customer.h5.dto.afp.UserCardResDto;

/**
 * Function: Reason: Date: 2017年9月22日 下午4:20:53
 * 
 * @author wucq
 * @version
 */
public interface AfpService {
	public Result<UserCardResDto> getCardByUUID(String uuid) throws Exception;
	public void getQRCode(HttpServletResponse response,String uuid) throws Exception ;
}

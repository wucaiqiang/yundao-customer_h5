

package com.yundao.customer.h5.util;

import com.yundao.core.config.common.CommonConfigEnum;
import com.yundao.core.ed.Md5Utils;
import com.yundao.core.utils.ConfigUtils;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年7月24日 下午9:36:33 
 * @author   wucq
 * @version   
 */
public class SignUtils {
	public static String getSign(String userId, String ticket, long now) {
		String salt = ConfigUtils.getValue(CommonConfigEnum.COOKIE_MD5_SIGN.getKey());
		return Md5Utils.md5(userId + "," + ticket + "," + now + "," + salt);
	}
}



package com.yundao.customer.h5.service.afp.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.service.AbstractService;
import com.yundao.customer.h5.constant.CodeConstant;
import com.yundao.customer.h5.constant.url.TenantUrl;
import com.yundao.customer.h5.dto.afp.UserCardResDto;
import com.yundao.customer.h5.dto.user.UserRoleDto;
import com.yundao.customer.h5.service.afp.AfpService;
import com.yundao.customer.h5.util.HttpUtils;
import com.yundao.customer.h5.util.qrcode.QRCodeUtil;

/**
 * Function: Reason: Date: 2017年9月22日 下午4:21:18
 * 
 * @author wucq
 * @version
 */
@Service
public class AfpServiceImpl extends AbstractService implements AfpService {

	private Log logger = LogFactory.getLog(AfpServiceImpl.class);

	@Override
	public Result<UserCardResDto> getCardByUUID(String uuid) throws Exception {

		Map<String, Object> methodParams = new HashMap<String, Object>();
		methodParams.put("uuid", uuid);
		Result<UserCardResDto> result = HttpUtils.get(TenantUrl.GET_AFP_CARD, methodParams,
				new BaseTypeReference<Result<UserCardResDto>>() {
				});
		return result;
	}

	@Override
	public void getQRCode(HttpServletResponse response, String uuid) throws Exception {
		Map<String, Object> methodParams = new HashMap<String, Object>();
		methodParams.put("uuid", uuid);
		Result<UserCardResDto> result = HttpUtils.get(TenantUrl.GET_AFP_CARD, methodParams,
				new BaseTypeReference<Result<UserCardResDto>>() {
				});
		if (result.getResult() == null) {
			throw new BaseException(CodeConstant.CODE_1300008);
		}
		UserCardResDto user = result.getResult();
		// 设置文件MIME类型
		response.setContentType("image/jpeg");
		// 设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=qrcode_" + user.getId() + ".jpg");

		StringBuilder roleNamesBuider = new StringBuilder();
		List<UserRoleDto> roleDtos = user.getRoles();
		if (roleDtos != null && !roleDtos.isEmpty()) {
			for (UserRoleDto roleDto : roleDtos) {
				if (roleNamesBuider.length() > 0) {
					roleNamesBuider.append(",");
				}
				roleNamesBuider.append(roleDto.getRoleName());
			}

		}

		StringBuilder text=new StringBuilder();
		text.append("BEGIN:VCARD\n").append("VERSION:3.0\n");

		if (StringUtils.isNotBlank(user.getRealName())) {
			text.append("N:").append(user.getRealName()).append("\n");
		}
		if (StringUtils.isNotBlank(user.getTenantName())) {
			text.append("ORG:").append(user.getTenantName()).append("\n");
		}
		if (StringUtils.isNotBlank(roleNamesBuider.toString())) {
			text.append("TITLE:").append(roleNamesBuider.toString()).append("\n");
		}
		if (StringUtils.isNotBlank(user.getMobile())) {
			text.append("TEL;TYPE=WORK,VOICE:").append(user.getMobile()).append("\n");
		}
		if (StringUtils.isNotBlank(user.getEmail())) {
			text.append("EMAIL:").append(user.getEmail()).append("\n");
		}
		if (StringUtils.isNotBlank(user.getAddress())) {
			text.append("ADR;TYPE=WORK:;").append(user.getAddress()).append(";;;;;\n");
		}
		text.append("END:VCARD");

		logger.info("名片信息：" + text.toString());

		QRCodeUtil.encode(text.toString(), user.getAvatar(), response.getOutputStream(), true);

	}

}

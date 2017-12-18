package com.yundao.customer.h5.dto.afp;

import java.util.List;

import com.yundao.customer.h5.dto.user.UserRoleDto;
import com.yundao.customer.h5.model.apf.BaseUserDetail;

/**
 * 查询用户详情
 * 
 * @author 欧阳利 2017年6月22日
 */
public class UserCardResDto extends BaseUserDetail {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 租户名称
	 */
	private String tenantName;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 名片UUID
	 */
	private String cardUUID;
	/**
	 * 当前用户角色组
	 */
	private List<UserRoleDto> roles;
	/**
	 * 用户部分
	 */
	private String departmentName;
	/**
	 * 理财师头像
	 */
	private String avatar;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 邮箱
	 */
	private String email;

	public String getTenantName() {

		return tenantName;
	}

	public void setTenantName(String tenantName) {

		this.tenantName = tenantName;
	}

	public String getMobile() {

		return mobile;
	}

	public void setMobile(String mobile) {

		this.mobile = mobile;
	}

	public String getCardUUID() {
		this.cardUUID = this.getCardUuid();
		return cardUUID;
	}

	public void setCardUUID(String cardUUID) {

		this.cardUUID = cardUUID;
	}

	public List<UserRoleDto> getRoles() {

		return roles;
	}

	public void setRoles(List<UserRoleDto> roles) {

		this.roles = roles;
	}

	public String getDepartmentName() {

		return departmentName;
	}

	public void setDepartmentName(String departmentName) {

		this.departmentName = departmentName;
	}

	public String getAvatar() {

		return avatar;
	}

	public void setAvatar(String avatar) {

		this.avatar = avatar;
	}

	public String getAddress() {
	
		return address;
	}

	public void setAddress(String address) {
	
		this.address = address;
	}

	public String getEmail() {
	
		return email;
	}

	public void setEmail(String email) {
	
		this.email = email;
	}

}

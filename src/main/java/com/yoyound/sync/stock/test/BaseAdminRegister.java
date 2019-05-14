package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAdminRegister<M extends BaseAdminRegister<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	/**
	 * 帐户名
	 */
	public void setUsername(java.lang.String username) {
		set("username", username);
	}
	
	/**
	 * 帐户名
	 */
	public java.lang.String getUsername() {
		return getStr("username");
	}

	/**
	 * 密码
	 */
	public void setPassword(java.lang.String password) {
		set("password", password);
	}
	
	/**
	 * 密码
	 */
	public java.lang.String getPassword() {
		return getStr("password");
	}

	/**
	 * 手机号
	 */
	public void setMobile(java.lang.String mobile) {
		set("mobile", mobile);
	}
	
	/**
	 * 手机号
	 */
	public java.lang.String getMobile() {
		return getStr("mobile");
	}

	/**
	 * 姓名/企业名
	 */
	public void setName(java.lang.String name) {
		set("name", name);
	}
	
	/**
	 * 姓名/企业名
	 */
	public java.lang.String getName() {
		return getStr("name");
	}

	/**
	 * 申请原因
	 */
	public void setDesc(java.lang.String desc) {
		set("desc", desc);
	}
	
	/**
	 * 申请原因
	 */
	public java.lang.String getDesc() {
		return getStr("desc");
	}

	public void setAddtime(java.lang.Integer addtime) {
		set("addtime", addtime);
	}
	
	public java.lang.Integer getAddtime() {
		return getInt("addtime");
	}

	/**
	 * 审核状态：0=待审核，1=通过，2=不通过
	 */
	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}
	
	/**
	 * 审核状态：0=待审核，1=通过，2=不通过
	 */
	public java.lang.Integer getStatus() {
		return getInt("status");
	}

	public void setIsDelete(java.lang.Integer isDelete) {
		set("is_delete", isDelete);
	}
	
	public java.lang.Integer getIsDelete() {
		return getInt("is_delete");
	}

}

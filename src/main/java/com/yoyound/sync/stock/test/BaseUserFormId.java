package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUserFormId<M extends BaseUserFormId<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setUserId(java.lang.Integer userId) {
		set("user_id", userId);
	}
	
	public java.lang.Integer getUserId() {
		return getInt("user_id");
	}

	public void setFormId(java.lang.String formId) {
		set("form_id", formId);
	}
	
	public java.lang.String getFormId() {
		return getStr("form_id");
	}

	/**
	 * 剩余使用次数
	 */
	public void setTimes(java.lang.Integer times) {
		set("times", times);
	}
	
	/**
	 * 剩余使用次数
	 */
	public java.lang.Integer getTimes() {
		return getInt("times");
	}

	public void setAddtime(java.lang.Integer addtime) {
		set("addtime", addtime);
	}
	
	public java.lang.Integer getAddtime() {
		return getInt("addtime");
	}

}

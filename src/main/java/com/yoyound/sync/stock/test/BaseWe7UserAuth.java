package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseWe7UserAuth<M extends BaseWe7UserAuth<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Long id) {
		set("id", id);
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public void setWe7UserId(java.lang.Integer we7UserId) {
		set("we7_user_id", we7UserId);
	}
	
	public java.lang.Integer getWe7UserId() {
		return getInt("we7_user_id");
	}

	public void setAuth(java.lang.String auth) {
		set("auth", auth);
	}
	
	public java.lang.String getAuth() {
		return getStr("auth");
	}

}

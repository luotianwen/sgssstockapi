package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseLotterySetting<M extends BaseLotterySetting<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setStoreId(java.lang.Integer storeId) {
		set("store_id", storeId);
	}
	
	public java.lang.Integer getStoreId() {
		return getInt("store_id");
	}

	/**
	 * 规则
	 */
	public void setRule(java.lang.String rule) {
		set("rule", rule);
	}
	
	/**
	 * 规则
	 */
	public java.lang.String getRule() {
		return getStr("rule");
	}

	/**
	 * 小程序标题
	 */
	public void setTitle(java.lang.String title) {
		set("title", title);
	}
	
	/**
	 * 小程序标题
	 */
	public java.lang.String getTitle() {
		return getStr("title");
	}

	/**
	 * 0不强制 1强制
	 */
	public void setType(java.lang.Integer type) {
		set("type", type);
	}
	
	/**
	 * 0不强制 1强制
	 */
	public java.lang.Integer getType() {
		return getInt("type");
	}

}

package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDiyPage<M extends BaseDiyPage<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Long id) {
		set("id", id);
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public void setStoreId(java.lang.Integer storeId) {
		set("store_id", storeId);
	}
	
	public java.lang.Integer getStoreId() {
		return getInt("store_id");
	}

	/**
	 * 页面标题
	 */
	public void setTitle(java.lang.String title) {
		set("title", title);
	}
	
	/**
	 * 页面标题
	 */
	public java.lang.String getTitle() {
		return getStr("title");
	}

	/**
	 * 模板ID
	 */
	public void setTemplateId(java.lang.Integer templateId) {
		set("template_id", templateId);
	}
	
	/**
	 * 模板ID
	 */
	public java.lang.Integer getTemplateId() {
		return getInt("template_id");
	}

	public void setIsDelete(java.lang.Integer isDelete) {
		set("is_delete", isDelete);
	}
	
	public java.lang.Integer getIsDelete() {
		return getInt("is_delete");
	}

	public void setAddtime(java.lang.Integer addtime) {
		set("addtime", addtime);
	}
	
	public java.lang.Integer getAddtime() {
		return getInt("addtime");
	}

	/**
	 * 状态 0--禁用 1--启用
	 */
	public void setStatus(java.lang.Boolean status) {
		set("status", status);
	}
	
	/**
	 * 状态 0--禁用 1--启用
	 */
	public java.lang.Boolean getStatus() {
		return get("status");
	}

	/**
	 * 是否覆盖首页 0--不覆盖 1--覆盖
	 */
	public void setIsIndex(java.lang.Boolean isIndex) {
		set("is_index", isIndex);
	}
	
	/**
	 * 是否覆盖首页 0--不覆盖 1--覆盖
	 */
	public java.lang.Boolean getIsIndex() {
		return get("is_index");
	}

}

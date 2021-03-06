package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseYyForm<M extends BaseYyForm<M>> extends Model<M> implements IBean {

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

	public void setGoodsId(java.lang.Integer goodsId) {
		set("goods_id", goodsId);
	}
	
	public java.lang.Integer getGoodsId() {
		return getInt("goods_id");
	}

	/**
	 * 字段名称
	 */
	public void setName(java.lang.String name) {
		set("name", name);
	}
	
	/**
	 * 字段名称
	 */
	public java.lang.String getName() {
		return getStr("name");
	}

	/**
	 * 字段类型
	 */
	public void setType(java.lang.String type) {
		set("type", type);
	}
	
	/**
	 * 字段类型
	 */
	public java.lang.String getType() {
		return getStr("type");
	}

	/**
	 * 是否必填
	 */
	public void setRequired(java.lang.Long required) {
		set("required", required);
	}
	
	/**
	 * 是否必填
	 */
	public java.lang.Long getRequired() {
		return getLong("required");
	}

	/**
	 * 默认值
	 */
	public void setDefault(java.lang.String _default) {
		set("default", _default);
	}
	
	/**
	 * 默认值
	 */
	public java.lang.String getDefault() {
		return getStr("default");
	}

	/**
	 * 提示语
	 */
	public void setTip(java.lang.String tip) {
		set("tip", tip);
	}
	
	/**
	 * 提示语
	 */
	public java.lang.String getTip() {
		return getStr("tip");
	}

	public void setSort(java.lang.Integer sort) {
		set("sort", sort);
	}
	
	public java.lang.Integer getSort() {
		return getInt("sort");
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
	 * 单选、复选项 值
	 */
	public void setOption(java.lang.String option) {
		set("option", option);
	}
	
	/**
	 * 单选、复选项 值
	 */
	public java.lang.String getOption() {
		return getStr("option");
	}

}

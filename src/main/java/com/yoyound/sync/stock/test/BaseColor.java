package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseColor<M extends BaseColor<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	/**
	 * rgb颜色码 例如："0，0，0"
	 */
	public void setRgb(java.lang.String rgb) {
		set("rgb", rgb);
	}
	
	/**
	 * rgb颜色码 例如："0，0，0"
	 */
	public java.lang.String getRgb() {
		return getStr("rgb");
	}

	/**
	 * 16进制颜色码例如：#ffffff
	 */
	public void setColor(java.lang.String color) {
		set("color", color);
	}
	
	/**
	 * 16进制颜色码例如：#ffffff
	 */
	public java.lang.String getColor() {
		return getStr("color");
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

}

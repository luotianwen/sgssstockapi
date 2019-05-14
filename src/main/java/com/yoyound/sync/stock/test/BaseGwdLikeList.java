package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseGwdLikeList<M extends BaseGwdLikeList<M>> extends Model<M> implements IBean {

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

	public void setGoodId(java.lang.Integer goodId) {
		set("good_id", goodId);
	}
	
	public java.lang.Integer getGoodId() {
		return getInt("good_id");
	}

	public void setIsDelete(java.lang.Integer isDelete) {
		set("is_delete", isDelete);
	}
	
	public java.lang.Integer getIsDelete() {
		return getInt("is_delete");
	}

	public void setAddtime(java.lang.String addtime) {
		set("addtime", addtime);
	}
	
	public java.lang.String getAddtime() {
		return getStr("addtime");
	}

	/**
	 * 0.商城|1.秒杀|2.拼团
	 */
	public void setType(java.lang.Boolean type) {
		set("type", type);
	}
	
	/**
	 * 0.商城|1.秒杀|2.拼团
	 */
	public java.lang.Boolean getType() {
		return get("type");
	}

}

package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCouponAutoSend<M extends BaseCouponAutoSend<M>> extends Model<M> implements IBean {

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

	public void setCouponId(java.lang.Integer couponId) {
		set("coupon_id", couponId);
	}
	
	public java.lang.Integer getCouponId() {
		return getInt("coupon_id");
	}

	/**
	 * 触发事件：1=分享，2=购买并付款
	 */
	public void setEvent(java.lang.Integer event) {
		set("event", event);
	}
	
	/**
	 * 触发事件：1=分享，2=购买并付款
	 */
	public java.lang.Integer getEvent() {
		return getInt("event");
	}

	/**
	 * 最多发放次数，0表示不限制
	 */
	public void setSendTimes(java.lang.Integer sendTimes) {
		set("send_times", sendTimes);
	}
	
	/**
	 * 最多发放次数，0表示不限制
	 */
	public java.lang.Integer getSendTimes() {
		return getInt("send_times");
	}

	public void setAddtime(java.lang.Integer addtime) {
		set("addtime", addtime);
	}
	
	public java.lang.Integer getAddtime() {
		return getInt("addtime");
	}

	public void setIsDelete(java.lang.Integer isDelete) {
		set("is_delete", isDelete);
	}
	
	public java.lang.Integer getIsDelete() {
		return getInt("is_delete");
	}

}

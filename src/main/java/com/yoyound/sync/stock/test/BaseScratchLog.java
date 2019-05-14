package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseScratchLog<M extends BaseScratchLog<M>> extends Model<M> implements IBean {

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

	public void setPondId(java.lang.Integer pondId) {
		set("pond_id", pondId);
	}
	
	public java.lang.Integer getPondId() {
		return getInt("pond_id");
	}

	public void setUserId(java.lang.Integer userId) {
		set("user_id", userId);
	}
	
	public java.lang.Integer getUserId() {
		return getInt("user_id");
	}

	/**
	 *  0预领取 1 未领取 2 已领取
	 */
	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}
	
	/**
	 *  0预领取 1 未领取 2 已领取
	 */
	public java.lang.Integer getStatus() {
		return getInt("status");
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(java.lang.Integer createTime) {
		set("create_time", createTime);
	}
	
	/**
	 * 创建时间
	 */
	public java.lang.Integer getCreateTime() {
		return getInt("create_time");
	}

	/**
	 * 领取时间
	 */
	public void setRaffleTime(java.lang.Integer raffleTime) {
		set("raffle_time", raffleTime);
	}
	
	/**
	 * 领取时间
	 */
	public java.lang.Integer getRaffleTime() {
		return getInt("raffle_time");
	}

	public void setType(java.lang.Integer type) {
		set("type", type);
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

	public void setNum(java.lang.Integer num) {
		set("num", num);
	}
	
	public java.lang.Integer getNum() {
		return getInt("num");
	}

	public void setGiftId(java.lang.Integer giftId) {
		set("gift_id", giftId);
	}
	
	public java.lang.Integer getGiftId() {
		return getInt("gift_id");
	}

	public void setCouponId(java.lang.Integer couponId) {
		set("coupon_id", couponId);
	}
	
	public java.lang.Integer getCouponId() {
		return getInt("coupon_id");
	}

	public void setPrice(java.math.BigDecimal price) {
		set("price", price);
	}
	
	public java.math.BigDecimal getPrice() {
		return get("price");
	}

	/**
	 * 规格
	 */
	public void setAttr(java.lang.String attr) {
		set("attr", attr);
	}
	
	/**
	 * 规格
	 */
	public java.lang.String getAttr() {
		return getStr("attr");
	}

	/**
	 * 订单号
	 */
	public void setOrderId(java.lang.Integer orderId) {
		set("order_id", orderId);
	}
	
	/**
	 * 订单号
	 */
	public java.lang.Integer getOrderId() {
		return getInt("order_id");
	}

}

package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseReOrder<M extends BaseReOrder<M>> extends Model<M> implements IBean {

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
	 * 订单号
	 */
	public void setOrderNo(java.lang.String orderNo) {
		set("order_no", orderNo);
	}
	
	/**
	 * 订单号
	 */
	public java.lang.String getOrderNo() {
		return getStr("order_no");
	}

	/**
	 * 用户
	 */
	public void setUserId(java.lang.Integer userId) {
		set("user_id", userId);
	}
	
	/**
	 * 用户
	 */
	public java.lang.Integer getUserId() {
		return getInt("user_id");
	}

	/**
	 * 支付金额
	 */
	public void setPayPrice(java.math.BigDecimal payPrice) {
		set("pay_price", payPrice);
	}
	
	/**
	 * 支付金额
	 */
	public java.math.BigDecimal getPayPrice() {
		return get("pay_price");
	}

	/**
	 * 赠送金额
	 */
	public void setSendPrice(java.math.BigDecimal sendPrice) {
		set("send_price", sendPrice);
	}
	
	/**
	 * 赠送金额
	 */
	public java.math.BigDecimal getSendPrice() {
		return get("send_price");
	}

	/**
	 * 支付方式 1--微信支付
	 */
	public void setPayType(java.lang.Integer payType) {
		set("pay_type", payType);
	}
	
	/**
	 * 支付方式 1--微信支付
	 */
	public java.lang.Integer getPayType() {
		return getInt("pay_type");
	}

	/**
	 * 是否支付 0--未支付 1--支付
	 */
	public void setIsPay(java.lang.Integer isPay) {
		set("is_pay", isPay);
	}
	
	/**
	 * 是否支付 0--未支付 1--支付
	 */
	public java.lang.Integer getIsPay() {
		return getInt("is_pay");
	}

	/**
	 * 支付时间
	 */
	public void setPayTime(java.lang.Integer payTime) {
		set("pay_time", payTime);
	}
	
	/**
	 * 支付时间
	 */
	public java.lang.Integer getPayTime() {
		return getInt("pay_time");
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

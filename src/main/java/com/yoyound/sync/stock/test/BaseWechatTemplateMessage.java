package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseWechatTemplateMessage<M extends BaseWechatTemplateMessage<M>> extends Model<M> implements IBean {

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
	 * 支付通知模板消息id
	 */
	public void setPayTpl(java.lang.String payTpl) {
		set("pay_tpl", payTpl);
	}
	
	/**
	 * 支付通知模板消息id
	 */
	public java.lang.String getPayTpl() {
		return getStr("pay_tpl");
	}

	/**
	 * 发货通知模板消息id
	 */
	public void setSendTpl(java.lang.String sendTpl) {
		set("send_tpl", sendTpl);
	}
	
	/**
	 * 发货通知模板消息id
	 */
	public java.lang.String getSendTpl() {
		return getStr("send_tpl");
	}

	/**
	 * 退款通知模板消息id
	 */
	public void setRefundTpl(java.lang.String refundTpl) {
		set("refund_tpl", refundTpl);
	}
	
	/**
	 * 退款通知模板消息id
	 */
	public java.lang.String getRefundTpl() {
		return getStr("refund_tpl");
	}

	/**
	 * 订单未支付通知模板消息id
	 */
	public void setNotPayTpl(java.lang.String notPayTpl) {
		set("not_pay_tpl", notPayTpl);
	}
	
	/**
	 * 订单未支付通知模板消息id
	 */
	public java.lang.String getNotPayTpl() {
		return getStr("not_pay_tpl");
	}

	/**
	 * 订单取消通知模板消息id
	 */
	public void setRevokeTpl(java.lang.String revokeTpl) {
		set("revoke_tpl", revokeTpl);
	}
	
	/**
	 * 订单取消通知模板消息id
	 */
	public java.lang.String getRevokeTpl() {
		return getStr("revoke_tpl");
	}

}

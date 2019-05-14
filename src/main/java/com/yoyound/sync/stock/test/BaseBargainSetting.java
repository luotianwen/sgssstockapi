package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseBargainSetting<M extends BaseBargainSetting<M>> extends Model<M> implements IBean {

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
	 * 是否打印 0--否 1--是
	 */
	public void setIsPrint(java.lang.Integer isPrint) {
		set("is_print", isPrint);
	}
	
	/**
	 * 是否打印 0--否 1--是
	 */
	public java.lang.Integer getIsPrint() {
		return getInt("is_print");
	}

	/**
	 * 是否参与分销 0--不参与 1--参与
	 */
	public void setIsShare(java.lang.Integer isShare) {
		set("is_share", isShare);
	}
	
	/**
	 * 是否参与分销 0--不参与 1--参与
	 */
	public java.lang.Integer getIsShare() {
		return getInt("is_share");
	}

	/**
	 * 是否发送短信 0--否 1--是
	 */
	public void setIsSms(java.lang.Integer isSms) {
		set("is_sms", isSms);
	}
	
	/**
	 * 是否发送短信 0--否 1--是
	 */
	public java.lang.Integer getIsSms() {
		return getInt("is_sms");
	}

	/**
	 * 是否发送邮件 0--否 1--是
	 */
	public void setIsMail(java.lang.Integer isMail) {
		set("is_mail", isMail);
	}
	
	/**
	 * 是否发送邮件 0--否 1--是
	 */
	public java.lang.Integer getIsMail() {
		return getInt("is_mail");
	}

	/**
	 * 活动规则
	 */
	public void setContent(java.lang.String content) {
		set("content", content);
	}
	
	/**
	 * 活动规则
	 */
	public java.lang.String getContent() {
		return getStr("content");
	}

	public void setShareTitle(java.lang.String shareTitle) {
		set("share_title", shareTitle);
	}
	
	public java.lang.String getShareTitle() {
		return getStr("share_title");
	}

}

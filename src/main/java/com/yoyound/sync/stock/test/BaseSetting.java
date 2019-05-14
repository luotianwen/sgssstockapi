package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSetting<M extends BaseSetting<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setFirst(java.math.BigDecimal first) {
		set("first", first);
	}
	
	public java.math.BigDecimal getFirst() {
		return get("first");
	}

	public void setSecond(java.math.BigDecimal second) {
		set("second", second);
	}
	
	public java.math.BigDecimal getSecond() {
		return get("second");
	}

	public void setThird(java.math.BigDecimal third) {
		set("third", third);
	}
	
	public java.math.BigDecimal getThird() {
		return get("third");
	}

	/**
	 * 商城id
	 */
	public void setStoreId(java.lang.Integer storeId) {
		set("store_id", storeId);
	}
	
	/**
	 * 商城id
	 */
	public java.lang.Integer getStoreId() {
		return getInt("store_id");
	}

	/**
	 * 分销层级 0--不开启 1--一级分销 2--二级分销 3--三级分销
	 */
	public void setLevel(java.lang.Integer level) {
		set("level", level);
	}
	
	/**
	 * 分销层级 0--不开启 1--一级分销 2--二级分销 3--三级分销
	 */
	public java.lang.Integer getLevel() {
		return getInt("level");
	}

	/**
	 * 成为下线条件 0--首次点击分享链接 1--首次下单 2--首次付款
	 */
	public void setCondition(java.lang.Integer condition) {
		set("condition", condition);
	}
	
	/**
	 * 成为下线条件 0--首次点击分享链接 1--首次下单 2--首次付款
	 */
	public java.lang.Integer getCondition() {
		return getInt("condition");
	}

	/**
	 * 成为分销商的条件 
0--无条件
1--申请
	 */
	public void setShareCondition(java.lang.Integer shareCondition) {
		set("share_condition", shareCondition);
	}
	
	/**
	 * 成为分销商的条件 
0--无条件
1--申请
	 */
	public java.lang.Integer getShareCondition() {
		return getInt("share_condition");
	}

	/**
	 * 分销佣金 的 用户须知
	 */
	public void setContent(java.lang.String content) {
		set("content", content);
	}
	
	/**
	 * 分销佣金 的 用户须知
	 */
	public java.lang.String getContent() {
		return getStr("content");
	}

	/**
	 * 提现方式 0--支付宝转账  1--微信企业支付
	 */
	public void setPayType(java.lang.Integer payType) {
		set("pay_type", payType);
	}
	
	/**
	 * 提现方式 0--支付宝转账  1--微信企业支付
	 */
	public java.lang.Integer getPayType() {
		return getInt("pay_type");
	}

	/**
	 * 最小提现额度
	 */
	public void setMinMoney(java.math.BigDecimal minMoney) {
		set("min_money", minMoney);
	}
	
	/**
	 * 最小提现额度
	 */
	public java.math.BigDecimal getMinMoney() {
		return get("min_money");
	}

	/**
	 * 分销协议
	 */
	public void setAgree(java.lang.String agree) {
		set("agree", agree);
	}
	
	/**
	 * 分销协议
	 */
	public java.lang.String getAgree() {
		return getStr("agree");
	}

	public void setFirstName(java.lang.String firstName) {
		set("first_name", firstName);
	}
	
	public java.lang.String getFirstName() {
		return getStr("first_name");
	}

	public void setSecondName(java.lang.String secondName) {
		set("second_name", secondName);
	}
	
	public java.lang.String getSecondName() {
		return getStr("second_name");
	}

	public void setThirdName(java.lang.String thirdName) {
		set("third_name", thirdName);
	}
	
	public java.lang.String getThirdName() {
		return getStr("third_name");
	}

	public void setPicUrl1(java.lang.String picUrl1) {
		set("pic_url_1", picUrl1);
	}
	
	public java.lang.String getPicUrl1() {
		return getStr("pic_url_1");
	}

	public void setPicUrl2(java.lang.String picUrl2) {
		set("pic_url_2", picUrl2);
	}
	
	public java.lang.String getPicUrl2() {
		return getStr("pic_url_2");
	}

	/**
	 * 分销金额 0--百分比金额  1--固定金额
	 */
	public void setPriceType(java.lang.Integer priceType) {
		set("price_type", priceType);
	}
	
	/**
	 * 分销金额 0--百分比金额  1--固定金额
	 */
	public java.lang.Integer getPriceType() {
		return getInt("price_type");
	}

	/**
	 * 银行卡支付  0 --不使用  1--使用
	 */
	public void setBank(java.lang.Integer bank) {
		set("bank", bank);
	}
	
	/**
	 * 银行卡支付  0 --不使用  1--使用
	 */
	public java.lang.Integer getBank() {
		return getInt("bank");
	}

	/**
	 * 余额提现 0=关闭 1=开启
	 */
	public void setRemainingSum(java.lang.Boolean remainingSum) {
		set("remaining_sum", remainingSum);
	}
	
	/**
	 * 余额提现 0=关闭 1=开启
	 */
	public java.lang.Boolean getRemainingSum() {
		return get("remaining_sum");
	}

	/**
	 * 自购返利
	 */
	public void setRebate(java.math.BigDecimal rebate) {
		set("rebate", rebate);
	}
	
	/**
	 * 自购返利
	 */
	public java.math.BigDecimal getRebate() {
		return get("rebate");
	}

	/**
	 * 是否开启自购返利
	 */
	public void setIsRebate(java.lang.Integer isRebate) {
		set("is_rebate", isRebate);
	}
	
	/**
	 * 是否开启自购返利
	 */
	public java.lang.Integer getIsRebate() {
		return getInt("is_rebate");
	}

	/**
	 * 购买商品自动成为分销商：0.关闭 1.任意商品 2.指定商品
	 */
	public void setShareGoodStatus(java.lang.Integer shareGoodStatus) {
		set("share_good_status", shareGoodStatus);
	}
	
	/**
	 * 购买商品自动成为分销商：0.关闭 1.任意商品 2.指定商品
	 */
	public java.lang.Integer getShareGoodStatus() {
		return getInt("share_good_status");
	}

	/**
	 * 购买指定分销商品Id
	 */
	public void setShareGoodId(java.lang.Integer shareGoodId) {
		set("share_good_id", shareGoodId);
	}
	
	/**
	 * 购买指定分销商品Id
	 */
	public java.lang.Integer getShareGoodId() {
		return getInt("share_good_id");
	}

}

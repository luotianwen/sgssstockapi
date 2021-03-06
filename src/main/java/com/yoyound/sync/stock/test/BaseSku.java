package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSku<M extends BaseSku<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public void setGoodsId(java.lang.String goodsId) {
		set("goods_id", goodsId);
	}
	public java.lang.String getGoodsId() {
		return getStr("goods_id");
	}



	public java.lang.String getSpec1() {
		return getStr("spec1");
	}
	public void setSpec1(java.lang.String spec1) {
		set("spec1", spec1);
	}

	public java.lang.String getSpec2() {
		return getStr("spec2");
	}
	public void setSpec2(java.lang.String spec2) {
		set("spec2", spec2);
	}

	/**
	 * 售价
	 */
	public void setDiscount(double discount) {
		set("discount", discount);
	}

	/**
	 * 售价
	 */
	public Double getDiscount() {
		return get("discount");
	}
	/**
	 * 售价
	 */
	public void setPrice(Double price) {
		set("price", price);
	}

	/**
	 * 售价
	 */
	public Double getPrice() {
		return get("price");
	}

	/**
	 * 商品类别
	 */
	public void setStock(java.lang.Integer stock) {
		set("stock", stock);
	}

	/**
	 * 商品类别
	 */
	public java.lang.Integer getStock() {
		return getInt("stock");
	}


	/**
	 * 商品类别
	 */
	public void setSort(java.lang.Integer sort) {
		set("sort", sort);
	}

	/**
	 * 商品类别
	 */
	public java.lang.Integer getSort() {
		return getInt("sort");
	}

	/**
	 * 售价
	 */
	public void setMarketPrice(Double marketPrice) {
		set("market_price", marketPrice);
	}

	/**
	 * 售价
	 */
	public Double getMarketPrice() {
		return getDouble("market_price");
	}

	/**
	 * 售价
	 */
	public void setProfitDiscount(Double profitDiscount) {
		set("profit_discount", profitDiscount);
	}

	/**
	 * 售价
	 */
	public Double getProfitDiscount() {
		return get("profit_discount");
	}

	/**
	 * 售价
	 */
	public void setSettlementDiscount(Double settlementDiscount) {
		set("settlement_discount", settlementDiscount);
	}

	/**
	 * 售价
	 */
	public Double getSettlementDiscount() {
		return getDouble("settlement_discount");
	}


	/**
	 * 售价
	 */
	public void setProfit(Double profit) {
		set("profit", profit);
	}

	/**
	 * 售价
	 */
	public Double getProfit() {
		return getDouble("profit");
	}




	/**
	 * 售价
	 */
	public void setSettlementPrice(Double settlementPrice) {
		set("settlement_price", settlementPrice);
	}

	/**
	 * 售价
	 */
	public Double getSettlementPrice() {
		return getDouble("settlement_price");
	}





}

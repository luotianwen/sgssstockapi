package com.yoyound.sync.stock.test;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseGoodsCard<M extends BaseGoodsCard<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setGoodsId(java.lang.Integer goodsId) {
		set("goods_id", goodsId);
	}
	
	public java.lang.Integer getGoodsId() {
		return getInt("goods_id");
	}

	/**
	 * 卡券id
	 */
	public void setCardId(java.lang.Integer cardId) {
		set("card_id", cardId);
	}
	
	/**
	 * 卡券id
	 */
	public java.lang.Integer getCardId() {
		return getInt("card_id");
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

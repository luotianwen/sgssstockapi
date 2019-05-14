package com.yoyound.sync.stock;

import com.alibaba.fastjson.JSON;
import com.jfinal.kit.JsonKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.yoyound.sync.stock.test.*;

import java.util.*;

public class SyncStockTask implements Runnable{
    Log log=Log.getLog(SyncStockTask.class);
    @Override
    public void run() {
        //读取同步品牌
        List<Record> bands= getSyncBrands();
        for (Record b:bands
             ) {
            List<Record> goods=getSyncGoods(b.getStr("id"));
            log.info(b.getStr("name")+"有"+goods.size()+"商品");
            for (Record g:goods
                 ) {
                saveOldSku(g);
                saveNewSku(g);
            }

        }


    }
    private String saveNewSku(Record go){
        int num=0;
        {
            num=0;
            Record good=Db.use("old").findFirst("SELECT g.id as id, g.num as gid,  g.name,g.price,g.market_price,d.details ,g.logo ,g.artNo,b.num from s_goods g ,s_goods_detail d ,s_brand b where g.brand_id=b.id and  d.goods_id=g.id and g.id=?",go.getStr("id"));
            if(good==null){
                return null;
            }
            List<Record> skus= Db.use("old").find("SELECT s.goods_id as id ,s.stock as num ,s.spec1,s.spec2,s.price as price   from s_goods_sku s where s.goods_id=?",good.getStr("id"));

            List<Record>  alldata=  new ArrayList<>();
            for (Record s:skus ){
                num+=s.getInt("num");
                Record data=new Record();
                List<Record> attr_list=new ArrayList<>();
                if(null!=s.getStr("spec2")) {
                    Record attr1 = Db.use("new").findFirst("SELECT id as attr_id,attr_name   from hjmall_attr where attr_name=?", s.getStr("spec1"));
                    if (null == attr1) {
                        attr1 = new Record();
                        Attr a = new Attr();
                        a.setAttrName(s.getStr("spec1"));
                        a.setAttrGroupId(4);
                        a.setIsDelete(0);
                        a.setIsDefault(0);
                        a.save();
                        attr1.set("attr_id", a.getId());
                        attr1.set("attr_name", a.getAttrName());
                    }
                    attr_list.add(attr1);
                    Record attr2= Db.use("new").findFirst("SELECT id as attr_id,attr_name   from hjmall_attr where attr_name=?",s.getStr("spec2"));
                    if(null==attr2) {
                        attr2 = new Record();
                        Attr a=new Attr();
                        a.setAttrName(s.getStr("spec2"));
                        a.setAttrGroupId(2);
                        a.setIsDelete(0);
                        a.setIsDefault(0);
                        a.save();
                        attr2.set("attr_id", a.getId());
                        attr2.set("attr_name", a.getAttrName());
                    }
                    attr_list.add(attr2);
                }
               else{
                    Record attr1 = Db.use("new").findFirst("SELECT id as attr_id,attr_name   from hjmall_attr where attr_name=?", s.getStr("spec1"));
                    if (null == attr1) {
                        attr1 = new Record();
                        Attr a = new Attr();
                        a.setAttrName(s.getStr("spec1"));
                        a.setAttrGroupId(2);
                        a.setIsDelete(0);
                        a.setIsDefault(0);
                        a.save();
                        attr1.set("attr_id", a.getId());
                        attr1.set("attr_name", a.getAttrName());
                    }
                    attr_list.add(attr1);
                }


                data.set("attr_list",attr_list);
                data.set("num",s.get("num"));
                data.set("price",s.get("price"));
                data.set("no",good.getStr("artNo"));
                data.set("pic","");
                data.set("share_commission_first","");
                data.set("share_commission_second","");
                data.set("share_commission_third","");
                data.set("member1","");
                data.set("member2","");
                data.set("member3","");
                data.set("member4","");
                alldata.add(data);
            }
            String logo=good.getStr("logo");


            GoodsPic pic=new GoodsPic();
            pic.setGoodsId(good.getInt("gid"));

            if(logo.indexOf("http")<0){
                pic.setPicUrl("http://image.yoyound.com/"+logo);

            }else{
                pic.setPicUrl(good.getStr("logo"));
            }

            pic.setIsDelete(0);
            pic.save();
            ////品牌
            GoodsCat cat=new GoodsCat();
            cat.setGoodsId(good.getInt("gid"));
            cat.setStoreId(2);
            cat.setAddtime(new Long( System.currentTimeMillis()/1000).intValue());
            cat.setIsDelete(0);
            cat.setCatId(good.getInt("num"));
            cat.save();

            //分类
            List<Record> cs= Db.use("old").find("SELECT c.num  from s_goods_category s , s_category c where  c.id=s.category_id and  s.goods_id=?",good.getStr("id"));
            for (Record rs:cs
            ) {
                GoodsCat c=new GoodsCat();
                c.setGoodsId(good.getInt("gid"));
                c.setStoreId(2);
                c.setAddtime(new Long( System.currentTimeMillis()/1000).intValue());
                c.setIsDelete(0);
                c.setCatId(rs.getInt("num"));
                c.save();
            }

            Record og=Db.use("new").findFirst("select id from  hjmall_goods where id=?",good.getInt("gid"));
            if(null==og) {
                Goods g = new Goods();
                g.setAddtime(new Long(System.currentTimeMillis()).intValue());
                g.setStoreId(2);
                g.setId(good.getInt("gid"));
                //good
                g.setName(good.getStr("name"));
                g.setPrice(good.getFloat("price"));
                g.setOriginalPrice(good.getFloat("market_price"));
                g.setDetail(good.getStr("details"));
                g.setCatId(0l);

                g.setStatus(1);
                g.setIsDelete(0);
                g.setAttr(JsonKit.toJson(alldata));
                g.setService("正品保障,极速发货,7天退换货");
                g.setSort(1000);
                g.setVirtualSales(new Random().nextInt(500) + 1);

                if (logo == null) {
                    return null;
                }
                if (logo.indexOf("http") < 0) {
                    g.setCoverPic("http://image.yoyound.com/" + logo);
                } else {
                    g.setCoverPic(good.getStr("logo"));

                }
                g.setUnit("件");
                g.setIndividualShare(0);
                g.setFullCut("{\"pieces\":\"\",\"forehead\":\"\"}");
                g.setIntegral("{\"give\":\"0\",\"forehead\":\"\",\"more\":\"\"}");
                g.setGoodsNum(num);
                g.save();
            }
            else{
                Db.use("new").update("update hjmall_goods set attr=? where id=? ",JsonKit.toJson(alldata),good.getInt("gid"));
            }
            log.info( "goods"+good.getInt("gid")+"同步商品完成");

        }
        return null;
    }
    private void saveOldSku(Record g){
        List<Stock> stocks= getSyncStock(g.getStr("artNo"));
        if(stocks.size()==0){
            log.info("下架商品"+g.getStr("artNo"));
             Db.use("old").update("update s_goods  set state=0,update_date=now() where id=?",g.getStr("id"));
             Db.use("old").update("update s_goods_sku set stock=0 where goods_id=?",g.getStr("id"));
        }
        for (Stock s:stocks
        ) {
            Record allsku=Db.use("old").findFirst("select * from s_goods_sku where goods_id=?   limit 1 " ,g.getStr("id"));
            if(null==allsku){
                Sku su = new Sku();
                su.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                su.setGoodsId(g.getStr("id"));
                su.setStock(s.getInnernum());
                su.setSpec1(s.getSize());
                su.setMarketPrice(s.getMarketprice());
                su.setDiscount(s.getDiscount()+2.0);
                su.setPrice(s.getMarketprice()*su.getProfit());
                su.setSettlementPrice(s.getMarketprice()*s.getDiscount());
                su.setProfit(su.getPrice()-su.getSettlementPrice());
                su.save();
                log.info(g.getStr("id")+"没有sku商品增加了"+su.getId());
            }
            else {
                Record sku = Db.use("old").findFirst("select * from s_goods_sku where goods_id=? and  (spec1 =? or spec2=?) ", g.getStr("id"), s.getSize(), s.getSize());
                if (null == sku) {
                    Sku su = new Sku();
                    su.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                    su.setGoodsId(g.getStr("id"));
                    su.setStock(s.getInnernum());
                    su.setSpec1(s.getSize());
                    su.setDiscount(allsku.getDouble("discount"));
                    su.setMarketPrice(allsku.getDouble("market_price"));
                    su.setSettlementDiscount(allsku.getDouble("settlement_discount"));
                    su.setPrice(allsku.getDouble("price"));
                    su.setSettlementPrice(allsku.getDouble("settlement_price"));
                    su.setProfit(allsku.getDouble("profit"));
                     su.save();
                    log.info(g.getStr("id")+"没有sku商品增加了"+su.getId());
                } else {
                    //修改库存
                    Db.use("old").update("update s_goods_sku set stock=? where id=?", s.getInnernum(), sku.getStr("id"));
                    log.info(g.getStr("id")+"修改库存"+ sku.getStr("id"));

                }
            }
        }
    }


   private List<Stock> getSyncStock(String articleno){
       Map map = new HashMap();
       map.put("sign", Cont.SIGN);
       map.put("articleno", articleno.trim());
       //配货率
       map.put("pickingRate","70");
       //最大折扣
       map.put("maxDiscount","8");
       //是否支持退货
       map.put("return_type","1");
       String str = Cont.post(Cont.GROUPSTOCK, map);
       log.info("天马仓库数据"+str);
       //String str="{\"total\":1,\"rows\":[{\"wareHouseName\":\"成都特供仓\",\"sex\":\"男\",\"division\":\"服\",\"marketprice\":348.0,\"ukSize\":\"S\",\"articleno\":\"288254-010\",\"brandName\":\"耐克\",\"discount\":2.3,\"quarter\":\"\",\"innerNum\":500,\"size\":\"S\",\"barcode\":\"4056561268379\"},{\"wareHouseName\":\"成都特供仓\",\"sex\":\"男\",\"division\":\"服\",\"marketprice\":1399.0,\"ukSize\":\"10\",\"articleno\":\"304775-125\",\"brandName\":\"耐克\",\"discount\":10.1,\"quarter\":\"15Q2\",\"innerNum\":500,\"size\":\"10\",\"barcode\":\"4056561268378\"}]}";
       BackData j = JSON.parseObject(str, BackData.class);
       Map<String,Stock> dataMap=new HashMap();
       List<Stock> datass = new ArrayList<>();
       if (j.getRows() != null && j.getRows().size() > 0) {
           List<Stock> ss = j.getRows();
           for (Stock s : ss
           ) {
               if (null == dataMap.get(s.getArticleno() + "||" + s.getSize())) {
                   datass.add(s);
                   dataMap.put(s.getArticleno() + "||" + s.getSize(), s);
               }
           }
       }
       return datass;
   }

    /**
     * 读取同步库存商品
     * @return
     */
    private List<Record> getSyncBrands(){
        return  Db.use("old").find("SELECT  id  ,name  from s_syncstock");
    }

    /**
     * 读取上架的 审核通过的没删除的商品
     * @param brandId
     * @return
     */
    private List<Record> getSyncGoods(String brandId){

        return  Db.use("old").find("SELECT artNo,price,id from s_goods where state='1' and del_flag='0' and pass='1' and brand_id='"+brandId+"'");
    }


}

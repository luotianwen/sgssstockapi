package com.yoyound.sync.stock;

import com.alibaba.fastjson.JSON;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.yoyound.sync.stock.test.*;

import java.util.*;

public class SyncStockTask implements Runnable {
    Log log = Log.getLog(SyncStockTask.class);

    @Override
    public void run() {
        //读取同步品牌
        log.error("开始同步库存");
          List<Record> bands = getSyncBrands();
        for (Record b : bands
        ) {
            List<Record> goods = getSyncGoods(b.getStr("id"));
            log.warn(b.getStr("name") + "有" + goods.size() + "商品");
            for (Record g : goods
            ) {
            /* Record g=new Record();
            g.set("artNo","CX5009");
            g.set("price","299.0");
            g.set("id","3402");
            g.set("name","Adidas阿迪达斯男裤2018夏款运动篮球透气梭织跑步五分裤 CX5009");*/
                try {

                    saveOldSku(g);
                    saveNewSku(g);
                } catch (Exception e) {
                     e.printStackTrace();
                    log.error(g.getStr("artNo")+"出错了" + e.toString());
                }
            }

        }
        log.error("同步数据完成");

    }


    private String saveNewSku(Record go) {
        int num = 0;
        {
            num = 0;
            Record good = Db.use("old").findFirst("SELECT g.id as id, g.num as gid,  g.name,g.price,g.market_price,d.details ,g.logo ,g.artNo,b.num from s_goods g ,s_goods_detail d ,s_brand b where g.state='1' and g.brand_id=b.id and  d.goods_id=g.id and g.id=?", go.getStr("id"));
            if (good == null) {
                return null;
            }
            List<Record> skus = Db.use("old").find("SELECT s.goods_id as id ,s.stock as num ,s.spec1, s.price as price   from s_goods_sku s where s.goods_id=? and s.stock>0", good.getStr("id"));
            Record og = Db.use("new").findFirst("select id from  hjmall_goods where name=?", good.getStr("name"));
            List<Record> alldata = new ArrayList<>();
            for (Record s : skus) {
                //同步规格

                num += s.getInt("num");
                Record data = new Record();
                List<Record> attr_list = new ArrayList<>();
                /*if (null != s.getStr("spec2")) {
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
                    Record attr2 = Db.use("new").findFirst("SELECT id as attr_id,attr_name   from hjmall_attr where attr_name=?", s.getStr("spec2"));
                    if (null == attr2) {
                        attr2 = new Record();
                        Attr a = new Attr();
                        a.setAttrName(s.getStr("spec2"));
                        a.setAttrGroupId(2);
                        a.setIsDelete(0);
                        a.setIsDefault(0);
                        a.save();
                        attr2.set("attr_id", a.getId());
                        attr2.set("attr_name", a.getAttrName());
                    }
                    attr_list.add(attr2);
                } else {*/
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
                // }


                data.set("attr_list", attr_list);
                data.set("num", s.get("num"));
                data.set("price", s.get("price"));
                data.set("no", good.getStr("artNo"));
                data.set("pic", "");
                data.set("share_commission_first", "");
                data.set("share_commission_second", "");
                data.set("share_commission_third", "");
                data.set("member1", "");
                data.set("member2", "");
                data.set("member3", "");
                data.set("member4", "");
                alldata.add(data);
            }
            if (null == og) {
                //同步商品
                String logo = good.getStr("logo");
                GoodsPic pic = new GoodsPic();
                pic.setGoodsId(good.getInt("gid"));

                if (logo.indexOf("http") < 0) {
                    pic.setPicUrl("http://image.yoyound.com/" + logo);

                } else {
                    pic.setPicUrl(good.getStr("logo"));
                }

                pic.setIsDelete(0);
                pic.save();
                ////品牌
                GoodsCat cat = new GoodsCat();
                cat.setGoodsId(good.getInt("gid"));
                cat.setStoreId(2);
                cat.setAddtime(new Long(System.currentTimeMillis() / 1000).intValue());
                cat.setIsDelete(0);
                cat.setCatId(good.getInt("num"));
                cat.save();

                //分类
                List<Record> cs = Db.use("old").find("SELECT c.num  from s_goods_category s , s_category c where  c.id=s.category_id and  s.goods_id=?", good.getStr("id"));
                for (Record rs : cs
                ) {
                    GoodsCat c = new GoodsCat();
                    c.setGoodsId(good.getInt("gid"));
                    c.setStoreId(2);
                    c.setAddtime((int) (System.currentTimeMillis() / 1000));
                    c.setIsDelete(0);
                    c.setCatId(rs.getInt("num"));
                    c.save();
                }
                //Db.use("new").delete("delete from hjmall_goods  where id=?",good.getInt("gid"));
                // Record og=Db.use("new").findFirst("select id from  hjmall_goods where id=?",good.getInt("gid"));
                /* if(null==og) {*/
                Goods g = new Goods();
                g.setAddtime((int) (System.currentTimeMillis() / 1000));
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
                g.setSort(good.getInt("gid"));
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
                log.warn("goods" + good.getInt("gid")+ "同步商品完成" + good.getStr("artNo") );
            } else {
                if(num>0){
                log.warn("goods" + og.getInt("id") + "同步库存完成"+ good.getStr("artNo") );
                //同步库存
                Db.use("new").update("update hjmall_goods set attr=?,status=1,price=? where id=? ", JsonKit.toJson(alldata), good.getFloat("price"), og.getInt("id"));
                }
            }


        }
        return null;
    }

    private String  saveOldSku(Record g) throws Exception {
        List<Stock> stocks = getSyncStock(g.getStr("artNo"));
        if (stocks.size() == 0) {
            log.error(g.getStr("artNo")+"下架商品" + g.getStr("name"));
            Db.use("old").update("update s_goods  set state=0,update_date=now() where id=?", g.getStr("id"));
            Db.use("old").update("update s_goods_sku set stock=0 where goods_id=?", g.getStr("id"));
            Db.use("new").update("update hjmall_goods set status=0 where name=?",g.getStr("name") );
           return null;
        }
        //设置尺码和库存
        Db.use("old").update("update s_goods  set supplier_id='5e455bfcce0446159af263118fbb2533',spec1='尺码' , spec2=null where  id=?", g.getStr("id"));
        Db.use("old").update("update s_goods_sku  set stock=0  where  goods_id=?", g.getStr("id"));


        Record allsku = Db.use("old").findFirst("select * from s_goods_sku where goods_id=?   limit 1 ", g.getStr("id"));
       //获取已经有的库存价格数据
        Double discount = null, price = null, settlementPrice = null,market_price=null,settlement_discount=null;
        if (null != allsku) {
            discount = allsku.getDouble("discount");
            price = allsku.getDouble("price");
            settlementPrice = allsku.getDouble("settlement_price");
            market_price= allsku.getDouble("market_price");
           // settlement_discount=allsku.getDouble("settlement_discount");
        }
        if (null == discount||discount==0d) {
            if(8>=stocks.get(0).getDiscount()&&6<=stocks.get(0).getDiscount()){
                discount = stocks.get(0).getDiscount() + 2.0;
            }
            else {
                discount = stocks.get(0).getDiscount() + 2.5;
            }
        }
        if(discount>9.9){
            discount=10d;
        }
        if(null==market_price||market_price==0d){
            market_price=stocks.get(0).getMarketprice();
        }
        if (null == price||price==0d) {
            price = (stocks.get(0).getMarketprice() * discount)/10;
        }
        if(null==settlement_discount||settlement_discount==0d){
            settlement_discount=stocks.get(0).getDiscount()/10;

        }
        if (null == settlementPrice||settlementPrice==0d) {
            settlementPrice = (stocks.get(0).getMarketprice() * stocks.get(0).getDiscount())/10;
        }

        for (Stock s : stocks
        ) {
                Record sku = Db.use("old").findFirst("select * from s_goods_sku where goods_id=? and  (spec1 =? or spec2=?) ", g.getStr("id"), s.getSize(), s.getSize());
                //比对已有的sku尺码
                    Sku su = new Sku();
                    su.setGoodsId(g.getStr("id"));
                    su.setStock(s.getInnernum());
                    su.setSpec1(s.getSize());
                    su.setDiscount(discount/10);
                    su.setMarketPrice(market_price);
                    su.setSettlementDiscount(settlement_discount);
                    su.setPrice(price);
                    su.setSettlementPrice(settlementPrice);
                    su.setProfit(su.getPrice() - su.getSettlementPrice());
                if (null == sku) {
                    su.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                    su.save();
                    log.warn(g.getStr("id") + "没有sku商品增加了" + su.getId());
                } else {
                    su.setId(sku.getStr("id"));
                    su.update();
                    //修改库存
                    //Db.use("old").update("update s_goods_sku set stock=?,spec1=?,spec2=null where id=?", s.getInnernum(), s.getSize(), sku.getStr("id"));
                    log.warn(g.getStr("id") + "修改库存" + sku.getStr("id"));

                }
        }
        //删除库存为0的
        Db.use("old").delete("delete from s_goods_sku where goods_id=? and stock=0 ", g.getStr("id"));
        //审核通过
        Db.use("old").update("update s_goods  set pass=1,state=1,update_date=now(),price=? where id=?",price, g.getStr("id"));
        return null;
    }


    private List<Stock> getSyncStock(String articleno) throws Exception {
        Map map = new HashMap();
        map.put("sign", Cont.SIGN);
        map.put("articleno", articleno.trim());
        //配货率
        map.put("pickingRate", "70");
        //最大折扣
        map.put("maxDiscount", "8");
        //是否支持退货
        map.put("return_type", "1");
         String str = Cont.post(Cont.GROUPSTOCK, map);
         Map map2 = new HashMap();
        map2.put("articleno", articleno.trim());
        //String str=Cont.post("http://open.api.yoyound.com/openapi/getInventoryList?sign=e509e958b76ae2667326680c5679e1",map2);
        // String str = "{\"total\":28,\"page\":\"1\",\"rows\":[{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"L\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.2\",\"size\":\"L\",\"innerNum\":\"240\",\"wareHouseName\":\"天马总仓1仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"M\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.2\",\"size\":\"M\",\"innerNum\":\"226\",\"wareHouseName\":\"天马总仓1仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"S\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.2\",\"size\":\"S\",\"innerNum\":\"195\",\"wareHouseName\":\"天马总仓1仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"XL\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.2\",\"size\":\"XL\",\"innerNum\":\"118\",\"wareHouseName\":\"天马总仓1仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"M\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.3\",\"size\":\"M\",\"innerNum\":\"135\",\"wareHouseName\":\"山东狼爪C仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"S\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.3\",\"size\":\"S\",\"innerNum\":\"234\",\"wareHouseName\":\"山东狼爪C仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"L\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"4.7\",\"size\":\"L\",\"innerNum\":\"30\",\"wareHouseName\":\"长春户外仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"M\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"4.7\",\"size\":\"M\",\"innerNum\":\"60\",\"wareHouseName\":\"长春户外仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"S\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"4.7\",\"size\":\"S\",\"innerNum\":\"30\",\"wareHouseName\":\"长春户外仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"L\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.4\",\"size\":\"L\",\"innerNum\":\"66\",\"wareHouseName\":\"山东狼爪\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"M\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.4\",\"size\":\"M\",\"innerNum\":\"28\",\"wareHouseName\":\"山东狼爪\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"S\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.4\",\"size\":\"S\",\"innerNum\":\"11\",\"wareHouseName\":\"山东狼爪\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"XL\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.4\",\"size\":\"XL\",\"innerNum\":\"31\",\"wareHouseName\":\"山东狼爪\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"L\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.3\",\"size\":\"L\",\"innerNum\":\"43\",\"wareHouseName\":\"山东乐斯菲斯2仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"M\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.3\",\"size\":\"M\",\"innerNum\":\"54\",\"wareHouseName\":\"山东乐斯菲斯2仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"S\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.3\",\"size\":\"S\",\"innerNum\":\"40\",\"wareHouseName\":\"山东乐斯菲斯2仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"XL\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.3\",\"size\":\"XL\",\"innerNum\":\"19\",\"wareHouseName\":\"山东乐斯菲斯2仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"L\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.3\",\"size\":\"L\",\"innerNum\":\"19\",\"wareHouseName\":\"山东狼爪E仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"M\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.3\",\"size\":\"M\",\"innerNum\":\"16\",\"wareHouseName\":\"山东狼爪E仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"S\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.3\",\"size\":\"S\",\"innerNum\":\"10\",\"wareHouseName\":\"山东狼爪E仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"L\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.4\",\"size\":\"L\",\"innerNum\":\"6\",\"wareHouseName\":\"山东狼爪D仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"M\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.4\",\"size\":\"M\",\"innerNum\":\"6\",\"wareHouseName\":\"山东狼爪D仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"S\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.4\",\"size\":\"S\",\"innerNum\":\"5\",\"wareHouseName\":\"山东狼爪D仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"XL\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.4\",\"size\":\"XL\",\"innerNum\":\"8\",\"wareHouseName\":\"山东狼爪D仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"XL\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.3\",\"size\":\"XL\",\"innerNum\":\"9\",\"wareHouseName\":\"山东狼爪E仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"L\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.4\",\"size\":\"L\",\"innerNum\":\"93\",\"wareHouseName\":\"山东户外2仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"M\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.4\",\"size\":\"M\",\"innerNum\":\"40\",\"wareHouseName\":\"山东户外2仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"},{\"sex\":\"男\",\"cate\":\"冲锋衣\",\"marketprice\":1299.0,\"ukSize\":\"XL\",\"warehouse_goods_no\":\"5118012-6000\",\"brandName\":\"狼爪\",\"discount\":\"5.4\",\"size\":\"XL\",\"innerNum\":\"107\",\"wareHouseName\":\"山东户外2仓\",\"division\":\"服\",\"articleno\":\"5118012-6000\",\"quarter\":\"19Q1\"}]}";
        log.warn("天马仓库数据" + str);
        BackData j = JSON.parseObject(str, BackData.class);
        if(StrKit.notBlank(j.error_code)){
            log.error(j.error_info);
            throw new Exception(j.error_info);
        }
        Map<String, Stock> dataMap = new HashMap();
        List<Stock> datass = new ArrayList<>();
        if (j.getRows() != null && j.getRows().size() > 0) {
            List<Stock> ss = j.getRows();
            double marketprice=ss.get(0).getMarketprice();
            double discount=ss.get(0).getDiscount();

            for (Stock s : ss
            ) {
                s.setMarketprice(marketprice);
                s.setDiscount(discount);
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
     *
     * @return
     */
    private List<Record> getSyncBrands() {
        return Db.use("old").find("SELECT  id  ,name  from s_syncstock");
    }

    /**
     * 读取上架的 审核通过的没删除的商品
     *
     * @param brandId
     * @return
     */
    private List<Record> getSyncGoods(String brandId) {
        return Db.use("old").find("SELECT artNo,price,id,name from s_goods where state='1' and del_flag='0'   and brand_id='" + brandId + "' order by update_date asc ");
    }


}

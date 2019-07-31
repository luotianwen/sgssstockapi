package com.yoyound.sync.stock;

import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLDecoder;
import java.util.List;

public class OutDongLiTask implements Runnable {
    Log log = Log.getLog(OutDongLiTask.class);
    @Override
    public void run() {
        {
            System.out.println("开始");
           String path= PropKit.get("filetopath");
            String www="http://image.yoyound.com/";
            List<Record> list= Db.use("old").find("select g.logo ,d.details ,g.num   from s_goods g,s_goods_detail d where d.goods_id=g.id and g.num>25477" );
            for (Record r:list
            ) {
                PageUtil.getFile(www+r.getStr("logo"),path+r.getStr("logo"));
                System.out.println(r.getStr("num")+" "+ 0+ " "+r.getStr("logo"));
                System.out.println(r.getStr("num")+" "+ 1+ " "+r.getStr("logo"));
                System.out.println(r.getStr("num")+" "+ 2+ " "+r.getStr("logo"));
                Document containerDoc = Jsoup.parse(r.getStr("details"));
                Elements e=containerDoc.select("img");
                for(Element ee:e){
                    String name=ee.attr("src").replaceAll(www,"");

                    PageUtil.getFile( ee.attr("src"), URLDecoder.decode(path+name));
                    System.out.println(r.getStr("num")+" "+ 3+ " "+URLDecoder.decode(name));
                }

            }
            System.out.println("结束");
        }
    }
}

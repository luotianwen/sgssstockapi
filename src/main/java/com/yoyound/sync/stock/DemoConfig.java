package com.yoyound.sync.stock;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.yoyound.sync.stock.test.Attr;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * API 引导式配置
 */
public class DemoConfig extends JFinalConfig {
	
	static Prop p;
	
	/**
	 * 启动入口，运行此 main 方法可以启动项目，此 main 方法可以放置在任意的 Class 类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		 UndertowServer.start(DemoConfig.class);


	}

    @Override
    public void onStart() {
        super.onStart();
        // new Phpxcx().getOldGoods();
		//  new Phpxcx().insertOldGoods();
    }

    /**
	 * PropKit.useFirstFound(...) 使用参数中从左到右最先被找到的配置文件
	 * 从左到右依次去找配置，找到则立即加载并立即返回，后续配置将被忽略
	 */
	static void loadConfig() {
		if (p == null) {
			p = PropKit.useFirstFound("demo-config-pro.txt", "demo-config-dev.txt");
		}
	}
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		loadConfig();
		
		me.setDevMode(p.getBoolean("devMode", false));
		
		/**
		 * 支持 Controller、Interceptor、Validator 之中使用 @Inject 注入业务层，并且自动实现 AOP
		 * 注入动作支持任意深度并自动处理循环注入
		 */
		me.setInjectDependency(true);
		
		// 配置对超类中的属性进行注入
		me.setInjectSuperClass(true);
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {

	}
	
	public void configEngine(Engine me) {

	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置 druid 数据库连接池插件
		DruidPlugin oldshoplugin = new DruidPlugin(p.get("old.jdbcUrl"), p.get("old.user"), p.get("old.password").trim());
		me.add(oldshoplugin);
		ActiveRecordPlugin oldMysql = new ActiveRecordPlugin("old", oldshoplugin);
		_MappingKit.oldmapping(oldMysql);
		me.add(oldMysql);


		DruidPlugin newshoplugin = new DruidPlugin(p.get("new.jdbcUrl"), p.get("new.user"), p.get("new.password").trim());
		me.add(newshoplugin);
		ActiveRecordPlugin newMysql = new ActiveRecordPlugin("new", newshoplugin);
		 _MappingKit.newmapping(newMysql);
		 me.add(newMysql);
		 Cron4jPlugin cp = new Cron4jPlugin("config.txt", "cron4j");
		 //cp.addTask("* 1 * * *", new SyncStockTask());
		 me.add(cp);
		// me.add(arp);
	}
	
	public static DruidPlugin createDruidPlugin() {
		loadConfig();
		
		return new DruidPlugin(p.get("new.jdbcUrl"), p.get("new.user"), p.get("new.password").trim());
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		
	}
}

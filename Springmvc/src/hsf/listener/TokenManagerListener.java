package hsf.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import hsf.common.constant.Common;
import hsf.weixin.popular.support.TokenManager;

public class TokenManagerListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//WEB容器 初始化时调用
		TokenManager.setDaemon(false);
		TokenManager.init(Common.WX_APPID,Common.WX_APPSECRET);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//WEB容器  关闭时调用
		TokenManager.destroyed();
	}
}

package kr.or.kosta.shoppingmall.common.service;

import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

import kr.or.kosta.shoppingmall.user.service.UserService;
import kr.or.kosta.shoppingmall.user.service.UserServiceImpl;

public class ServiceFactory {

	private Hashtable<String, Object> services;

	public ServiceFactory(String serviceMapperLocation) {
		services = new Hashtable<String, Object>();

		Properties prop = new Properties();
		FileInputStream fis = null;
		System.out.println("--- 서비스 생성 목록 ---");
		try {
			fis = new FileInputStream(serviceMapperLocation);
			prop.load(fis);
			Iterator keyIter = prop.keySet().iterator();
			while (keyIter.hasNext()) {
				String serviceName = (String) keyIter.next();
				String serviceClassName = prop.getProperty(serviceName);
				Object serviceObject = Class.forName(serviceClassName).newInstance();
				services.put(serviceClassName, serviceObject);
				System.out.println(serviceClassName + " = " + serviceObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getService(String serviceName) {
		return services.get(serviceName);
	}

	public Object getService(Class cls) {
		return getService(cls.getName());
	}
	
	public static void main(String[] args) throws Exception {
		String mapperLocation = "C:/KOSTA187/workspace/Model2Study/WebContent/WEB-INF/service-mapper.properties";
		ServiceFactory factory = new ServiceFactory(mapperLocation);
		UserService userService = (UserService) factory.getService(UserServiceImpl.class);
		
		userService.list();
	}
}

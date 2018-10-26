package kr.or.kosta.shoppingmall.common.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sun.xml.internal.ws.util.StringUtils;

import kr.or.kosta.shoppingmall.common.dao.DaoFactory;

public class XMLObjectFactory extends DaoFactory {

	private Hashtable<String, Object> serviceList;
	private Hashtable<String, Object> daoList;

	public XMLObjectFactory(String objectMapperLocation) {
		serviceList = new Hashtable<String, Object>();
		daoList = new Hashtable<String, Object>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);

		DocumentBuilder parser = null;
		try {
			parser = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.out.println("[디버깅]: XML DOM 파서 로딩 오류" + e);
		}

		Document document = null;
		try {
			document = parser.parse(objectMapperLocation);
		} catch (Exception e) {
			System.out.println("[디버깅]: 파서 document 로딩 오류" + e);
		}

		Element beans = document.getDocumentElement();
		NodeList bean = beans.getChildNodes();

		// beans 아래의 모든 bean 객체 생성하기
		Map<String, String> beanList = new HashMap<>();
		System.out.println("--- 팩토리 생성 목록 ---");
		for (int i = 0; i < bean.getLength(); i++) {
			Element beanElement = (Element) bean.item(i);
			String className = null;
			String name = null;
			String type = null;
			className = beanElement.getAttribute("class");
			name = beanElement.getAttribute("name");
			type = beanElement.getAttribute("type");
			try {
				Object objectClass = null;
				if (type.equals("dao")) {
					objectClass = Class.forName(className).newInstance();
					addDataSource(objectClass);
					daoList.put(className, objectClass);
					System.out.println(className + "=" + objectClass);
				} else if (type.equals("service")) {
					objectClass = Class.forName(className).newInstance();
					serviceList.put(className, objectClass);
					System.out.println(className + "=" + objectClass);
				}
				beanList.put(name, className);

				NodeList properties = beanElement.getChildNodes();
				for (int j = 0; j < properties.getLength(); j++) {
					Element property = (Element) properties.item(j);
					String refClass = property.getAttribute("ref");
					String propertyName = property.getAttribute("name");
					String refClassName = null;
					if (refClass != null) {
						Set<String> beanNames = beanList.keySet();
						for (String beanName : beanNames) {
							if (beanName.equals(refClass)) {
								refClassName = beanList.get(beanName);
								break;
							}
						}
						String methodName = "set" + StringUtils.capitalize(propertyName);
						String interfaceName = getDao(refClassName).getClass().getInterfaces()[0].getName();
						addDao(objectClass, interfaceName, methodName, getDao(refClassName));
					} else {
						// ref 가 아닐때 구현해야 하는 내용
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Object getService(String serviceName) {
		return serviceList.get(serviceName);
	}

	public Object getService(Class cls) {
		return getService(cls.getName());
	}

	public Object getDao(String daoName) {
		return daoList.get(daoName);
	}

	public Object getDao(Class cls) {
		return getDao(cls.getName());
	}

	private void addDataSource(Object dao) {
		Class cls = dao.getClass();
		// 동적 메소드호출
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			method.invoke(dao, getDataSource());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addDao(Object service, String daoName, String methodName, Object dao) {
		Class cls = service.getClass();
		Method method = null;
		try {
			method = cls.getMethod(methodName, Class.forName(daoName));
			method.invoke(service, dao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

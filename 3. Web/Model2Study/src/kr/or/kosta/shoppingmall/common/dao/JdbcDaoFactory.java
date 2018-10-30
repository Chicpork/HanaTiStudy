package kr.or.kosta.shoppingmall.common.dao;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

import javax.sql.DataSource;

public class JdbcDaoFactory extends DaoFactory {

	private Hashtable<String, Object> daos;

	public JdbcDaoFactory(String daoMapperLocation) {
		daos = new Hashtable<String, Object>();

		Properties prop = new Properties();
		FileInputStream fis = null;
		System.out.println("--- Dao 생성 목록 ---");
		try {
			fis = new FileInputStream(daoMapperLocation);
			prop.load(fis);
			Iterator keyIter = prop.keySet().iterator();
			while (keyIter.hasNext()) {
				String daoName = (String) keyIter.next();
				String daoClassName = prop.getProperty(daoName);
				Object daoObject = Class.forName(daoClassName).newInstance();
				addDataSource(daoObject);
				daos.put(daoClassName, daoObject);
				System.out.println(daoClassName + " = " + daoObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	@Override
	public Object getDao(String daoName) {
		return daos.get(daoName);
	}

	@Override
	public Object getDao(Class cls) {
		return getDao(cls.getName());
	}
}

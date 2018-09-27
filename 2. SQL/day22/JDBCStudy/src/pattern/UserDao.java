package pattern;

import java.sql.Connection;
import java.util.List;

/**
 * Dao 패턴 적용을 위한 인터페이스 선언
 * 
 * @author 정지원
 *
 */
public interface UserDao {
	public abstract void create(User user) throws Exception;

	public abstract User read(String id) throws Exception;

	public abstract void update(User user) throws Exception;

	public abstract void delete(String id) throws Exception;

	public abstract List<User> listAll() throws Exception;

	public abstract User certify(String id,String passwd) throws Exception;
	
	public abstract Connection getConnection() throws Exception;
}

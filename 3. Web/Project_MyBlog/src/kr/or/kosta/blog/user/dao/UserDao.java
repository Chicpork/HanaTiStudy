package kr.or.kosta.blog.user.dao;

import java.util.List;
import java.util.Map;

import kr.or.kosta.blog.user.domain.User;

/**
 * 블로그 사용자를 DB와 연동해 CRUD 하기 위한 Dao 클래스
 * @author 정지원
 *
 */
public interface UserDao {
	
	/**
	 * 신규 유저 추가 메소드
	 * @param user
	 * @throws Exception
	 */
	public void create(User user) throws Exception;
	
	/**
	 * 아이디에 해당하는 유저 반환
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User read(String id) throws Exception;
	
	/**
	 * 존재하는 이메일인지 반환해주는 메소드
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public boolean isExistEmail(String email) throws Exception;
	
	/**
	 * 유저 정보를 업데이트 하기 위한 메소드
	 * @param user
	 * @throws Exception
	 */
	public void update(User user) throws Exception;
	
	/**
	 * 유저를 삭제하기 위한 메소드
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
	/**
	 * 전체 유저 리스트를 반환하는 메소드
	 * @return
	 * @throws Exception
	 */
	public List<User> listAll() throws Exception;
	
	/**
	 * 유저 로그인을 위해 필요한 검증 메소드
	 * @param id
	 * @param passwd
	 * @return
	 * @throws Exception
	 */
	public User certify(String id, String passwd) throws Exception;
	
}

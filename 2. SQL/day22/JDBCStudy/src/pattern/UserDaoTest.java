package pattern;

import java.sql.SQLException;

public class UserDaoTest {

	public static void main(String[] args) {
		UserDao dao = new JdbcUserDao();
		User user = new User();
		User user2 = null;
		user.setId("chicpork");
		user.setName("정지원");
		user.setPasswd("1234");
		user.setEmail("jjw1022@daum.net");
		try {
//			dao.create(user);
//			System.out.println("회원가입 완료!!");
			user2 = dao.read("chicpork2");
			if (user2 == null) {
				System.out.println("해당하는 유저 없음");
			} else {
				System.out.println(user2);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println();
		}

	}

}

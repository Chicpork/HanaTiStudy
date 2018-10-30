package kr.or.kosta.shoppingmall.user.service;

import java.util.List;

import kr.or.kosta.shoppingmall.user.domain.User;

/**
 * 고객의 요구사항을 반영한 도메인(개발하고자 하는 업무 영역)별 비즈니스 메소드 선언 복잡한 트랜잭션 처리, 예외 처리 등
 * 
 * @author 정지원
 *
 */
public interface UserService {

	/** 회원 검색 */
	public User search(String id) throws Exception;

	/** 회원 목록 */
	public List<User> list() throws Exception;

}

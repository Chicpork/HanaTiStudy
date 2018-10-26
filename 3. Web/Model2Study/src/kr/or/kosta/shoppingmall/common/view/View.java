package kr.or.kosta.shoppingmall.common.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 모든 뷰에 대한 실행메소드 규약 선언
 * @author 김기정
 */
public interface View {
	
	/** 실행 규약 메소드 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
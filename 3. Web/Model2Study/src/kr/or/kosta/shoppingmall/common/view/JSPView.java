package kr.or.kosta.shoppingmall.common.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSPView implements View {
	
	private String path;
	
	public JSPView(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		if (path == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		if(path.startsWith("redirect")){// redirect
			String[] tokens = path.split(":", 2);
			response.sendRedirect(request.getContextPath()+tokens[1]);
		}else{//forward
			dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}
	}
}

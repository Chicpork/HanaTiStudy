package kr.or.kosta.servlet;

public class IndexHTML {
	private String loginBF;
	private String loginAF;
	private String loginForm;
	
	private IndexHTML() {
		loginBF = 
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"\r\n" + 
				"<head>\r\n" + 
				"  <link rel=\"stylesheet\" type=\"text/css\" href=\"css/basic.css\">\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body>\r\n" + 
				"\r\n" + 
				"  <div class=\"header\">\r\n" + 
				"    <h1>My Website</h1>\r\n" + 
				"    <p>Resize the browser window to see the effect.</p>\r\n" + 
				"  </div>\r\n" + 
				"\r\n" + 
				"  <div class=\"topnav\">\r\n" + 
				"    <a href=\"#\">Link</a>\r\n" + 
				"    <a href=\"#\">Link</a>\r\n" + 
				"    <a href=\"#\">Link</a>\r\n" + 
				"    <a href=\"#\" style=\"float:right\">Link</a>\r\n" + 
				"  </div>\r\n" + 
				"\r\n" + 
				"  <div class=\"row\">\r\n" + 
				"    <div class=\"leftcolumn\">\r\n" + 
				"      <div class=\"card\">\r\n" + 
				"        <h2>TITLE HEADING</h2>\r\n" + 
				"        <h5>Title description, Dec 7, 2017</h5>\r\n" + 
				"        <div class=\"fakeimg\" style=\"height:200px;\">Image</div>\r\n" + 
				"        <p>Some text..</p>\r\n" + 
				"        <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod\r\n" + 
				"          tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation\r\n" + 
				"          ullamco.</p>\r\n" + 
				"      </div>\r\n" + 
				"      <div class=\"card\">\r\n" + 
				"        <h2>TITLE HEADING</h2>\r\n" + 
				"        <h5>Title description, Sep 2, 2017</h5>\r\n" + 
				"        <div class=\"fakeimg\" style=\"height:200px;\">Image</div>\r\n" + 
				"        <p>Some text..</p>\r\n" + 
				"        <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod\r\n" + 
				"          tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation\r\n" + 
				"          ullamco.</p>\r\n" + 
				"      </div>\r\n" + 
				"    </div>\r\n" + 
				"\r\n" + 
				"    <div class=\"rightcolumn\">\r\n" + 
				"      <div class=\"card\">\r\n";
		loginAF =
				"\r\n" + 
				"      </div>\r\n" + 
				"\r\n" + 
				"      <div class=\"card\">\r\n" + 
				"        <h3>Popular Post</h3>\r\n" + 
				"        <div class=\"fakeimg\">\r\n" + 
				"          <p>Image</p>\r\n" + 
				"        </div>\r\n" + 
				"        <div class=\"fakeimg\">\r\n" + 
				"          <p>Image</p>\r\n" + 
				"        </div>\r\n" + 
				"        <div class=\"fakeimg\">\r\n" + 
				"          <p>Image</p>\r\n" + 
				"        </div>\r\n" + 
				"      </div>\r\n" + 
				"      <div class=\"card\">\r\n" + 
				"        <h3>Follow Me</h3>\r\n" + 
				"        <p>Some text..</p>\r\n" + 
				"      </div>\r\n" + 
				"    </div>\r\n" + 
				"  </div>\r\n" + 
				"\r\n" + 
				"  <div class=\"footer\">\r\n" + 
				"    <h2>Footer</h2>\r\n" + 
				"  </div>\r\n" + 
				"\r\n" + 
				"</body>\r\n" + 
				"\r\n" + 
				"</html>";
		loginForm = "<div>\r\n" + 
					"	<form action=\"login.do\" method=\"post\">\r\n" + 
					"		<input type=\"text\" id=\"userid\" name=\"userid\" placeholder=\"Identifier...\">\r\n" + 
					"		<input type=\"password\" id=\"userpw\" name=\"userpw\" placeholder=\"Password...\">\r\n" + 
					"		<input type=\"submit\" value=\"Login\">\r\n" + 
					"	</form>\r\n" + 
					"</div>\r\n";
	}
	
	private static class Singleton {
		private static final IndexHTML instance = new IndexHTML();
	}
	
	public static IndexHTML getInstance() {
		return Singleton.instance;
	}

	private String setLogoutForm(String id, String count) {
		String logoutForm = 
				"<div>\r\n" + 
				"	<form action=\"logout.do\" method=\"post\">\r\n" + 
				"		<div>"+id+"님이 입장하셨습니다.</div>\r\n" + 
				"		<div>"+count+"번 입장.</div>\r\n" + 
				"		<input type=\"submit\" value=\"Logout\">\r\n" + 
				"	</form>\r\n" + 
				"</div>";
		return logoutForm;
	}
	
	public String loginHTML() {
		String html = null;
		html = loginBF + loginForm + loginAF;
		return html;
	}

	public String logoutHTML(String id, String count) {
		String html = null;
		html = loginBF + setLogoutForm(id, count) + loginAF;
		return html;
	}
}

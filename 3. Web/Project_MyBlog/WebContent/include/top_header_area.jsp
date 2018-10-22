<%@ page contentType="text/html; charset=utf-8"%>

<div class="top_header_area">
	<div class="container">
		<div class="row">

			<!--  Top Social bar start -->
			<div class="top_home_bar">
				<a href="/">Home</a>
			</div>

			<!--  Login Register Area -->

			<div
				class="signup-search-area d-flex align-items-center justify-content-end">
				<%@ include file="/user/getUserCookie.jsp"%>
				<%
					if (userId == null) {
						String saveId = null;
						if (cookies != null) {
							for (Cookie cookie : cookies) {
								if (cookie.getName().equals("saveId")) {
									saveId = cookie.getValue();
									break;
								}
							}
						}
						if (saveId == null) {
				%>
				<div class="login-form">
					<form action="/user/login_action.jsp" method="post">
						<input type="text" name="id" id="userId" placeholder="Your id">
						<input type="password" name="passwd" id="userPw"
							placeholder="Password"> <input type="submit"
							id="login-submit" value="Login"> <input type="checkbox"
							value="saveId" id="saveId"> <label>Save ID</label>
					</form>
				</div>
				<div class="login_register_area d-flex">
					<div class="login">
						<a href="#">Sign in</a>
					</div>
					<div class="register">
						<a href="/user/regist.jsp">Sign up</a>
					</div>
					<%
						} else {
					%>
					<div class="login-form">
						<form action="/user/login_action.jsp" method="post">
							<input type="text" name="id" id="userId" placeholder="Your id"
								value="<%=saveId%>"> <input type="password"
								name="passwd" id="userPw" placeholder="Password"> <input
								type="submit" id="login-submit" value="Login"> <input
								type="checkbox" value="saveId" id="saveId" checked> <label>Save
								ID</label>
						</form>
					</div>
					<div class="login_register_area d-flex">
						<div class="login">
							<a href="#">Sign in</a>
						</div>
						<div class="register">
							<a href="/user/regist.jsp">Sign up</a>
						</div>
						<%
							}
							} else {
						%>
						<label> <%=userId%>님이 로그인 하셨습니다.
						</label>
						<div class="login_register_area d-flex">
							<div class="logout">
								<a href="/user/logout_action.jsp">Log out</a>
							</div>
							<%
								}
							%>
						</div>
						<!-- Search Button Area -->
						<div class="search_button">
							<a class="searchBtn" href="#"><i class="fa fa-search"
								aria-hidden="true"></i></a>
						</div>
						<!-- Search Form -->
						<div class="search-hidden-form">
							<form action="https://www.google.co.kr/search" method="get">
								<input type="search" name="q" class="search-anything"
									placeholder="Search Anything to Google"> <input
									type="submit" value="" class="d-none"> <span
									class="searchBtn"><i class="fa fa-times"
									aria-hidden="true"></i></span>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

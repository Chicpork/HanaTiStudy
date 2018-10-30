<%@ page contentType="text/html; charset=utf-8"%>
<nav class="navbar navbar-expand-lg">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#yummyfood-nav"
    aria-controls="yummyfood-nav" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars"
      aria-hidden="true"></i> Menu</button>
  <!-- Menu Area Start -->
  <div class="collapse navbar-collapse justify-content-center" id="yummyfood-nav">
    <ul class="navbar-nav" id="yummy-nav">
      <li class="nav-item" id="home">
        <a class="nav-link" href="/">Home<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item" id="freeboard">
        <a class="nav-link" href="/board/freeboard.jsp">Free Board</a>
      </li>
      <li class="nav-item" id="guestbook">
        <a class="nav-link" href="/guest/guestbook.jsp">Guest Book</a>
      </li>
      <li class="nav-item" id="about">
        <a class="nav-link" href="/about_blog/about.jsp" id="about">About</a>
      </li>
    </ul>
  </div>
</nav>
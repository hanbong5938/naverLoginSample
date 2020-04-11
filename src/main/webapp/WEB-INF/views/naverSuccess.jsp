
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="ko">
<head>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<style type="text/css">
html, div, body, h3 {
	margin: 0;
	padding: 0;
}

h3 {
	display: inline-block;
	padding: 0.6em;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		var name = ${result}.response.name;
		var email = ${result}.response.email;
		$("#name").html("환영합니다. "+name+"님");
		$("#email").html(email);
	  });
</script>

</head>
<body>

	<div
		style="background-color: #15a181; width: 100%; height: 50px; text-align: center; color: white;">
		<h3>Naver_Login Success</h3>
	</div>
	<br>
	<h2 style="text-align: center" id="name"></h2>
	<h4 style="text-align: center" id="email"></h4>
  <div class="pad">
    <hr />
    <div id="resultCode" style="text-align:center"></div>
    <div id="message" style="text-align:center"></div>
    <div id="nickname" style="text-align:center"></div>
    <div id="image" style="text-align:center"></div>
    <div id="age" style="text-align:center"></div>
    <div id="gender" style="text-align:center"></div>
    <div id="id" style="text-align:center"></div>
    <div id="birthday" style="text-align:center"></div>
 <a href="https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=DEi3y4ZUBxePnhzWUCAq&client_secret=RbcHAWmgBU&access_token=${token }&service_provider=NAVER">로그아웃</a> 
 <!--   <a href="https://nid.naver.com/nidlogin.logout">로그아웃</a> 네이버 로그아웃으로 이동하게 되면 네이버로 이동하는 문제가 방생  -->
	<a href="logout">로오오오오그아웃ㄴ</a>
  </div>

  <script>
    $(document).ready(function(){
      var obj = JSON.parse('${result}');

      $("#resultCode").text("결과코드 : " + obj.resultcode);
      $("#message").text("결과메시지 : " + obj.message);
      $("#age").text("나이 : " + obj.response.age);
      $("#name").text("이름 : " + obj.response.name);
      $("#birthday").text("생일 : " + obj.response.birthday);
      $("#enc_id").text("무슨 아이디 : " + obj.response.enc_id);
      $("#gender").text("성별 : " + obj.response.gender);
      $("#id").text("아이디 : " + obj.response.id);
      $("#nickname").text("닉네임 : " + obj.response.nickname);
      $("#image").children("img").attr("src", obj.response.profile_image);
    });
  </script>
</body>
</html>

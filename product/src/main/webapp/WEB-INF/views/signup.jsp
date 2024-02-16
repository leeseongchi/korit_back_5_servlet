<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 내장 객체
	
	// 저장소
	
	// ServletContext
	// 서블릿의 심장 - 전역저장소이고 서블릿의 설정이 다 들어있다.
	// application 객체 저장소이다.
	
	// application 객체 저장소
	// 생명주기 : 서버가 켜지고 꺼질 때까지 저장 공간이 살아있다.
	
	// request 객체 저장소
	// 생명주기 : 요청이 들어오고 응답이 될 때까지 저장 공간이 살아있다. 
	
	// session 객체 저장소
	// 생명주기 : 서버가 켜지고 꺼질 때까지 또는 세션 만료 시간 까지 저장 공간이 살아있다.
	
	// value값이 Object타입이라 다운캐스팅을 해줘야 한다.
	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- key값이 같을경우 우선순위 : request - session - application -->
	<h3>${key4}</h3>
	
	<input type="text" placeholder="username">
	<input type="password" placeholder="password">
	<input type="text" placeholder="name">
	<input type="email" placeholder="email">
	
	<button onclick="handleSignupSubmit();">회원가입</button>
	
	<img src="/product/hellotest/hellojava.png">
	
	<script src="/product/static/js/signup.js"></script>
</body>
</html>
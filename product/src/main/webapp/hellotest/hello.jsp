<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	// 스크립트릿
	// List<String> names = List.of("김준일", "김준이", "김준삼");
	
	// 서버 사이드 렌더링(SSR)
	// model1 - html 안에서 스크립트릿과 html을 같이 쓰는 것
	// model2 - 스크립트릿을 위에다가 올리고 html만 쓰는 것
	// mvc(model, view, controller)
	// model => 데이터 		-> DTO
	// view => html(화면)	-> HTML, JSP 
	// controller => model, view를 제어, 요청, 응답 처리 -> Servlet
	
	// WEB-INF - 외부에서 접근 불가 (private으로 서블릿 내부에서만 접근 가능)
	
	String name = "김준일";
	String inputValue = "test";
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello</h1>
	<ul>
		<li><%=name %></li>
	</ul>
	<div>
		<input value="<%=inputValue %>" >
	</div>
</body>
</html>
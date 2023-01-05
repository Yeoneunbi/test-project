<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/CSS/navCSS.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
body {
	background: #926dde;
	font-family: 'Varela Round', sans-serif;
	text-align : center;
	padding-top: 6rem;
}

.memberView-title{
		padding-top : 4rem;
		padding-bottom: 2rem;
}

.memberView-wrrapper{
	min-width: 1000px;
	background: #fff;
	padding: 20px 25px;
	border-radius: 3px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
}
</style>
</head>
<body>
	<!-- navbar -->
	<jsp:include page="topNavbar.jsp"></jsp:include>
	
	<div class="memberView-title text-white"><h2>회원 전용 페이지</h2></div>
			
	<div class="memberView-wrrapper container-lg">
	<p>안녕하세요. ${member.name }(${member.id })님</p>
	<hr>
	<a href="logout.do"><input type="button" value="로그아웃" onclick="logout();"></a>
	<a href="memberUpdateView.jsp?id="${member.id }><input type="button" value="회원정보변경"></a>
	</div>

</body>
</html>
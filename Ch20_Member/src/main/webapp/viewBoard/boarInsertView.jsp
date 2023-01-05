<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="<%=request.getContextPath() %>/CSS/boardTable.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
body {
	color: #566787;
	background: #f7f5f2;
	font-family: 'Roboto', sans-serif;
	text-align: center;
}

.view_content_wrap{
	min-width: 1000px;
	background: #fff;
	padding: 20px 25px;
	border-radius: 3px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
}
</style>
</head>
<body>
	<jsp:include page="../topNavbar.jsp"></jsp:include>

	<form action="<%=request.getContextPath() %>/insertPost.do" method="post">
		<div class="view_content_wrap container-lg">
			<header>
				<h3 class="title">
					<span class="title_subject"><input name="title" class="form-control form-control-lg"
						placeholder="제목을 입력해 주세요."></span>
				</h3>
				<div class="content_writer">
					<span class="id">ID : ${member.id }</span>
					<span><input type="hidden" name="seq"></span>
					<span><input type="hidden" name="id"  value="${member.id }"></span>
				</div>
				<hr>
			</header>
			<div class="content">
				<div class="write_div">
					<div><textarea rows="20" cols="100" class="form-control" name="content"></textarea></div>
				</div>
			</div>
			<hr>
			<div>
				<span><input type="submit" class="btn btn-secondary btn-lg" value="글쓰기"></span>
			</div>
		</div>
	</form>
</body>
</html>
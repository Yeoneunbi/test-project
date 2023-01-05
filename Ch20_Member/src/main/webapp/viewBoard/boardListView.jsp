<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Bootstrap Table with Search Column Feature</title>
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
}

</style>
</head>
<body>
	<!-- navbar -->
	<jsp:include page="../topNavbar.jsp"></jsp:include>
	
	
	<div class="container-lg">
		<div class="table-responsive">
			<div class="table-wrapper">
				<div class="table-title">
					<div class="row">
						<div class="col-sm-6">
							<h2>북작<b>커뮤니티</b></h2>
						</div>
						<div class="col-sm-6">
							<div class="search-box">
								<div class="input-group">
									<input type="text" id="search" class="form-control"
										placeholder="Search"> <span class="input-group-addon"><i
										class="material-icons">&#xE8B6;</i></span>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				
				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th style="width: 60%;">title</th>
							<th style="width: 10%;">id</th>
							<th>날짜</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${boardList }" var="board">
							<tr>
								<td>${board.seq }</td>
								<td align="left"><a
									href="<%=request.getContextPath() %>/getBoard.do?seq=${board.seq }">${board.title }</a>
								</td>
								<td>${board.id }</td>
								<td>${board.regDate }</td>
								<td>${board.cnt }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				
				<div>
					<a href="boarInsertView.jsp"><input type="button" class="btn btn-secondary" value="글쓰기"></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
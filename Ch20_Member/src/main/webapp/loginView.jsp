<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.1.1.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script> 
<title>Insert title here</title>
<style type="text/css">
body {
	color: #fff;
	background: #3598dc;
	font-family: 'Roboto', sans-serif;
	text-align: center;
}

.login-form {
    width: 340px;
    margin: 50px auto;
  	font-size: 15px;
}

.login-form h2  {
	color: #333;
	font-weight: bold;
	margin-top: 0;
}

.login-form form {
    margin-bottom: 15px;
    background: #f7f7f7;
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    padding: 30px;
}
.login-form h2 {
    margin: 0 0 15px;
}
.form-control, .btn {
    min-height: 38px;
    border-radius: 2px;
	margin-bottom: 1rem;
}
.btn {        
    font-size: 15px;
    font-weight: bold;
}
</style>
</head>
<body>

	<div class="login-form">
		<form action="login.do" method="post" id="frm">
				<h2>로그인</h2>
				<hr>
				<div class="form-group">
					<input type="text" name="userid"
						class="form-control input-lg" placeholder="id">
				</div>
				<div class="form-group">
					<input type="password" name="userpw"
						class="form-control input-lg" placeholder="비밀번호">
				</div>
				<div class="form-group" id="hidden-msg">
					<p class="text-danger">${msg}</p>
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-primary btn-block" value="로그인">
					<a href="signUpView.jsp"><input
							type="button" class="btn btn-primary btn-block" value="회원가입"></a>
				</div>
		</form>
	</div>
</body>
</html>
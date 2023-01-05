<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.1.1.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/CSS/signUp.css">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script> 
<title>Insert title here</title>
<style>
body{
	color : #fff;
	background: #3598dc;
	font-family: 'Roboto', sans-serif;
	text-align: center;
}
</style>
</head>
<body>
	<div class="signup-form">
	<form action="signup.do" method="post" id="frm">
		<h2>회원 가입</h2>
		<p>'*'표시 항목은 필수 입력 항목입니다.</p>
		<hr>
		<table cellpadding = "3" class="form-group sign-frm" >
			<tr class="form-group">
				<td>이름</td>
				<td><input type="text" class="form-control" name="name" value="${memberSign.name }"></td>
								<td>*  </td>
			</tr>
			<tr class="form-group">
				<td>아이디</td>
				<td><input type="text" class="form-control" name="userid"  value="${memberSign.id }"></td>
				<td>*  </td>
			</tr>
			<tr>
			<td colspan="3" id="hidden-td"><font id="idChk" class="text-danger"></font></td>
			</tr>
			<tr class="form-group">
				<td>암호</td>
				<td><input type="password" class="form-control" name="userpw" value="${memberSign.password }"></td>
								<td>*  </td>
			</tr>
			<tr class="form-group">
				<td>암호 확인</td>
				<td><input type="password" class="form-control"name="pwChk"></td>
								<td>*  </td>
			</tr>
			<tr class="form-group">
				<td>이메일</td>
				<td><input type="email" class="form-control"name="email" value="${memberSign.email}"></td>
			</tr>
			<tr class="form-group">
				<td>전화번호</td>
				<td><input type="text" class="form-control" name="tel" value="${memberSign.tel }"></td>
			</tr>
			<tr class="form-group">
				<td>등급</td>
				<td><input type="radio" name="role" value="2" checked="checked">일반회원
					<input type="radio" name="role" value="1">관리자</td>
			</tr>
			<tr class="form-group">
				<td><input type="submit" class="btn btn-primary btn-lg" value="확인" id="btn"></td>
				<td><a href="loginView.jsp"><input type="button" class="btn btn-secondary btn-lg" value="취소"></a></td>
			</tr>

		</table>
	</form>
</div>
	<input type="hidden" name="idChk" value="invalid" />

<script type="text/javascript">
$(document).ready(function(){
	$("#hidden-td, #idChk").css("display", "none");
	
	$("#frm").submit(function(){
		if ($('input[name=idChk]').val() === 'invalid') {
		    alert("id가 중복되었거나 중복 체크하지 않았습니다.");
		    $('input[name=idChk]').focus();
			return false;
		}else if($('input[name=userpw]').val() !== $('input[name=pwChk]').val()){
			alert("password가 일치하지 않습니다. 한 번 더 확인해주세요.");
			$('input[name=userpw]').focus();
			return false;
		}else if($('input[name=name]').val()==''){
			alert("이름을 입력하지 않았습니다. 이름을 입력해주세요");
			$('input[name=name]').focus();
			return false;
		}else if($('input[name=userpw]').val()=='' || $('input[name=pwChk]').val()==''){
			alert("비밀번호를 입력하지 않았습니다. 비밀번호를 입력해주세요");
			$('input[name=userpw]').focus();
			return false;
		}
		else{
			alert("회원가입 완료!");
		}
		
  	});
	
	$('td').not('input[name=userid]').click(function(){
		if ($('input[name=idChk]').val() === 'valid'){
			$("#idChk").html("");
			$("#hidden-td, #idChk").css("display", "none");
		}
	});


	$('input[name=userid]').keyup(function(){
		$("#hidden-td, #idChk").attr('style', "display:'';"); 
		
		var url = 'checkID.do?userid=' + $('input[name=userid]').val();
		$.getJSON(url, function(data) {
			if (data.id === $('input[name=userid]').val()) {
				$("#idChk").html("이미 있는 아이디 입니다.");
				$('input[name=idChk]').val("invalid");
				
			}else if($('input[name=userid]').val()==''){
				$("#idChk").html("아이디를 입력하지 않으셨습니다.");
				$('input[name=idChk]').val("invalid");
			}
			else {
				$("#idChk").html("사용할 수 있는 아이디 입니다.");
				$("#idChk").attr('class', "text-success");
				$('input[name=idChk]').val("valid");
				}
			});
		});
	});
</script>
</body>
</html>
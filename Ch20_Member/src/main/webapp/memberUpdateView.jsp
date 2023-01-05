<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.1.1.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h2>회원 수정</h2>

	<form action="update.do" method="post">
		<table cellpadding="0">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="${member.name }"></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" value="${member.id }"
					readonly></td>
			</tr>
			<tr>
				<td>암호</td>
				<td><input type="password" name="userpw"
					value="${member.password }">*</td>
			</tr>
			<tr>
				<td>암호 확인</td>
				<td><input type="password" name="pwChk" value="${member.password }">*</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="email" name="email" value="${member.email }"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="tel" value="${member.tel }"></td>
			</tr>
			<tr>
				<td>등급</td>
				<td><input type="radio" name="role" value="2"
					<c:if test ="${member.role eq '2'}">checked="checked"</c:if> />일반회원
					<input type="radio" name="role" value="1"
					<c:if test ="${member.role eq '1'}">checked="checked"</c:if> />관리자</td>
			</tr>
			<tr>
				<td><input type="submit" value="확인"></td>
				<td><a href="memberView.jsp"><input type="button"
						value="취소"></a></td>
			</tr>
		</table>
	</form>
<script type="text/javascript">

$('form').submit(function(){
	if($('input[name=userpw]').val()!==$('input[name=pwChk]').val()){
		alert("암호가 일치하지 않습니다 한번더 확인해주세요.");
		return false;
	}
})



</script>
</body>
</html>
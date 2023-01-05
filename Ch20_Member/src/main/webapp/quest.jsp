<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8"); 
	String cp = request.getContextPath(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록 만들기(ajax)</title>

<link rel="stylesheet" href="<%=cp %>/data/css/style.css" type="text/css">
<script type="text/javascript" src="<%=cp%>/data/js/jquery-3.4.1.min.js"></script>

<script type="text/javascript">

	// 화면이 호출되면 표시할 첫 페이지
	$(function(){
		
		listPage(1);
		
	});
	
	
	// 새로고침X
	$(document).ready(function(){
		
		$("#sendButton").click(function(){
			
			// javascript는 document.getElementById 이렇게 접근했었음
			// jquery는 사용자가 아래서 입력한 데이터를 이렇게 받아옴
			var params = "name=" + $("#name").val()
					+ "&email=" + $("#email").val()
					+ "&content=" + $("#content").val();
			
			$.ajax({
				// 입력창 보내는거니깐 post형태로
				// 현재 struts2+spring로 연결하는거임
				type:"POST",  
				url:"<%=cp%>/guest/created.action", 
				data:params,
				success:function(args){
					// 결과 데이터는 이미 표로 가공된 상태로 올 것임 (방명록 글 창)
					$("#listData").html(args);
					
					//새로고침한게 아니라 ajax부분만 바뀌기 때문에, 사용자가 [등록하기] 누르고 나면 
					// 입력창에 사용자가 입력한 데이터가 그대로 남아있음
					// => input 태그에 입력된 데이터를 없애주는 소스가 필요 (초기화)
					$("#name").val("");
					$("#email").val("");
					$("#content").val("");
					$("#name").focus; // 다 지우고 name에다가 커서 갖다놓기
					
				},
				beforeSend:showRequest, // 보내기전에 실행
				error:function(e) {
					alert(e.responseText); // 갔다와서 에러가 나면 알람을 띄워라 
				}
			}); // ...end $.ajax
			
		});
		
	});
	
	function showRequest() {
		
		// 사용자가 입력한 데이터를 공백 제거하고 가져오기
		var name = $.trim($("#name").val());
		var email = $.trim($("#email").val());
		var content = $.trim($("#content").val());
		
		if(!name) {
			alert("\n이름을 입력하세요!");
			$("#name").focus;
			return false;
		}
		if (!email) {
			alert("\n이메일을 입력하세요!");
			$("#email").focus;
			return false;
		}
		if (!content) {
			alert("\n내용을 입력하세요!");
			$("#content").focus;
			return false;
		}
		
		if (!content.length > 200) {
			alert("\n내용을 200자까지만 입력 가능합니다!");
			$("#content").focus;
			return false;
		}
		
		// true가 반환되어야만, 서버로 값을 보냄
		return true;
	}
	
	
	// 3페이지 누르면 3페이지 뜨게
	// 페이징처리를 js로 
	function listPage(page) {
		
		var url = "<%=cp%>/guest/list.action";
		
		//jQuery로만 ajax를 만들 때 사용
		$.post(url,{pageNum:page},function(args) {
			// js특징중하나
			//url 에 pageNum,page를 가지고 들어가서 
			//결과를 바로 받아온다 (보내는애와 받는애가 같이 있는 구조)
			
			$("#listData").html(args);
			
		});
		
		$("#listData").show(); // display:block으로 프로퍼티를 설정한 것과 동일한 효과
		
	}
	
	
	// 삭제할번호, 페이징처리를 위한 pageNum을 매개변수로 받아야함
	// 새로고침X
	function deleteData(num,page) {
		
		var url = "<%=cp%>/guest/deleted.action";
		
		// *jQuery로만 ajax를 만들 때 사용하는 방법*
		// 변수명:데이터
		
		$.post(url,{num:num,pageNum:page},function(args) {
		//결과를 바로 받아온다 (보내는애와 받는애가 같이 있는 구조)
			
		// 삭제 후 댓글창 결과(args) 뿌려줌
		$("#listData").html(args);
			
		});
		
		
		$("#listData").show();
		
	}
	
	
</script>
</head>
<body>

<br/><br/>

<table width="600" border="2" cellpadding="0" cellspacing="0"
bordercolor="#e6d4a6" align="center">
<tr height="40">
	<td style="padding-left: 20px;">방 명 록</td>
</tr>	
</table>


<br/><br/>

<table width="600" border="0" cellpadding="0" cellspacing="0" align="center">

<tr>
	<td width="600" colspan="4" height="3" bgcolor="#e6d4a6"></td>
</tr>

<tr>
	<td width="60" height="30" bgcolor="#eeeeee" align="center">작성자</td>
	
	<td width="240" height="30" style="padding-left: 10px;">
	<input type="text" id="name" size="35" maxlength="20" class="boxTF"/>
	</td>
	
	<td width="60" height="30" bgcolor="#eeeeee" align="center">
	이메일
	</td>
	<td width="240" height="30" style="padding-left: 10px;">
	<input type="text" id="email" size="35" maxlength="50" class="boxTF"/>
	</td>
</tr>

<tr>
	<td width="600" colspan="4" height="1" bgcolor="#e6d4a6"></td>
</tr>

<tr>
	<td width="60" height="30" bgcolor="#eeeeee" align="center">
	내용
	</td>
	<td width="540" colspan="3" style="padding-left: 10px;">
	<textarea rows="3" cols= "84" class="boxTA" style="height: 50px;" id="content">
	</textarea>
	</td>
</tr>

<tr>
	<td width="600" colspan="4" height="1" bgcolor="#e6d4a6"></td>
</tr>

<tr>
	<td width="600" height="30" colspan="4" align="right" style="padding-right: 15px;">
	<input type="button" value="등록하기" class="btn2" id="sendButton"/>
	</td>
</tr>

</table>

<br/><br/>

<!-- 방명록 글 (=댓글창) 뜨는 부분 -->
<span id="listData" style="display: none">
</span>

<br/>
</body>
</html>
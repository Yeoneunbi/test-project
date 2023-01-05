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
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
body {
	color: #566787;
	background: #f7f5f2;
	font-family: 'Roboto', sans-serif;
	text-align : center;
}

.view_content_wrap {
	
	min-width: 1000px;
	background: #fff;
	padding: 20px 25px;
	padding-top : 3rem;
	border-radius: 3px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
}
</style>
</head>
<body>
	<jsp:include page="../topNavbar.jsp"></jsp:include>
	
	
	<div class="view_content_wrap container-lg">
		<header>
			<h2 class="title">
				<span class="title_subject">${board.title }</span>
			</h2>
			<div class="content_writer">
				<span class="id">${board.id } |</span>
				<span class="date">${board.regDate } |</span>
				<span>조회수 ${board.cnt }</span>
			</div>
			<hr>
		</header>
	<div class="content">
		<div class="write_div">
			<div>${board.content }</div>
		</div>
	</div>
		<hr>
		<c:if test="${member.id eq board.id }">
		<div>
			<span><a href="<%=request.getContextPath() %>/checkUpdate.do?id=${member.id }&seq=${board.seq}"><input type="button" class="btn btn-secondary btn-lg" value="수정"></a></span>
			<span><a href="<%=request.getContextPath() %>/checkDelete.do?id=${member.id }&seq=${board.seq}"><input type="button" class="btn btn-secondary btn-lg" value="삭제"></a></span>
		</div>
		</c:if>
		<br><br>
		<!-- 댓글창보이는블럭태그 -->
	<div id="viewComment"></div>
	
	<div class="comment-form">
        <div>
            <table class="table table-striped" style="text-align: center; border : 1px solid #dddddd">
                <tr>
                    <td><br><br>${member.id}</td>
                    <td><input type="text" style="height: 100px; width : 500px;" class="form-control" name="commentText" id="commentTextInput"></td>
					<td><input type="hidden" name="userID" value="${member.id }"></td>
					<td><input type="hidden" name="bbsID" value="${board.seq }"></td>
                    <td><br><br><button type="button" id="commentWriteBtn" class="btn-primary pull">댓글등록</button></td>
                </tr>
            </table>
        </div>
    </div>
    
    
	</div>
	    <div class="container-lg">
        <div class="table-responsive">
            <div class="table-wrapper">			
                <table class="table table-striped">
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
						<td align="left"> <a href="<%=request.getContextPath() %>/getBoard.do?seq=${board.seq }">${board.title }</a> </td>
						<td>${board.id }</td>
						<td>${board.regDate }</td>
						<td>${board.cnt } </td>
                        </tr>
                    </c:forEach> 
                    </tbody>
                </table>
            </div>
        </div>        
    </div>     
	<script type="text/javascript">
	$(function(){
		getListComment();
	});
	
	$.ajaxSetup({
		type:'POST',
		dataType : 'text',
		error:function(x,e){
	            if(x.status==0){
	            console.log('You are offline!!n Please Check Your Network.');
	            }else if(x.status==404){
	            console.log('Requested URL not found.');
	            }else if(x.status==500){
	            console.log('Internel Server Error.');
	            }else if(e=='parsererror'){
	            console.log('Error.nParsing JSON Request failed.');
	            }else if(e=='timeout'){
	            console.log('Request Time out.');
	            }else {
	            console.log('Unknow Error.n'+x.responseText);
	            }
	        }
	});
	
	function getListComment(){
		var url = "<%=request.getContextPath()%>/getlistcomment.do?bbsID="+'${board.seq}';
		$.getJSON(url,function(data){
			//data가 array이고  data크기가 0이아니면 해당메서드를 실행하라
			if(Array.isArray(data) && data.length!==0)
			showHtml(data);
		});
	}
	
	
	function hide(rid,rid_id,content){
		console.log('클릭은됬음');
		
		$('div #rid'+rid+' span').html("<textarea style='width:100%;' name='commentText'>"+content+"</textarea>");
		var html = "<span class='col-2' id='updateBtn' onclick='update_comment(\"" + rid  +"\")'>수정완료</span>";
		$('#updateBtn'+rid).replaceWith(html);
		var cancle = "<span onclick='getListComment()'>취소</span></a>"
		$('#deleteBtn'+rid).replaceWith(cancle);
		
	}
	
	function update_comment(rid){
		var comment_val = $('textarea[name=commentText]').val();		
		
		$.ajax({
			url : "<%=request.getContextPath()%>/updatecomment.do",
			data : {
				"commentText" : comment_val,
				"commentid" : rid,
				"bbsid" : ${board.seq}
			},
			success: function(result){
				getListComment();
			}
		})
		
	}
	
	function delete_comment(rid){
		$.ajax({
			url : "<%=request.getContextPath()%>/deletecomment.do",
			data : {
				"commentid" : rid,
				"bbsid" : ${board.seq}
			},
			success : function(result){
				alert('삭제되었습니다.');
				getListComment();
			}
		})
	}

	
	function showHtml(data){
		let html ="<div class= 'bg-light px-5 pt-4 text-left'>댓글<hr>";
		$.each(data,function(index, el){
			html += "<div class='comment_area bg-light  pt-2'>"
			html += "<div class='comment_info text-left id='comment_info'>";
			html += "<span>&#129489;<strong>"+el.userid+"</strong></span>";
			html += "</div><div class='comment_text_wrap text-left' id='rid"+el.commentid+"'>";
			html += "<span>"+el.commentText+"</span></div>";
			let presentDay = el.commentdate;
			html += "<div class='text-secondary text-left comment_bottom'>"+presentDay;
			if('${member.id}'===el.userid){
			html += "<a href='javascript:void(0)'><span class='col-2' id='updateBtn"+el.commentid+"' onclick='hide("+ el.commentid+ ",\""+el.userid+"\",\"" + el.commentText  +"\")'>수정</span></a>";
			html += " <a href='javascript:void(0)'><span id='deleteBtn"+el.commentid+"' onclick='delete_comment("+el.commentid+")'>삭제</span></a> ";
			}
			html += "</div><hr>";
			html += "</div>";
			});
		html+="</div>";
					
		$('#viewComment').html(html);
	};
	

	
	
	
	$('.comment-form #commentWriteBtn').click(function(){
		
		if($('.comment-form #commentTextInput').val()==''){
			alert('댓글을적어주세요');
			return false;
		}
		
		$.ajax({
			url : "<%=request.getContextPath()%>/writerComment.do",
			data : {"bbsID" : '${board.seq}',
					"userID" : '${member.id}',
					"commentText" : $('.comment-form #commentTextInput').val()},
			success:function(data){
					console.log('성공적으로 데이터를 보냈습니다.');
					getListComment();
					$('.comment-form #commentTextInput').val('');
					$(".comment-form #commentTextInput").focus();
			}
		});
		
		return false;
	});
		
	
	</script>
</body>
</html>
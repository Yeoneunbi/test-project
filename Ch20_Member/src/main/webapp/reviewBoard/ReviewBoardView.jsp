<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/CSS/navCSS.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
body {
	background-image: linear-gradient(to right, #ccd5e0 0%, #96b2f3 100%);
	font-family: 'Varela Round', sans-serif;
	padding-top: 6rem;
}
button {
  all:unset;

}
.review_board {
	margin: 3rem;
	padding: 2rem;
	padding-bottom : 1rem;
	width: 50%;
	height: 20%;
	border-radius: 30px;
	 box-shadow: 0px 0px 20px 1px rgba(141, 139, 139, 0.203);
}

.review_board a{
	all:unset;
	cursor: pointer;
}

.review_board a:hover{
	color : violet;
}

#review_board_date{
	text-align: right;
}
</style>
</head>
<body>
	<jsp:include page="../topNavbar.jsp"></jsp:include>
	<div id="review_wrraping" class="px-6">
	<c:forEach items="${reviewList}" var="review" varStatus="theCount">
		<c:choose>
			<c:when test="${member.id eq review.userid }">     
				<div id="review_board${theCount.count}" style="float: right;" class="review_board bg-primary text-white">
			</c:when>
			<c:otherwise>
				<div id="review_board" style="float: left;" class="review_board bg-light">
			</c:otherwise>
		</c:choose>
		
			<div class="d-flex bd-highlight">
				<div class="p-2 w-100 bd-highlight">#${review.reviewid } <span>${review.userid }님이 읽은 <a href = "https://search.kyobobook.co.kr/search?keyword=${review.bookTitle}" target="_blank">[${review.bookTitle}]</a>📖</span></div>
				<c:if test="${member.id eq review.userid }">
					<div class="p-2 flex-shrink-1 bd-highlight">
					<!-- 아이콘html태그 -->
						<button type="button" onclick="delReview(${theCount.count},${review.reviewid })"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg " viewBox="0 0 16 16">
				 			<path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
						</svg></button>
					</div>
				</c:if>
			</div>
			<hr>
			<div>${review.reviewText }</div>
			<hr>
			<div id="review_board_date" >${review.reviewDate } 에 작성한 리뷰입니다.</div>
		</div>
	</c:forEach>
	</div>
	
	
	<button type="button" class="btn btn-primary fixed-bottom" data-toggle="modal" data-target="#review-write-Modal">리뷰작성하기</button>

	<div class="modal fade" id="review-write-Modal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">리뷰작성하기</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">무슨책을 읽으셨나요?</label>
							<input type="text" class="form-control" id="review-book-name">
						</div>
						<div class="form-group">
							<label for="message-text" class="col-form-label">리뷰내용:</label>
							<textarea class="form-control" id="review-message-text"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" onclick="writeRvBtn()")>Send message</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function writeRvBtn(){
		var text = $('#review-message-text').val();
		var title = $('#review-book-name').val();
		$.ajax({
			url : "<%=request.getContextPath()%>/writereview.do",
			data : {
				"reviewText" : text,
				"bookTitle" :title,
				"userid" : '${member.id}'
			},
			success: function(result){
				alert('리뷰가 등록되었습니다');
				$('#review-write-Modal').hide();
				location.replace('<%=request.getContextPath() %>/reviewListCon.do');
			}
		})
		
		
	}
	
	
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
	
	function delReview(rid,rid_id){
		$.ajax({
			url : "<%=request.getContextPath()%>/deleteReview.do",
			data : {
				"reviewid" : rid_id
			},
			success : function(result){
				//누르면 사라지는 애니메이션추가
				$('#review_board'+rid).hide(1000);
			}
		});
		
		
		
	}

	
	</script>
</body>

</html>
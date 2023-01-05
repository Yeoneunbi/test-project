<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class="header-nav fixed-top">
		<nav class="navbar navbar-expand-xl navbar-dark bg-dark">
			<a href="#" class="navbar-brand"><i class="fa fa-book"></i>북작<b>북작</b></a>
			<button type="button" class="navbar-toggler" data-toggle="collapse"
				data-target="#navbarCollapse">
				<span class="navbar-toggler-icon"></span>
			</button>
			<!-- Collection of nav links, forms, and other content for toggling -->
			<div id="navbarCollapse"
				class="collapse navbar-collapse justify-content-start">
				<div class="navbar-nav ml-auto">
					<a href="<%=request.getContextPath() %>/mainView.jsp" class="nav-item nav-link active"><i class="fa fa-home"></i><span>Home</span></a> 
					<a href="<%=request.getContextPath() %>/boardListcon.do" class="nav-item nav-link"><i class="fa fa-comments"></i><span>커뮤니티</span></a>
					<a href="<%=request.getContextPath() %>/reviewListCon.do" class="nav-item nav-link"><i class="fa fa-pencil"></i><span>짧은감상문</span></a>
					<a href="<%=request.getContextPath() %>/boardListcon.do" class="nav-item nav-link"><i class="fa fa-users"></i><span>독서캠프신청</span></a>
				<c:if test="${member.role eq '1' }">
					<a href="<%=request.getContextPath() %>/boardListcon.do" class="nav-item nav-link"><i class="fa fa-gears"></i><span>관리자페이지</span></a>
				</c:if>
					
					<div class="nav-item dropdown">
						<a href="#" data-toggle="dropdown"
							class="nav-item nav-link dropdown-toggle user-action"><img
							src="https://www.tutorialrepublic.com/examples/images/avatar/3.jpg"
							class="avatar" alt="Avatar"> ${member.name } <b
							class="caret"></b></a>
						<div class="dropdown-menu">
							<a href="<%=request.getContextPath() %>/memberView.jsp" class="dropdown-item"><i
								class="fa fa-user-o"></i> Profile</a>
							<div class="divider dropdown-divider"></div>
							<a href="<%=request.getContextPath() %>/logout.do" class="dropdown-item"><i
								class="material-icons">&#xE8AC;</i> Logout</a>
						</div>
					</div>
				</div>
			</div>
		</nav>
	</div>
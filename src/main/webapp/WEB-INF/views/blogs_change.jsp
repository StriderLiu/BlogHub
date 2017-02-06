<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Blogs</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function(){
		$("li").mouseenter(function(){
			$(this).addClass("active");
		});
		$("li").mouseleave(function(){
			$(this).removeClass("active");
		});
	});
</script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation"
		style="background:#F4A460;">
		<div class='container-fluid'>
		<div class="navbar-header">
			<a class="navbar-brand" href="/FinalProject_V3/user"><b>BlogHub</b></a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li><a href="/bloghub/user"><b>HOME</b></a></li>
				<li><a href="/bloghub/blog"><b>BLOG</b></a></li>
				<li><a href="/bloghub/friends"><b>FRIENDS</b></a></li>
				<li><a href="/bloghub/message"><b>MESSAGE</b></a></li>
				<c:choose>
					<c:when test="${sessionScope.currentUser.name eq 'admin'}">
						<li><a href="/bloghub/manager" style='color:purple;'><b>USER MANAGEMENT</b></a></li>
					</c:when>
				</c:choose>
			</ul>
			<c:choose>
				<c:when test="${!(sessionScope.currentUser eq null)}">
					<ul class='nav navbar-nav navbar-right'>
						<li><a href="/bloghub/user"> <span
								class='glyphicon glyphicon-user'></span> <b>${sessionScope.currentUser.name}</b>
						</a></li>
						<li><a href="/bloghub/logout"> <span
								class='glyphicon glyphicon-log-out'></span> <b>Logout</b>
						</a></li>
					</ul>
				</c:when>
				
				<c:otherwise>
					<ul class='nav navbar-nav navbar-right'>
						<li><a href="/bloghub/register"> <span
								class='glyphicon glyphicon-user'></span> <b>Sign Up</b>
						</a></li>
						<li><a href="/bloghub/"> <span
								class='glyphicon glyphicon-log-in'></span> <b>Login</b>
						</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	</nav>

	<div class='col-md-4'>
		<c:forEach var="blog" items="${blogs}">
			<div class='row'>
				<div class='col-md-3'>
					<img src="/bloghub/user/${blog.user.id}/photo" height="80"
						width="80" class='img-circle' />
				</div>
				<div class='col-md-6'>
					<a href="/bloghub/blog/${blog.id}/viewBlog"
						style='font-size: 20px;'><b>${blog.title}</b></a> by
					[${blog.user.name}]
					<form action="/bloghub/friends/${blog.user.id}/add" method="get">
						<input type="submit" value="Add to Friends" class="btn btn-info">
					</form>
					<hr>
				</div>
			</div>

		</c:forEach>
	</div>
	<div class='col-md-7'>
		<h2>${currentBlog.title}</h2>
		<hr>
		
		<p>${currentBlog.content}</p>
		<hr>

		<c:forEach var="comment" items="${currentBlog.comments}">
			<div class='row'>
				<div class='col-md-2'>
					<img src="/bloghub/user/${comment.user.id}/photo"
						height="50" width="50" class='img-circle' />
				</div>
				<div class='col-md-4'>
					<h4>
						<b>${comment.user.name}</b> says:
					</h4>
				</div>
			</div>
			<p class='text-muted'>at [${comment.date}]</p>
			<p>${comment.content}</p>
		</c:forEach>

		<form:form role='form' modelAttribute="comment"
			action="/bloghub/comment/${currentBlog.id}/add" method="post">
			<form:textarea rows='3' class='form-control col-md-6' id='content'
					placeholder="Write comments here" path="content" />
			<input class='btn btn-info' type="submit" value="Submit" />
		</form:form>
	</div>

</body>
</html>
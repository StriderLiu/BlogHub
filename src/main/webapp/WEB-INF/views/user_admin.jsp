<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administrator Home</title>
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
	$(document).ready(function() {
		$("li").mouseenter(function() {
			$(this).addClass("active");
		});
		$("li").mouseleave(function() {
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
			<a class="navbar-brand" href="/bloghub/user"><b>BlogHub</b></a>
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

	<div class='container row'>
		<div class='col-md-6'>
		
			<div>
				<div class='row'>
					<div class='col-md-6'>
						<img src="/bloghub/user/${user.id}/photo" height="180"
							width="180" class='img-circle' />
					</div>
					<div class='col-md-6'>
						<p class='text-success' style="font-size:34px;">Welcome, administrator!</p>
					</div>
				</div>
				
				<h2>Profile:</h2>

				<table class='table table-hover table-condensed'>
					<tr>
						<td><b>User Name :</b></td>
						<td><c:out value="${user.name}"></c:out></td>
					</tr>
					<tr>
						<td><b>Email :</b></td>
						<td><c:out value="${user.email}"></c:out></td>
					</tr>
					<tr>
						<td><b>Phone Number:</b></td>
						<td><c:out value="${user.phoneNumber}"></c:out></td>
					</tr>
					<tr>
						<td><b>Gender :</b></td>
						<td><c:out value="${user.gender}"></c:out></td>
					</tr>
					<tr>
						<td><b>Country :</b></td>
						<td><c:out value="${user.country}"></c:out></td>
					</tr>
					<tr>
						<td><b>City :</b></td>
						<td><c:out value="${user.city}"></c:out></td>
					</tr>
					<tr>
						<td><b>Address :</b></td>
						<td><c:out value="${user.address}"></c:out></td>
					</tr>
					<tr>
						<td><a href="/bloghub/user/${user.id}/editProfile"
							style='font-size: 18px;'>Edit Profile</a></td>
						<td><form action="/bloghub/user/${user.id}/writeBlog"
								method="get">
								<input type="submit" value="Write New Blog"
									class='btn btn-lg btn-block btn-info' />
							</form></td>
					</tr>
				</table>
			</div>
		</div>

		<div class='col-md-6'>
			<a href='#' class='list-group-item active'><h2>My Blogs:</h2></a>
			<c:forEach var="blog" items="${user.blogs}">
				<a href="/bloghub/blog/${blog.id}/viewBlog"
					class='list-group-item'>${ blog.title }</a>
			</c:forEach>
			<hr>
			<h3>My Address:</h3>
			<img
				src="https://maps.googleapis.com/maps/api/staticmap?
				center=${user.address}&zoom=13&size=550x200
				&key=AIzaSyBQ55lOjZWlv9TrSQL8ytb5EjA693h8bCA">
		</div>
	</div>

</body>
</html>
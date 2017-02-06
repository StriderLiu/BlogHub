<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>

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
			<a class="navbar-brand" href="/bloghub/user"><b>BlogHub</b></a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li><a href="/bloghub/user"><b>HOME</b></a></li>
				<li><a href="/bloghub/blog"><b>BLOG</b></a></li>
				<li><a href="/bloghub/friends"><b>FRIENDS</b></a></li>
				<li><a href="/bloghub/message"><b>MESSAGE</b></a></li>
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

	<div class='container'>
		<form:form class='form-horizontal' role='form' modelAttribute="user"
			action="/bloghub/validate" method="post">
			<div class='form-group'>
				<form:label class='col-md-2 control-label' for="name" path="name"
					style="font-size:18px;">UserName:</form:label>
				<div class='col-md-10'>
					<form:input class='form-control' id='name'
						placeholder="Please Enter the UserName" path="name" />
				</div>
			</div>

			<div class='form-group'>
				<form:label class='col-md-2 control-label' for="password"
					path="password" style="font-size:18px;">Password:</form:label>
				<div class='col-md-10'>
					<form:password class='form-control' id='password'
						placeholder="Please Enter the Password" path="password" />
				</div>
			</div>

			<div class='form-group'>
				<div class='col-md-offset-2 col-md-10'>
					<input class='btn btn-success' type="submit" value="Login" /> <input
						class='btn btn-warning' type="reset" value="Reset" />
				</div>
			</div>
		</form:form>
		<fieldset>
			<p class='col-md-offset-2' style="font-size: 18px;">New User?</p>
			<a class='col-md-offset-2' href="register"
				style="font-size: 20px; color: #E63F00;">Register For Free!</a>
		</fieldset>
	</div>



</body>
</html>
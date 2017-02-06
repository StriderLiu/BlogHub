<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Registration</title>
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
			</ul>
			<c:choose>
				<c:when test="${!(session.currentUser eq null)}">
					<ul class='nav navbar-nav navbar-right'>
						<li><a href="/bloghub/user"> <span
								class='glyphicon glyphicon-user'></span> <b>${session.currentUser.name}</b>
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
		<h1>New User Register:</h1>
		<p class='text-danger'>Fields With "*" Must Be Completed</p>
		<form:form class='form-horizontal' role='form' modelAttribute="user"
			action="/bloghub/register" method="post"
			enctype="multipart/form-data">
			<div class='form-group'>
				<form:label class='col-md-2 control-label' for="name" path="name"
					style="font-size:16px;">*UserName:</form:label>
				<div class='col-md-8'>
					<form:input class='form-control' id='name'
						placeholder="Please Enter the UserName" path="name" />
				</div>
			</div>

			<div class='form-group'>
				<form:label class='col-md-2 control-label' for="password"
					path="password" style="font-size:16px;">*Password:</form:label>
				<div class='col-md-8'>
					<form:password class='form-control' id='password'
						placeholder="Please Enter the Password" path="password" />
				</div>
			</div>

			<div class='form-group'>
				<form:label class='col-md-2 control-label' for="email" path="email"
					style="font-size:16px;">Email:</form:label>
				<div class='col-md-8'>
					<form:input class='form-control' id='email'
						placeholder="Please Enter the Email" path="email" />
				</div>
			</div>

			<div class='form-group'>
				<form:label class='col-md-2 control-label' for="phoneNumber"
					path="phoneNumber" style="font-size:16px;">Phone Number:</form:label>
				<div class='col-md-8'>
					<form:input class='form-control' id='phoneNumber'
						placeholder="Please Enter the Phone Number" path="phoneNumber" />
				</div>
			</div>

			<div class='form-group'>
				<form:label class='col-md-2 control-label' for="gender"
					path="gender" style="font-size:16px;">Gender:</form:label>
				<div class='col-md-4'>
					<form:radiobutton id='male' path="gender"
						value="Male" label="Male" />
					<form:radiobutton id='male' path="gender"
						value="Female" label="Female" />
				</div>
			</div>

			<div class='form-group'>
				<form:label class='col-md-2 control-label' for="country"
					path="country" style="font-size:16px;">Country:</form:label>
				<div class='col-md-8'>
					<form:input class='form-control' id='country'
						placeholder="Please Enter the Country" path="country" />
				</div>
			</div>

			<div class='form-group'>
				<form:label class='col-md-2 control-label' for="city" path="city"
					style="font-size:16px;">City:</form:label>
				<div class='col-md-8'>
					<form:input class='form-control' id='city'
						placeholder="Please Enter the City" path="City" />
				</div>
			</div>

			<div class='form-group'>
				<form:label class='col-md-2 control-label' for="address"
					path="address" style="font-size:16px;">Address:</form:label>
				<div class='col-md-8'>
					<form:input class='form-control' id='address'
						placeholder="Please Enter the Address" path="address" />
				</div>
			</div>

			<div class='form-group'>
				<form:label class='col-md-2 control-label' for="photo" path="photo"
					style="font-size:16px;">Upload Photo:</form:label>
				<div class='col-md-8'>
					<form:input type='file' class='form-control' id='photo'
						path="photo" />
				</div>
			</div>

			<div class='form-group'>
				<div class='col-md-offset-2 col-md-8'>
					<input class='btn btn-info' type="submit" value="Register" /> <input
						class='btn btn-warning' type="reset" value="Reset" />
				</div>
			</div>
		</form:form>
	</div>

</body>
</html>
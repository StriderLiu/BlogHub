<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Message</title>
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
	
	<div class='col-md-4'>
		<h2>Messages:</h2>
		<hr>
			<c:forEach var="message" items="${messages}">
				<div class='row'>
					<div class='col-md-2'>
						<img src="/bloghub/user/${message.sender_name}/pic"
							height="50" width="50" class='img-circle' />
					</div>
					<div class='col-md-6'>
						<p>
							<b>${message.sender_name}</b>
						</p>
					</div>
				</div>
				<div class='row col-md-offset-1'>
					<div class='col-md-4'>
						<p class='text-info'>[${message.subject}]</p>
					</div>
					<div class='col-md-8'>
						<p class='text-warning'>${message.content}</p>
					</div>	
				</div>
			</c:forEach>
	</div>

	<div class='col-md-7'>
		<h2>Write a Message</h2>
		<hr>
		<form:form role='form' modelAttribute="newMessage"
			action="/bloghub/message/send" method="post">
			<div class='form-group'>
				<form:label class='col-md-2 control-label' for="receiver_name"
					path="receiver_name" style="font-size:18px;">Name:</form:label>
				<form:input class='form-control' id='receiver_name'
					placeholder="Enter the Name of the Receiver" path="receiver_name" />
			</div>

			<div class='form-group'>
				<form:label class='col-md-2 control-label' for="subject"
					path="subject" style="font-size:18px;">Subject:</form:label>
				<form:input class='form-control' id='subject'
					placeholder="Enter the Subject" path="subject" />
			</div>

			<div class='form-group'>
				<form:label class='col-md-2 control-label' for="content"
					path="content" style="font-size:18px;">Content:</form:label>
				<form:textarea row='15' class='form-control' id='content'
					placeholder="Enter the Content" path="content" />
			</div>
			<input class='btn btn-success' type="submit" value="Send Message" />
		</form:form>
	</div>

</body>
</html>
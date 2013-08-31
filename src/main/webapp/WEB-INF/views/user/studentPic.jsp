<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html ng-app="user">
<head>
<title>wechat console</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<header>
		<div class="navbar navbar-inverse navbar-static-top">
			<div class="navbar-inner">
				<a class="brand" href="/admin">wechat 管理</a>
				<ul class="nav pull-right">
					<li><a href="<c:url value='/user/studentPic'/>">新生新体验</a></li>
					<li><a href="<c:url value='/logout'/>">logout</a></li>
				</ul>
			</div>
		</div>
	</header>
	<div ng-view></div>
	<li><a href="<c:url value='/user/readPicFromWechat'/>">从wechat服务器同步相片</a></li>
	<p>Nothing here {{1 + 2}}</p>
	<script type="text/javascript"
		src="<c:url value="/resources/js/vendor/jquery-2.0.3.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/vendor/angular.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/vendor/angular-resource.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/vendor/bootstrap.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/config.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/app.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/services.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/controllers.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/directives.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/filters.js" />"></script>

</body>
</html>
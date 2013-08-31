<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html ng-app="main">
<head>
<title>wechat console</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<header>
		<div class="navbar navbar-inverse navbar-static-top"
			ng-controller="NavCtrl">
			<div class="navbar-inner">
				<a class="brand" href="#">wechat 管理</a>
				<ul class="nav">
					<li ng-repeat="nav in navs"
						ng-class="{'active': location.url().indexOf(nav.url)==1}"><a
						ng-href="#/{{nav.url}}">{{nav.name}}</a></li>
				</ul>
				<ul class="nav pull-right">
					<li><a href="<c:url value='/user/studentPic'/>">新生新体验</a></li>
					<li><a href="<c:url value='/logout'/>">logout</a></li>
				</ul>
			</div>
		</div>
	</header>
	<div ng-view></div>
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
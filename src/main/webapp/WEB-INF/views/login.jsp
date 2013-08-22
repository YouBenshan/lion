<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Wechat Console Login</title>
</head>
<body>
	<div id="box">
		<div class="title">Wechat Console Login</div>

		<div class="content">
			<form:form modelAttribute="loginCommand">

				<form:errors path="*" element="div" cssClass="errors" />

				<div>
					<div class="form-label">Username:</div>
					<form:input path="username" />
				</div>
				<div>
					<div class="form-label">Password:</div>
					<form:password path="password" />
				</div>
				<div>
					<form:checkbox path="rememberMe" />
					Remember Me
				</div>
				<div>
					<input type="submit" value="Login" />
				</div>
			</form:form>

			<div>
				Don't have an account? <a href="<c:url value="/s/signup"/>">Sign
					up</a>
			</div>
		</div>
	</div>

	<p>username:itms password:itms</p>

	<script type="text/javascript">
        document.getElementById('username').focus();
    </script>

</body>
</html>

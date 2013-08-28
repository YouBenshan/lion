<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Student Info</title>
</head>
<body>
	<form:form modelAttribute="studengInfo">

				<input name="other" type="hidden" value="${param.other}">
				<div>
					<div>姓名:</div>
					<input name="name" type="text"/>
				</div>
				<div>
					<div>学校 :</div>
					<input name="school" type="text"/>
				</div>
				<div>
					<div>手机:</div>
					<input name="mobile" type="number"/>
				</div>
				<div>
					<input type="submit" value="提交" />
				</div>
			</form:form>

</body>
</html>
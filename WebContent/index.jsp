<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>index</title>

</head>
<body>
	<c:choose>
		<c:when test="${not empty sessionScope.userInfoMap.us_no}">
			<jsp:forward page="/page/main.jsp"/>			
		</c:when>
		<c:otherwise>
			<jsp:forward page="/page/login2.jsp"/>
		</c:otherwise>
	</c:choose>
</body>
</html>

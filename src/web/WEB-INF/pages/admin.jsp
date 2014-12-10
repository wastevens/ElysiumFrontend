<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
<html>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>
 
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name}
			
			<form th:action="@{/logout}" method="post">
    		<input type="submit" value="Log out" />
    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</h2>
	</c:if>
 
</body>
</html>
<html>
<body>
	<form id="logoutForm" action="/logout" method="post"></form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>Welcome : ${pageContext.request.userPrincipal.name}
		
		<p><a href="/user/page/characters">Manage characters</a></h2>
		
        <p><a href="javascript:formSubmit()" >Logout</a></h2>  
	</c:if>
</body>
</html>
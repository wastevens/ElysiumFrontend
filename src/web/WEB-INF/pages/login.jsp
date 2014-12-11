<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head><title>Login Page</title></head>
<body>
	<script>
		function formSubmit(formId) {
			document.getElementById(formId).submit();
		}
	</script>
	<h1>Login to Elysium</h1>
	<div id="login-box">
		<form name="f" action="/login" method="post">
			<c:if test="${not empty error}">
				<div style="color:red,font-style:bold">${error}</div>
			</c:if>
			<c:if test="${not empty message}">
				<div style="color:blue,font-style:bold">${message}</div>
			</c:if>
		    <table>
			<tr><td>Username or Email Address:</td><td><input type='text' name='username' value=''></td></tr>
			<tr><td>Password:</td><td><input type='password' name='password' /></td></tr>
			<tr><td colspan='2'><input name="submit" type="submit" value="submit" /></td></tr>
		   </table>
		   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<p><a href="/resetPassword">Reset your Password</a></h2>
		<p><a href="/createAccount">Create new Account</a></h2>
	</div>
 
</body>
</html>
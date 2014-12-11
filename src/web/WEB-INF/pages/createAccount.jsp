<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head><title>Login Page</title></head>
<body>
	<h1>Create Account for Elysium</h1>
	<div id="login-box">
		<h3>Enter your email address, username and password</h3>
		<form name="f" action="/createAccount" method="post">
		    <table>
		    <tr><td>Email:</td><td><input type='text' name='email' value=''></td></tr>
			<tr><td>Username:</td><td><input type='text' name='username' value=''></td></tr>
			<tr><td>Password:</td><td><input type='password' name='password' /></td></tr>
			<tr><td colspan='2'><input name="submit" type="submit" value="submit" /></td></tr>
		   </table>
		   
		   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
 
</body>
</html>
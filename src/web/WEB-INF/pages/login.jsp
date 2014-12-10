<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
<head><title>Login Page</title></head>
<body>
	<h1>Spring Security Custom Login Form (Annotation)</h1>
	<div id="login-box">
		<h3>Login with Username and Password</h3>
		<form name="f" th:action="@{/login}" method="post">
 
		    <table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
			        <td colspan='2'>
                                <input name="submit" type="submit" value="submit" />
                                </td>
			</tr>
		   </table>
 
		   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
 
</body>
</html>
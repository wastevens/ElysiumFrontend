<html>
<head><title>Request Password Reset</title></head>
<body>
	<h1>Request Password Reset</h1>
	<div id="login-box">
		<h3>Enter your email address</h3>
		<form name="f" action="/passwordResetRequested" method="post">
		    <table>
		    <tr><td>Email:</td><td><input type='text' name='email' value=''></td></tr>
			<tr><td colspan='2'><input name="submit" type="submit" value="submit" /></td></tr>
		   </table>
		</form>
	</div>
 
</body>
</html>
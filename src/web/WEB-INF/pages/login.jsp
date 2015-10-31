<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Login Page</title>
</head>
<body>
	<h1 style="text-align: center">Login to The Green Room</h1>
	<div id="login-box" class="well col-md-4 col-md-offset-4">
		<form name="f" action="/login" method="post">
			<c:if test="${not empty error}">
				<div style="color:red,font-style:bold">${error}</div>
			</c:if>
			<c:if test="${not empty message}">
				<div style="color:blue,font-style:bold">${message}</div>
			</c:if>
			<h3>Login</h3>
				<label>Email Address:</label><input type='text' name='username' value='' required autofocus /><br/>
				<label>Password:</label><input type='password' name='password' required /><br/>
				<button class="btn btn-info" name="submit" type="submit" value="submit">
					<span class="glyphicon glyphicon-save"></span> Submit
				</button>
				<button class="btn btn-info" onclick="window.location.href='/requestPasswordReset'">
                	<span class="glyphicon glyphicon-save"></span> Password Reset
            	</button>
            	<button class="btn btn-info" onclick="window.location.href='/createAccount'">
                	<span class="glyphicon glyphicon-save"></span> Create Account
            	</button>
		</form>
	</div>
	<script>
		function formSubmit(formId) {
			document.getElementById(formId).submit();
		}
	</script>
</body>
</html>
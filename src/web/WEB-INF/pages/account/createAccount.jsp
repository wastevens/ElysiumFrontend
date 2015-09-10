<link rel="stylesheet" href = "/css/bootstrap.min.css">
<html>
<head><title>Create Account Page</title></head>
<body>
<nav id="Navbar" class="navbar navbar-fixed-top navbar-default alert-info" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/login">
                The Green Room
                <small> v1.0</small>
            </a>
        </div>

    </div>
</nav>
<header class="jumbotron">
    <div class="container text-center">
        <h1>Sign up for The Green Room</h1>
    </div>
</header>
<div class="container">
    <section class="first">
    <div class="row">
	<div id="login-box" class="col-lg-12">
		<form name="f" action="/createAccount" method="post">
			<c:if test="${not empty error}">
				<div style="color:red,font-style:bold">${error}</div>
			</c:if>
		    <table class="table">
		    <tr><td>Email:</td><td><input type='text' name='email' required=true/></td></tr>
            <tr><td>Password:</td><td><input type='password' name='password' required=true/></td></tr>
            <tr><td>First Name (optional):</td><td><input type='firstName' name='firstName'/></td></tr>
            <tr><td>Last Name (optional):</td><td><input type='lastName' name='lastName'/></td></tr>
            <tr><td>Old Database Username (optional):</td><td><input type='text' name='originalUsername'/></td></tr>
            <tr><td>Last Paypal Patronage Receipt Id (optional):</td><td><input type='text' name='paymentReceiptIdentifier'/></td></tr>
			<tr><td>
                <button class="btn btn-info" name="submit" type="submit" value="submit">
                    <span class="glyphicon glyphicon-save"></span> Submit
                </button>
            </td><td></td></tr>
		   </table>
		</form>
    </div>
    </div>
    </section>
</div>
 
</body>
</html>
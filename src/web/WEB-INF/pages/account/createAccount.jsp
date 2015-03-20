<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<link rel="stylesheet" href = "/css/bootstrap.min.css">
<html>
<head><title>Create Account Page</title></head>
<body>
<nav id="Navbar" class="navbar navbar-fixed-top navbar-default alert-info" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/login">
                <i class="glyphicon glyphicon-tower"></i>
                elysium
                <small> v0.1</small>
            </a>
        </div>

    </div>
</nav>
<header class="jumbotron">
    <div class="container text-center">
        <h1>Sign up for an Elysium Account</h1>
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
		    <tr><td>Email:</td><td><input type='text' name='email' value=''></td></tr>
            <tr><td>Password:</td><td><input type='password' name='password' /></td></tr>
            <tr><td>First Name:</td><td><input type='firstName' name='firstName' /></td></tr>
            <tr><td>Last Name:</td><td><input type='lastName' name='lastName' /></td></tr>
			<tr><td>
                <button class="btn btn-info" name="submit" type="submit" value="submit">
                    <span class="glyphicon glyphicon-save"></span> Submit
                </button>
            </td><td></td></tr>
		   </table>
		   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
    </div>
    </div>
    </section>
</div>
 
</body>
</html>
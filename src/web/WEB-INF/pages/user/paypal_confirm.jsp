<html>
<head>
    <link rel="stylesheet" href = "/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Underground Theater - ${pageContext.request.userPrincipal.name}</a>
    </div>
    <div>
      <form id="logoutForm" action="/logout" method="post"></form>
      <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
      </script>      
      <ul class="nav navbar-nav">
        <li><a href="/user/main">Home</a></li>
        <li class="active"><a href="#">Manage Patronage</a></li>
        <li><a href="/user/page/characters">Manage Characters</a></li>
        <li><a href="javascript:formSubmit()">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container">
    <form id="confirmForm" action="/user/patronage/payments/paypal/confirm" method="post"></form>
    <script>
    function confirmSubmit() {
        document.getElementById("confirmForm").submit();
    }
    </script>
    Confirm you Paypal Patronage Donation!
    <br>This is the screen that will (soon) display the details of the paypal payment the user has just made.
    <br>They will then confirm their payment by clicking a link, <a href="javascript:confirmSubmit()">like this!</a>
</div>
</body>
</html>
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
    <form id="confirmForm" action="/user/patronage/payments/paypal/confirm" method="post">
      <input type="hidden" name="token" value="${GetExpressCheckoutDetailsResponse.confirmationToken}" />
      <input type="hidden" name="payerId" value="${GetExpressCheckoutDetailsResponse.payerId}" />
      <input type="hidden" name="amount" value="${GetExpressCheckoutDetailsResponse.amount}" />
    </form>
    <script>
    function confirmSubmit() {
        document.getElementById("confirmForm").submit();
    }
    </script>
    <h1>Please confirm you Paypal Patronage Donation!</h1>
    
    <div class="row">
        <div class="col-md-2"><strong>Paypal Email Address</strong></div> <div class="col-md-6">${GetExpressCheckoutDetailsResponse.email}</div>
    </div>
    <div class="row">
        <div class="col-md-2"><strong>Donation Date</strong></div> <div class="col-md-6">${GetExpressCheckoutDetailsResponse.timestamp}</div>
    </div>
    <div class="row">
        <div class="col-md-2"><strong>Amount</strong></div> <div class="col-md-6">${GetExpressCheckoutDetailsResponse.amount}</div>
    </div>
    
    <p>If this looks correct, please confirm your donation by <a href="javascript:confirmSubmit()">clicking here!</a>
</div>
</body>
</html>
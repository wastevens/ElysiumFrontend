<html>
<head>
<link rel="stylesheet" href = "/css/bootstrap.min.css">

<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-resource.js"></script>
<script src="/js/resources/en_US/user/paymentTypes.js"></script>
<script src="/js/services/authorization.js"></script>
<script src="/js/services/paymentTypeRepository.js"></script>
<script src="/js/sources/paymentTypes.js"></script>
<script src="/js/user/patronage.js"></script>
<script src="/js/filters/payment_type.js"></script>
</head>
<body ng-app="user.patronage">
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
	<manage-patronage user='${user}'></manage-patronage>
	<form id="donateForm" action="/user/patronage/payments/paypal" method="post"></form>
<script>
function donateSubmit() {
    document.getElementById("donateForm").submit();
}
</script>
<br><a href="javascript:donateSubmit()">Make a Paypal Patronage Donation!</a>
</div>
</body>
</html>
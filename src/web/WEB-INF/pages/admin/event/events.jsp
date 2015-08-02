<html>
<head>
<link rel="stylesheet" href = "/css/bootstrap.min.css">

<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-resource.js"></script>
<script src="/js/admin/event.js"></script>
<script src="/js/admin/services/eventRepository.js"></script>
<script src="/js/services/authorization.js"></script>
</head>

<body ng-app="admin.event">
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
        <li><a href="/admin/main">Home</a></li>
        <li><a href="/admin/page/users">Manage Users</a></li>
        <li><a href="/admin/page/troupes">Manage Troupes</a></li>
        <li class="active"><a href="#">Manage Events</a></li>
        <li><a href="javascript:formSubmit()">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="container">
	<list-events></list-events>
</div>
</body>
</html>
<html>
<head>
<link rel="stylesheet" href = "/css/bootstrap.min.css">

<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-resource.js"></script>
<script src="/js/resources/en_US/vampire/settings.js"></script>
<script src="/js/sources/vampire.js"></script>
<script src="/js/filters/vampire.js"></script>
<script src="/js/services/redirection.js"></script>
<script src="/js/services/authorization.js"></script>
<script src="/js/services/troupeRepository.js"></script>
<script src="/js/services/traitRepository.js"></script>
<script src="/js/admin/services/userRepository.js"></script>
<script src="/js/admin/troupe.js"></script>
</head>
<body ng-app="admin.troupe">
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
        <li class="active"><a href="#">Manage Troupes</a></li>
        <li><a href="/admin/page/events">Manage Events</a></li>
        <li><a href="javascript:formSubmit()">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="container">
	<list-troupes></list-troupes>
	<add-troupe></add-troupe>
</div>
</body>
</html>
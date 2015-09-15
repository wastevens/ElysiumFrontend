<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-resource.js"></script>
<script src="/js/sources/displayableValues.js"></script>
<script src="/js/services/authorization.js"></script>
<script src="/js/services/traitRepository.js"></script>
<script src="/js/services/troupeRepository.js"></script>
<script src="/js/sources/vampire.js"></script>
<script src="/js/filters/vampire.js"></script>
<script src="/js/admin/event.js"></script>
<script src="/js/admin/services/eventRepository.js"></script>

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
<div class="container-fluid" ng-controller="eventsController" >
	<list-events></list-events>
	<manage-event event="event"></manage-event>
</div>
</body>
</html>
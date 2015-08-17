<html>
<head>
<link rel="stylesheet" href = "/css/bootstrap.min.css">

<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-resource.js"></script>
<script src="/js/resources/en_US/character/attributeFocuses.js"></script>
<script src="/js/sources/displayableValues.js"></script>
<script src="/js/sources/vampire.js"></script>
<script src="/js/filters/vampire.js"></script>
<script src="/js/sources/approval_status.js"></script>
<script src="/js/filters/approval_status.js"></script>
<script src="/js/sources/player_status.js"></script>
<script src="/js/filters/player_status.js"></script>
<script src="/js/services/traitRepository.js"></script>
<script src="/js/services/authorization.js"></script>
<script src="/js/services/characterRepository.js"></script>
<script src="/js/user/character.js"></script>
</head>
<body ng-app="user.character">
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
        <li><a href="/user/page/patronage">Manage Patronage</a></li>
        <li class="active"><a href="#">Manage Characters</a></li>
        <li><a href="javascript:formSubmit()">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container">
	<list-characters></list-characters>
	<p><a href="/user/page/character/create">Create a new characters</a></p>
</div>
</body>
</html>
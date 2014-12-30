<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-resource.js"></script>
<script src="/js/sources/settings.js"></script>
<script src="/js/filters/setting.js"></script>
<script src="/js/sources/approval_status.js"></script>
<script src="/js/filters/approval_status.js"></script>
<script src="/js/sources/player_status.js"></script>
<script src="/js/filters/player_status.js"></script>
<script src="/js/services/csrfResource.js"></script>
<script src="/js/services/characterRepository.js"></script>
<script src="/js/user/character.js"></script>
</head>
<body ng-app="user.character">

	<list-characters csrf='{"header": "${_csrf.headerName}", "token": "${_csrf.token}"}'></list-characters>
	<p><a href="/user/page/character/create">Create a new characters</a>

</body>
</html>
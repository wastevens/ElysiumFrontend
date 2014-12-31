<html>
<head>
<base href="/" />
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-resource.js"></script>
<script src="/js/sources/settings.js"></script>
<script src="/js/filters/setting.js"></script>
<script src="/js/services/redirection.js"></script>
<script src="/js/services/csrfResource.js"></script>
<script src="/js/services/troupeRepository.js"></script>
<script src="/js/services/characterRepository.js"></script>
<script src="/js/user/character_creation.js"></script>
</head>
<body ng-app="user.character.creation">

	<create-character csrf='{"header": "${_csrf.headerName}", "token": "${_csrf.token}"}'></create-character>

</body>
</html>
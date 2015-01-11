<html>
<head>
<base href="/" />
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-resource.js"></script>
<script src="/js/sources/clans.js"></script>
<script src="/js/sources/disciplines.js"></script>
<script src="/js/sources/settings.js"></script>
<script src="/js/filters/setting.js"></script>
<script src="/js/services/redirection.js"></script>
<script src="/js/services/csrfResource.js"></script>
<script src="/js/services/troupeRepository.js"></script>
<script src="/js/services/characterRepository.js"></script>
<script src="/js/user/character_manage.js"></script>
</head>
<body ng-app="user.character.manage">

	<manage-character character='${character}' csrf='{"header": "${_csrf.headerName}", "token": "${_csrf.token}"}'></manage-character>

</body>
</html>
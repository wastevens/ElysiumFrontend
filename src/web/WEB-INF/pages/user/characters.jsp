<html>
<head>
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
<script src="/js/services/authorization.js"></script>
<script src="/js/services/characterRepository.js"></script>
<script src="/js/user/character.js"></script>
</head>
<body ng-app="user.character">

	<list-characters></list-characters>
	<p><a href="/user/page/character/create">Create a new characters</a>

</body>
</html>
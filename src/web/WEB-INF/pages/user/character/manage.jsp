<html>
<head>
<base href="/" />
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-resource.js"></script>
<script src="/js/resources/en_US/character/attributeFocuses.js"></script>
<script src="/js/sources/displayableValues.js"></script>
<script src="/js/sources/vampire.js"></script>
<script src="/js/sources/attributes.js"></script>
<script src="/js/sources/skills.js"></script>

<script src="/js/filters/picker.js"></script>
<script src="/js/filters/attributes.js"></script>
<script src="/js/filters/vampire.js"></script>

<script src="/js/services/redirection.js"></script>
<script src="/js/services/authorization.js"></script>
<script src="/js/services/troupeRepository.js"></script>
<script src="/js/services/characterRepository.js"></script>
<script src="/js/services/traitRepository.js"></script>


<script src="/js/user/character_manage.js"></script>
</head>
<body ng-app="user.character.manage">

	<manage-character character='${character}'></manage-character>

</body>
</html>
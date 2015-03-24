<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-resource.js"></script>
<script src="/js/sources/settings.js"></script>
<script src="/js/filters/setting.js"></script>
<script src="/js/services/redirection.js"></script>
<script src="/js/services/csrfResource.js"></script>
<script src="/js/services/troupeRepository.js"></script>
<script src="/js/admin/services/userRepository.js"></script>
<script src="/js/admin/troupe.js"></script>
</head>
<body ng-app="admin.troupe">

	<list-troupes></list-troupes>
	<add-troupe></add-troupe>

</body>
</html>
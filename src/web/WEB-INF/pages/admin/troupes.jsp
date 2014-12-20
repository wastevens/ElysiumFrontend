<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-resource.js"></script>
<script src="/js/filters.js"></script>
<script src="/js/services.js"></script>
<script src="/js/admin/troupe.js"></script>
</head>
<body ng-app="admin.troupe">

	<list-troupes csrf='{"header": "${_csrf.headerName}", "token": "${_csrf.token}"}'></list-troupes>
	<add-troupe csrf='{"header": "${_csrf.headerName}", "token": "${_csrf.token}"}'></add-troupe>


</body>
</html>
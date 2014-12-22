<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-resource.js"></script>
<script src="/js/sources/roles.js"></script>
<script src="/js/filters/role.js"></script>
<script src="/js/filters/joinBy.js"></script>
<script src="/js/services/csrfResource.js"></script>
<script src="/js/admin/user.js"></script>
</head>
<body ng-app="admin.user">

	<list-users csrf='{"header": "${_csrf.headerName}", "token": "${_csrf.token}"}'></list-users>
	<modify-user csrf='{"header": "${_csrf.headerName}", "token": "${_csrf.token}"}'></modify-user>

</body>
</html>
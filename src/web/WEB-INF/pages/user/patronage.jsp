<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-resource.js"></script>
<script src="/js/sources/displayableValues.js"></script>
<script src="/js/services/authorization.js"></script>
<script src="/js/services/paymentTypeRepository.js"></script>
<script src="/js/sources/paymentTypes.js"></script>
<script src="/js/user/patronage.js"></script>
<script src="/js/filters/payment_type.js"></script>
</head>
<body ng-app="user.patronage">

	<manage-patronage user='${user}'></manage-patronage>

</body>
</html>
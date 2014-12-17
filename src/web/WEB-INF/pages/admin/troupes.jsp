<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.js"></script>
<script src="/js/filters.js"></script>
<script src="/js/http.js"></script>
<script src="/js/admin/troupe.js"></script>
</head>
<body ng-app="admin.troupe" ng-controller="deleteUrl">

<div id="showTroupes" ng-init='troupes = ${troupes}'>

<table>
  <tr><th>Name</th><th>Setting</th><th>Number of players</th><th>Delete this troupe?</th></tr>
  <tr ng-repeat="troupe in troupes">
    <td>{{troupe.name}}</td>
    <td>{{troupe.setting|setting}}</td>
    <td>{{troupe.numberOfPlayers}}</td>
    <td><a href ng-click="deleteUrl('/admin/troupes/', troupe.id, '${_csrf.headerName}', '${_csrf.token}')">Delete troupe</a></td>
  </tr>
</table>
</div>

<div id="addTroupe">
<p><h2>Add a new troupe</h2>
	<form name="f" action="/admin/troupes" method="post">
	    <table>
	    <tr><td>Name:</td><td><input type='text' name='name' value=''></td></tr>
	    <tr><td>Setting:</td><td>
	    	<select name='setting'>
	    		<option value=0>Camarilla</option>
	    		<option value=1>Anarch</option>
	    		<option value=2>Sabbat</option>
	    	</select>
	    </td></tr>
		<tr><td colspan='2'><input name="submit" type="submit" value="submit" /></td></tr>
	   </table>
	   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	</div>
</body>
</html>
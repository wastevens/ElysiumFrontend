<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html ng-app id="ng-app">
<head>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular.min.js"></script>
</head>
<body>
	<div ng-init='troupes = ${troupes}'>
	There are currently {{troupes.length}} troupes.  They are:
	<br>
	<table>
		<thead><tr><th>Name</th><th>Setting</th><th>Number of players</th></tr></thead>
		<tbody ng:repeat="i in troupes">
     	<tr>
     		<td>{{i.name}}</td><td>{{i.setting}}</td><td>{{i.numberOfPlayers}}</td>
    	</tr>
		</tbody>
	</table>
	</div>
	
	<div id="addTroupe">
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
angular.module('admin.troupe.filters', ['filters.setting']);

angular.module('admin.troupe.controllers', ['controllers.http']);

angular.module('admin.troupe', ['admin.troupe.filters', 'admin.troupe.controllers']);
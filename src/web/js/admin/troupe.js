angular.module('admin.troupe.filters', ['filters.setting']);

angular.module('admin.troupe.controllers', ['controllers.http']);

angular.module('directive.troupes', []).
directive('listTroupes', function() {
return {
  restrict: 'E',
  scope: {
    troupes: '=',
    csrf: '='
  },
  templateUrl: '/js/admin/troupes.html'
};
});

angular.module('admin.troupe.directives', ['directive.troupes']);

angular.module('admin.troupe', ['admin.troupe.filters', 'admin.troupe.controllers', 'admin.troupe.directives']);
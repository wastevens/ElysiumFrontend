angular.module('admin.troupe.filters', ['filters.setting']);

angular.module('admin.troupe.controllers', ['controllers.http']);

angular.module('directive.hello', []).
directive('myCustomer', function() {
return {
  restrict: 'E',
  scope: {
    customer: '=info'
  },
  templateUrl: '/js/admin/my-customer.html'
};
});

angular.module('admin.troupe.directives', ['directive.hello']);

angular.module('admin.troupe', ['admin.troupe.filters', 'admin.troupe.controllers', 'admin.troupe.directives']);
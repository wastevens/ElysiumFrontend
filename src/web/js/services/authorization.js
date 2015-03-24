angular.module('configuration.authorization', []).
factory('authInterceptor', function ($rootScope, $q, $window) {
  return {
    request: function (config) {
      console.log('authorization intercepted!');
      config.headers = config.headers || {};
      if ($window.sessionStorage.token) {
        config.headers.Authorization = $window.sessionStorage.token;
      }
      return config;
    },
    response: function (response) {
      if (response.status === 401) {
        // handle the case where the user is not authenticated
      }
      return response || $q.when(response);
    }
  };
}).
config(function ($httpProvider) {
  $httpProvider.interceptors.push('authInterceptor');
});
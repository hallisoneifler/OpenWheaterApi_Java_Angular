(function() {
  'use strict';

  angular
    .module('app.searchcity')
    .run(appRun);

  appRun.$inject = ['routerHelper'];
  /* @ngInject */
  function appRun(routerHelper) {
    routerHelper.configureStates(getStates());
  }

  function getStates() {
    return [
      {
        state: 'search-city',
        config: {
          url: '/search-city',
          templateUrl: 'app/search-city/search-city.html',
          controller: 'SearchCityController',
          controllerAs: 'vm',
          title: 'search-city',
          settings: {
            nav: 1,
            content: '<i class="fa fa-dashboard"></i> Search City'
          }
        }
      }
    ];
  }
})();

(function() {
  'use strict';

  angular
    .module('app.core')
    .service('searchcityservice', searchcityservice);

  searchcityservice.$inject = ['$http', '$q', 'exception', 'logger'];
  /* @ngInject */
  function searchcityservice($http, $q, exception, logger) {
    var service = {
      cityInfos : getCityInfos
    };

    return service;

    function getCityInfos(city) {
      if (city == "") {
        city = 'Sao paulo';
      }
      var c = 'http://api.openweathermap.org/data/2.5/weather?q=' + city + '&APPID=dc0ecb20b0180432d7c2df347acbfbad&units=metric';
      var result = {content:null};
      $http.get(c).success(function(data, status){
        result.content = data;
        return result;
      });
      return result;
    }
  }
})();

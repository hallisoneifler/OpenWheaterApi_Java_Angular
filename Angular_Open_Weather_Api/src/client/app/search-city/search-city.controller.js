(function() {
  'use strict';

  angular
    .module('app.searchcity')
    .controller('SearchCityController', SearchCityController);

  SearchCityController.$inject = ['$q', 'searchcityservice', 'logger'];
  /* @ngInject */
  function SearchCityController($q, searchcityservice, logger) {
    var vm = this;

    vm.url = {};
    vm.url = 'api.openweathermap.org/data/2.5/weather?q=';
    var key = '&APPID=dc0ecb20b0180432d7c2df347acbfbad';
    vm.title = 'Search City';
    vm.searchCityName = '';
    vm.cityInfos = {};

    vm.getCityInfos = function getCityInfos() {
      vm.cityInfos = searchcityservice.cityInfos(vm.searchCityName);
    }

    //GoogleMapsLoader.KEY = 'AIzaSyAj1ld0IduDGb_AGUBs7OJi9sPd-OUvfEw';
    //vm.map = {key:'AIzaSyAj1ld0IduDGb_AGUBs7OJi9sPd-OUvfEw',center: {lat:51.51,lon:-0.13}, zoom:8};
    activate();

    function activate() {
      vm.getCityInfos();
    }
  }
})();

(function () {
  'use strict';

  angular.module('app').config(['envServiceProvider', '$httpProvider', function (envServiceProvider, $httpProvider, interceptor) {

    envServiceProvider.config({
      vars: {
        production: {
          monitUrl: 'http://'+ location.host
        },
        development: {
          monitUrl: 'http://' + location.host.substring(0, location.host.lastIndexOf(":")) + ':8080'
        }
      }
    });

    envServiceProvider.check();

    $httpProvider.interceptors.push('interceptor');

  }]);
}());

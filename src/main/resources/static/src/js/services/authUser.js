/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
  'use strict';

  angular.module('app')
    .service('authUser', ['$cookies', '$location', function ($cookies, $location) {
      this.setUser = function (user) {
        $cookies.putObject('user', user);
      };

      this.setLogged = function (logged) {
        StorageHelper.setItem('logged', logged);
      };

      this.setConnected = function (connected) {
        $cookies.put('connected', connected);
      };

      this.getUser = function () {
        return $cookies.getObject('user');
      };

      this.isLogged = function () {
        return StorageHelper.getItem('logged');
      };

      this.isConnected = function () {
        return $cookies.get('connected');
      };

      this.authorize = function () {
        if (!this.isLogged()) {
          $location.path('/');
        }
      };

      this.removeCookies = function () {
        $cookies.remove('logged');
        $cookies.remove('connected');
        $cookies.remove('user');
      };
    }]);
}());
(function () {
  'use strict';

  angular.module('app').service('api', [ '$http', 'config', function ($http, config) {

    var KEY_STORAGE = 'token';
    var host = config.apiUrl();
    var baseUrl = host;

    this.loadUsers = function () {
      return $http({
        method: 'GET',
        url: baseUrl + '/api/users',
        headers: {
          'X-Auth-Token' : StorageHelper.getItem(KEY_STORAGE)
        }
      });
    };

    this.saveUser = function (entry) {
      return $http({
        method: 'POST',
        url: baseUrl + '/api/user',
        data: entry ,
        headers: {
          'Content-type': 'application/json;charset=utf-8',
          'X-Auth-Token' : StorageHelper.getItem(KEY_STORAGE)
        }
      });
    };

    this.editUser = function (entry) {
      return $http({
        method: 'PUT',
        url: baseUrl + '/api/user',
        data: entry ,
        headers: {
          'Content-type': 'application/json;charset=utf-8',
          'X-Auth-Token' : StorageHelper.getItem(KEY_STORAGE)
        }
      });
    };

    this.deleteUser = function (entry) {
      return $http({
        method: 'DELETE',
        url: baseUrl + '/api/user',
        data: entry ,
        headers: {
          'Content-type': 'application/json;charset=utf-8',
          'X-Auth-Token' : StorageHelper.getItem(KEY_STORAGE)
        }
      });
    };

    this.doLogin = function (entry) {
      return $http.post(baseUrl + '/api/auth', entry);
    };

    this.refreshToken = function (entry) {
      return $http({
        method:'GET',
        url: baseUrl + '/api/auth',
        params: {
          username: entry.username
        },
        headers: {
          'Content-type': 'application/json;charset=utf-8',
          'X-Auth-Token' : StorageHelper.getItem(KEY_STORAGE)
        }
      });
    };
  }]);
}());

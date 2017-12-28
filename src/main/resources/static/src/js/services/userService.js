/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
  'use strict';

  angular.module('app').service('userService', ['api', function (api) {

    this.loadUsers = function (entry) {
      return api.loadUsers(entry)
        .then(function (res) {
          return res.data;
        });
    };

    this.deleteUser = function (entry) {
      return api.deleteUser(entry)
        .then(function (res) {
          return res.status;
        });
    };

    this.saveUser = function (entry) {
      if (entry.hasOwnProperty('id') && entry.id) {
        return api.editUser(entry)
          .then(function (res) {
            return res;
          });
      } else {
        return api.saveUser(entry)
          .then(function (res) {
            return res;
          });
      }
    };

  }]);
}());

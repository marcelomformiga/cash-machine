/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
  'use strict';

  angular.module('app').service('profileService', ['api', function (api) {

    this.loadProfile = function (entry) {
      return api.loadProfile(entry)
        .then(function (res) {
          return res;
        });
    };

    this.deleteProfile = function (entry) {
      return api.deleteProfile(entry)
        .then(function (res) {
          return res;
        });
    };

    this.saveProfile = function (entry) {
      if (entry.hasOwnProperty('id') && entry.id) {
        return api.editProfile(entry)
          .then(function (res) {
            return res;
          });
      } else {
        return api.saveProfile(entry)
          .then(function (res) {
            return res;
          });
      }
    };

  }]);
}());

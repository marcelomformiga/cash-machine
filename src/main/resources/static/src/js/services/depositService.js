/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
  'use strict';

  angular.module('app').service('depositService', ['api', function (api) {

    this.deposit = function (entry) {
        return api.deposit(entry)
            .then(function (res) {
                return res;
            });
    };

  }]);
}());

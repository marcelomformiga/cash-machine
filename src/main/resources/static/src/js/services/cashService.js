/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
  'use strict';

  angular.module('app').service('cashService', ['api', function (api) {

    this.cashout = function (entry) {
        return api.cashout(entry)
            .then(function (res) {
                return res;
            });
    };

  }]);
}());

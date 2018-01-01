/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
  'use strict';

  angular.module('app').service('balanceService', ['api', function (api) {

    this.loadBalance = function (entry) {
        return api.loadBalance(entry)
            .then(function (res) {
                return res;
            });
    };

  }]);
}());

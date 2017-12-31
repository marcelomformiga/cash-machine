/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
  'use strict';

  angular.module('app').service('transactionService', ['api', function (api) {

    this.loadTransactions = function (entry) {
        return api.loadTransactions(entry)
            .then(function (res) {
                return res;
            });
    };

  }]);
}());

/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
  'use strict';

  angular.module('app').service('cashService', ['api', function (api) {

    this.process = function (entry) {
        return api.process(entry)
            .then(function (res) {
                return res;
            });
    };

  }]);
}());

/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
    'use strict';

    angular.module('app')
        .directive('balanceForm', [function () {
            return {
                restrict: 'E',
                templateUrl: 'views/partials/balance/balance-form.html'
            }
        }])
}());
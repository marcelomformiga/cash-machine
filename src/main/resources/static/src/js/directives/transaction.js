/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
    'use strict';

    angular.module('app')
        .directive('transactionList', [function () {
            return {
                restrict: 'E',
                templateUrl: 'views/partials/transaction/transaction-list.html'
            }
        }])
}());
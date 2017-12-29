/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
    'use strict';

    angular.module('app')
        .directive('cashForm', [function () {
            return {
                restrict: 'E',
                templateUrl: 'views/partials/cash/cash-form.html'
            }
        }])
        .directive('cashList', [function () {
            return {
                restrict: 'E',
                templateUrl: 'views/partials/cash/cash-list.html'
            }
        }])
}());
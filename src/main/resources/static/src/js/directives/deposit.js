/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
    'use strict';

    angular.module('app')
        .directive('depositForm', [function () {
            return {
                restrict: 'E',
                templateUrl: 'views/partials/deposit/deposit-form.html'
            }
        }])
}());
/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
    'use strict';

    angular.module('app')
        .directive('profileForm', [function () {
            return {
                restrict: 'E',
                templateUrl: 'views/partials/profile/profile-form.html'
            }
        }])
}());
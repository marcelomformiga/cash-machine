/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
  'use strict';

  angular.module('app')
    .directive('userForm', [ function () {
      return {
        restrict: 'E',
        templateUrl: 'views/partials/users/user-form.html'
      }
    }])
    .directive('userList', [ function () {
      return {
        restrict: 'E',
        templateUrl: 'views/partials/users/user-list.html'
      }
    }])
}());
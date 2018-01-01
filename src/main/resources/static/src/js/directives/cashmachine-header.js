/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
  'use strict';

  angular.module('app').directive('cashmachineHeader', [ function () {
    return {
      restrict: 'E',
      templateUrl: 'views/partials/header.html',
    }
  }]);
}());

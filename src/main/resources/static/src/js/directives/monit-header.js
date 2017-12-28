/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
  'use strict';

  angular.module('app').directive('monitHeader', [ function () {
    return {
      restrict: 'E',
      templateUrl: 'views/partials/header.html',
    }
  }]);
}());

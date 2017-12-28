/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
  'use strict';

  angular.module('app').directive('monitFooter', [ function () {
    return {
      restrict: 'E',
      templateUrl: 'views/partials/footer.html'
    }
  }]);
}());
/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
  'use strict';

  angular.module('app')
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
      $urlRouterProvider.otherwise("/login");
      $stateProvider
        .state('login', {
          url: "/login",
          templateUrl: "views/login.html",
          controller: "loginCtrl"
        })
        .state('home', {
          abstract: true,
          url: "",
          templateUrl: "views/home.html"
        })
        .state('home.users', {
          url: "/users",
          templateUrl: "views/pages/users.html",
          controller: "userCtrl"
        });
    }]);
}());

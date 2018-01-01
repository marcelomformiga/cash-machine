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
                .state('home.menu', {
                    url: "/menu",
                    templateUrl: "views/pages/menu.html",
                    controller: "menuCtrl"
                })
                .state('home.cash', {
                    url: "/cash",
                    templateUrl: "views/pages/cash.html",
                    controller: "cashCtrl"
                })
                .state('home.balance', {
                    url: "/balance",
                    templateUrl: "views/pages/balance.html",
                    controller: "balanceCtrl"
                })
                .state('home.deposit', {
                    url: "/deposit",
                    templateUrl: "views/pages/deposit.html",
                    controller: "depositCtrl"
                })
                .state('home.transaction', {
                    url: "/transaction",
                    templateUrl: "views/pages/transaction.html",
                    controller: "transactionCtrl"
                })
                .state('home.profile', {
                    url: "/profile",
                    templateUrl: "views/pages/profile.html",
                    controller: "profileCtrl"
                });
        }]);
}());

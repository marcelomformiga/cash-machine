/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
    'use strict';

    angular.module('app')
        .controller('menuCtrl', ['$scope', 'authUser', '$location',
                function ($scope, authUser, $location) {

                    var KEY_STORAGE = 'token';

                    StorageHelper.setItem("previous_page", "menu");

                    authUser.authorize();

                    $("body").removeClass('cashmachine-color');

                    $scope.logout = function () {
                        StorageHelper.removeItem(KEY_STORAGE);
                        authUser.setLogged(false);
                        authUser.removeCookies();
                        $location.path('/');
                    };
                }
            ]
        );
}());
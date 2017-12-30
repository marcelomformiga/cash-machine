/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
    'use strict';

    angular.module('app')
        .controller('menuCtrl', ['$scope', 'authUser', '$location', 'socketService',
            function ($scope, authUser, $location, socketService) {


                    var KEY_STORAGE = 'token';
                    var KEY_SESSION = 'sessionId';

                    StorageHelper.setItem("previous_page", "menu");
                    authUser.authorize();
                    console.log(StorageHelper.getItem(KEY_SESSION));
                    if(StorageHelper.getItem(KEY_SESSION)) {
                        socketService.disconnect();
                    }

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
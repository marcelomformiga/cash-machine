/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
    'use strict';
    angular.module('app')
        .controller('loginCtrl', ['$scope', '$location', 'authUser', 'loginService', 'socketService', 'toastr',
                function ($scope, $location, authUser, loginService, socketService, toastr) {

                    $("body").addClass('cashmachine-color');
                    StorageHelper.setItem("previous_page", "login");
                    $scope.entry = {};

                    $scope.submitLoginForm = function () {
                        if ($scope.form.$valid) {
                            loginService.login($scope.entry)
                                .then(function (data) {
                                    authUser.setUser(data);
                                    authUser.setLogged(true);
                                    $scope.changeBackgroundColor();
                                    toastr.success("Login realizado com sucesso!", {timeOut: 900});
                                    $location.path('/menu');
                                })
                                .catch(function (error) {
                                    if (error.status === 401) {
                                        toastr.error('Usuário ou Senha Inválido!', {timeOut: 900});
                                    } else {
                                        toastr.error('Falha no login', {timeOut: 900});
                                        console.log(error);
                                    }
                                });
                        }
                    };

                    $scope.changeBackgroundColor = function () {
                        $("body").removeClass('cashmachine-color');
                    };

                    function isLogged() {
                        if (authUser.isLogged() && authUser.isConnected()) {
                            $location.path('/menu');
                        }
                    }
                    isLogged();
                }
            ]
        );
}());

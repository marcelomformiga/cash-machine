/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
    'use strict';

    angular.module('app')
        .controller('cashCtrl', ['$scope', 'authUser', '$location',
                function ($scope, authUser, $location) {

                    StorageHelper.setItem("previous_page", "cash");

                    authUser.authorize();

                    $("body").removeClass('cashmachine-color');

                    $scope.process = function () {
                        if ($scope.form.$valid) {
                            profileService.saveProfile($scope.user)
                                .then(function (res) {
                                    if (res.status === 201) {
                                        $scope.user = res.data;
                                        toastr.success('Salvo com sucesso', {timeOut: 900});
                                    } else if (res.status === 200) {
                                        refreshToken(res.data);
                                        toastr.success('Editado com sucesso', {timeOut: 900});
                                    } else {
                                        toastr.error('Não foi possível salvar o usuário', {timeOut: 900});
                                    }
                                })
                                .catch(function () {
                                    toastr.error('Ocorreu um problema ao salvar o usuário', {timeOut: 900});
                                })
                        }
                    };

                    $scope.back = function () {
                        $location.path('/menu');
                    };

                }
            ]
        );
}());
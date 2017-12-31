/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
    'use strict';

    angular.module('app')
        .controller('depositCtrl', ['$scope', 'authUser', '$location', 'depositService', 'socketService', 'SweetAlert', 'toastr',
                function ($scope, authUser, $location, depositService, socketService, SweetAlert, toastr) {

                    var user = authUser.getUser();

                    StorageHelper.setItem("previous_page", "deposit");
                    $scope.cash = {
                        username:user.username
                    };
                    authUser.authorize();

                    $("body").removeClass('cashmachine-color');

                    $scope.process = function () {
                        SweetAlert.swal({
                            title: 'Confirmar?',
                            text: 'Deseja confirmar a operação?',
                            type: 'warning',
                            showCancelButton: true,
                            confirmButtonColor: '#89bedd',
                            confirmButtonText: 'Sim',
                            cancelButtonText: 'Cancelar',
                            closeOnConfirm: true
                        }, function (isConfirm) {
                            if (isConfirm) {
                                depositService.deposit($scope.cash).then(function (res) {
                                    var response = res.data;

                                    if(response.message === "Cannot find account by User") {
                                        toastr.error('Conta de usuário não encontrada!', {timeOut: 7000});
                                    } else {
                                        toastr.success('Operação concluída', {timeOut: 7000});
                                    }
                                }).catch(function () {
                                    toastr.error('Ocorreu um problema na operação', {timeOut: 2000});
                                })
                            }
                        });
                    };

                    $scope.back = function () {
                        $location.path('/menu');
                    };

                }
            ]
        );
}());
/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
    'use strict';

    angular.module('app')
        .controller('cashCtrl', ['$scope', 'authUser', '$location', 'cashService', 'SweetAlert', 'toastr',
                function ($scope, authUser, $location, cashService, SweetAlert, toastr) {

                    StorageHelper.setItem("previous_page", "cash");
                    $scope.cash = {};
                    $scope.notes = [];
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
                                cashService.process($scope.cash).then(function (res) {
                                    if (res.status === 200) {
                                        $scope.notes = res.data;
                                        toastr.success('Operação realizada com sucesso', {timeOut: 900});
                                    } else {
                                        toastr.error('Ocorreu um problema na operação', {timeOut: 900});
                                    }
                                }).catch(function () {
                                    toastr.error('Ocorreu um problema na operação', {timeOut: 900});
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
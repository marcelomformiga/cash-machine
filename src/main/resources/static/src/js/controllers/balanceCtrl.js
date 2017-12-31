/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
    'use strict';

    angular.module('app')
        .controller('balanceCtrl', ['$scope', 'authUser', '$location', 'balanceService', 'toastr',
                function ($scope, authUser, $location, balanceService, toastr) {

                    var user = authUser.getUser();

                    StorageHelper.setItem("previous_page", "cash");
                    $scope.cash = {};
                    authUser.authorize();

                    function loadBalance() {
                        balanceService.loadBalance(user).then(function (res) {
                            var response = res.data;

                            if(response.message === "Cannot find account by User") {
                                toastr.error('Conta de usuário não encontrada!', {timeOut: 7000});
                            } else {
                                toastr.success('Operação concluída', {timeOut: 7000});
                            }
                            $scope.cash = response;
                        }).catch(function () {
                            toastr.error('Ocorreu um problema na operação', {timeOut: 2000});
                        })
                    }
                    loadBalance();

                    $("body").removeClass('cashmachine-color');

                    $scope.back = function () {
                        $location.path('/menu');
                    };

                }
            ]
        );
}());
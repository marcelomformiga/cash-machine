/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
    'use strict';

    angular.module('app')
        .controller('transactionCtrl', ['$scope', 'authUser', '$location', 'transactionService', 'toastr',
                function ($scope, authUser, $location, transactionService, toastr) {

                    var user = authUser.getUser();

                    StorageHelper.setItem("previous_page", "transaction");
                    $scope.transactions = [];
                    authUser.authorize();

                    function loadTransactions() {
                        transactionService.loadTransactions(user).then(function (res) {
                            var response = res.data;

                            if (response.message === "Cannot find account by User") {
                                toastr.error('Conta de usuário não encontrada!', {timeOut: 7000});
                            } else {
                                toastr.success('Operação concluída', {timeOut: 7000});
                            }
                            showTransactiosn(response.transactionList);
                        }).catch(function () {
                            toastr.error('Ocorreu um problema na operação', {timeOut: 2000});
                        })
                    }

                    loadTransactions();

                    $("body").removeClass('cashmachine-color');

                    $scope.back = function () {
                        $location.path('/menu');
                    };

                    function showTransactiosn(transactions) {
                        var type = "";
                        angular.forEach(transactions, function (value, key) {
                            if (value.transaction_type === "CASHOUT") {
                                type = "SAÍDA";
                            } else {
                                type = "ENTRADA";
                            }
                            var date = new Date(value.date);
                            $scope.transactions.push({
                                value: value.value,
                                date: date.toLocaleDateString() + " " + date.toLocaleTimeString(),
                                transaction_type: type
                            });
                        });

                    }
                }
            ]
        );
}());
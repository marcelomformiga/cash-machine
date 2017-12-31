/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
    'use strict';

    angular.module('app')
        .controller('cashCtrl', ['$scope', 'authUser', '$location', 'cashService', 'socketService', 'SweetAlert', 'toastr',
                function ($scope, authUser, $location, cashService, socketService, SweetAlert, toastr) {

                    var KEY_SESSION = 'sessionId';
                    var KEY_STORAGE = 'token';
                    var KEY_LOGGED = 'logged';
                    var user = authUser.getUser();

                    StorageHelper.setItem("previous_page", "cash");
                    $scope.cash = {
                        username:user.username
                    };
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
                                    var response = res.data;
                                    if(response.message === "Insufficient funds") {
                                        toastr.error('Saldo insuficiente!', {timeOut: 7000});
                                    } else if(response.message === "Cannot find account by User") {
                                        toastr.error('Conta de usuário não encontrada!', {timeOut: 7000});
                                    } else if(response.message === "Invalid value") {
                                        toastr.error('Valor inválido!', {timeOut: 7000});
                                    } else {
                                        toastr.success('Operação concluída', {timeOut: 7000});
                                    }
                                    $scope.notes = response.cashList;

                                }).catch(function () {
                                    toastr.error('Ocorreu um problema na operação', {timeOut: 2000});
                                })
                            }
                        });
                    };

                    $scope.back = function () {
                        var parsed = JSON.stringify({
                            sessionId: StorageHelper.getItem(KEY_SESSION)
                        });
                        socketService.send("/topic/leave", {}, parsed);
                        StorageHelper.removeItem(KEY_SESSION);
                        $location.path('/menu');
                    };

                    var initStompClient = function () {

                        socketService.init();
                        socketService.connect(function (frame) {

                            socketService.subscribe("/topic/join/" + StorageHelper.getItem(KEY_STORAGE), function (response) {

                                var socketResponse = JSON.parse(response.body);
                                console.log(socketResponse);
                                if(socketResponse.message === "joined") {
                                    StorageHelper.setItem(KEY_SESSION, socketResponse.sessionId);
                                } else if (socketResponse.message === "User limit exceeded") {
                                    toastr.info('O limite máximo de usuários realizando saque foi excedido. Aguarde um  momento e tente novamente.', {timeOut: 7000});
                                    StorageHelper.removeItem(KEY_SESSION);
                                    socketService.disconnect();
                                    $location.path('/menu');
                                } else if (socketResponse.message === "User already connected") {
                                    toastr.info('Usuário ja conectado em outra sessão!.', {timeOut: 7000});
                                    StorageHelper.removeItem(KEY_SESSION);
                                    socketService.disconnect();
                                    $location.path('/menu');
                                }
                            });

                            socketService.subscribe("/topic/leave/" + StorageHelper.getItem(KEY_STORAGE), function (response) {
                                console.log("leave");
                                StorageHelper.removeItem(KEY_SESSION);
                                socketService.disconnect();
                                $location.path('/menu');
                            });

                            var user = authUser.getUser();
                            if (typeof  user !== 'undefined' && user !== null) {
                                var parsed = JSON.stringify({
                                    token: StorageHelper.getItem(KEY_STORAGE),
                                    username: user.username
                                });
                                socketService.send("/topic/join", {}, parsed);
                            }

                        }, function (error) {
                            toastr.error('Falha na Conexão', {timeOut: 900});
                        });
                    };
                    initStompClient();

                }
            ]
        );
}());
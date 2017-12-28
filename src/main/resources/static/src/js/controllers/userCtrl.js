/**
 * Created by Eduardo Alves.
 * Email: <eduardodinizo@hotmail.com>
 */

(function () {
    'use strict';

    angular.module('app')
        .controller('userCtrl', ['$scope', '$timeout', '$interval', 'toastr', 'SweetAlert', 'userService', 'authUser',
                'loginService', 'socketService', '$location',
                function ($scope, $timeout, $interval, toastr, SweetAlert, userService, authUser, loginService,
                          socketService, $location) {
                    //authUser.authorize();

                    var KEY_SESSION = 'sessionId';
                    var KEY_STORAGE = 'token';
                    var KEY_LOGGED = 'logged';
                    var promise;

                    $scope.isEdit = false;
                    $scope.users = [];
                    $scope.account = {};
                    $scope.user = {
                        authorities:"ROLE_ADMIN",
                        account: $scope.account
                    };

                    $scope.saveUser = function () {
                        console.log($scope.user);
                        console.log("ddsd");
                        if ($scope.form.$valid) {
                            userService.saveUser($scope.user)
                                .then(function (res) {
                                    console.log(res);
                                    if (res.status === 201) {
                                        $scope.users.push(res.data);
                                        toastr.success('Salvo com sucesso', {timeOut: 900});
                                    } else if (res.status === 200) {
                                        if ($scope.user.loged) {
                                            refreshToken(res.data);
                                        }
                                        toastr.success('Editado com sucesso', {timeOut: 900});
                                    } else {
                                        toastr.error('Não foi possível salvar o usuário', {timeOut: 900});
                                    }
                                })
                                .catch(function () {
                                    toastr.error('Ocorreu um problema ao salvar o usuário', {timeOut: 900});
                                })
                                .finally(function () {

                                });
                        }
                    };

                    function loadUsers() {
                        userService.loadUsers()
                            .then(function (users) {
                                $scope.users = users;

                            })
                            .catch(function () {
                                toastr.error('Ocorreu um problema ao carregar os usuários', {timeOut: 900});
                            });
                    }


                    function refreshToken(entry) {
                        loginService.refreshToken(entry)
                            .then(function (data) {
                                authUser.setUser(data);
                                authUser.setLogged(true);
                            })
                            .catch(function (error) {
                                console.log(error);
                            });
                    }

                    $scope.editUser = function (user) {
                        $scope.isEdit = true;
                        $scope.user = angular.copy(user);
                        $scope.user.password = "";
                    };

                    var initStompClient = function () {

                        socketService.init();
                        socketService.connect(function (frame) {

                            socketService.subscribe("/topic/join/" + StorageHelper.getItem(KEY_STORAGE), function (response) {
                                var socketResponse = JSON.parse(response.body);
                                StorageHelper.setItem(KEY_SESSION, socketResponse.sessionId);
                                loadUsers();
                            });

                            socketService.subscribe("/topic/leave/" + StorageHelper.getItem(KEY_STORAGE), function (response) {
                                StorageHelper.removeItem(KEY_SESSION);
                                StorageHelper.removeItem(KEY_STORAGE);
                                StorageHelper.removeItem(KEY_LOGGED);
                                authUser.removeCookies();
                                socketService.disconnect();
                                $location.path('/');
                            });

                            socketService.subscribe("/topic/heartbeat/" + StorageHelper.getItem(KEY_STORAGE), function (response) {
                                console.log(response);
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


                    function initSocket() {
                        var oldURL = document.documentURI;
                        if(oldURL.indexOf("login") === -1) {
                            if (StorageHelper.getItem(KEY_SESSION) === null && StorageHelper.getItem(KEY_STORAGE) !== null) {
                                initStompClient();
                            } else if (StorageHelper.getItem(KEY_STORAGE) !== null) {
                                loadUsers();
                            }
                            $scope.start();
                        }
                    }

                    $(window).on("unload", function (e) {
                        StorageHelper.removeItem(KEY_SESSION)
                    });

                    $scope.start = function () {
                        $scope.stop();
                        promise = $interval(checkSocketConnection, 15 * 60 * 1000);
                    };

                    $scope.stop = function () {
                        $interval.cancel(promise);
                    };

                    $scope.$on('$destroy', function () {
                        $scope.stop();
                    });

                    function checkSocketConnection() {
                        if (StorageHelper.getItem(KEY_SESSION) !== null) {
                            var user = authUser.getUser();
                            var parsed = JSON.stringify({
                                token: StorageHelper.getItem(KEY_STORAGE),
                                username: user.username
                            });
                            socketService.send("/topic/heartbeat", {}, parsed);
                        }
                    }

                    initSocket();
                }
            ]
        );
}());
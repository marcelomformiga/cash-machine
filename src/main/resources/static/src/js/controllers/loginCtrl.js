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

          $scope.entry = {};
          $scope.logged = false;
          var KEY_STORAGE = 'token';
          var KEY_SESSION = 'sessionId';
          var KEY_LOGGED = 'logged';

          function clearForm() {
            $scope.entry = {};
          }

          $scope.submitLoginForm = function () {
            if ($scope.form.$valid) {
              loginService.login($scope.entry)
                .then(function (data) {
                  authUser.setUser(data);
                  authUser.setLogged(true);
                  if (document.getElementById("btnLogin").disabled === false) initStompClient();
                  document.getElementById("btnLogin").disabled = true;

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

          var initStompClient = function () {
            socketService.init();
            socketService.connect(function (frame) {

              socketService.subscribe("/topic/join/" + StorageHelper.getItem(KEY_STORAGE), function (response) {
                document.getElementById("btnLogin").disabled = false;
                var socketResponse = JSON.parse(response.body);

                $("body").removeClass('cashmachine-color');

                toastr.success("Login realizado com sucesso!", {timeOut: 900});

                StorageHelper.setItem(KEY_SESSION, socketResponse.sessionId);

                if ($scope.entry.hasOwnProperty('connected') && $scope.entry.connected) {
                  authUser.setConnected(true);
                }
                $location.path('/users');

                clearForm();

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

          function isLogged() {
            if (authUser.isLogged() && authUser.isConnected()) {
              $location.path('/videos-wall');
            }
          }

          isLogged();
        }
      ]
    );
}());

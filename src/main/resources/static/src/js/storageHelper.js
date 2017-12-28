var StorageHelper = (function() {

  var SH = {};

  SH.setItem = function(chave, valor) {
    window.localStorage.setItem(chave, angular.toJson(valor));
  };

  SH.getItem = function(chave) {
    return angular.fromJson(window.localStorage.getItem(chave));
  };

  SH.removeItem = function(chave) {
    window.localStorage.removeItem(chave);
  };

  return SH;

})();
var app = angular.module('gxc-rcalc', ['ionic']);

app.controller('RiskCalculatorController', function($scope) {
    
    $scope.items=[
                 {name:'Nombre'},{name:'RFC'},{name:'Edad/Fecha de nacimiento'}
                 ];
});

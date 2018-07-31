// 创建myApp模块
var myApp = angular.module('myApp', [])
 
// 创建一个controller，名为Left-navigation
myApp.controller('Left-navigation', ['$scope', function ($scope) {
  // 定义一个函数navFunc， 接受一个参数
 $scope.navFunc = function (arg) {
 // 让navAction变量等于函数传入过来的值arg
    $scope.navAction = arg;
  };
}]);
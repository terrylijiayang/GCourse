/**
 * 
 */
var app=angular.module('myLogin', ['ui.router'])
//前后台交互
app.config(['$httpProvider', function($httpProvider) {
	 		$httpProvider.defaults.headers.post["Content-Type"] =
         			"application/x-www-form-urlencoded";
 			 $httpProvider.defaults.
         		 transformRequest.unshift(function(data,headersGetter) {
                var key, result = [];
            	for (key in data) {
                 if (data.hasOwnProperty(key)) {
                     result.push(encodeURIComponent(key) + "="
                            + encodeURIComponent(data[key]));
                 }
             }
             return result.join("&");
        });
}])
//本地存数据服务
app.factory('locals', ['$window', function ($window) {
        return {        //存储单个属性
            set: function (key, value) {
                $window.localStorage[key] = value;
            },        //读取单个属性
            get: function (key, defaultValue) {
                return $window.localStorage[key] || defaultValue;
            },        //存储对象，以JSON格式存储
            setObject: function (key, value) {
                $window.localStorage[key] = JSON.stringify(value);//将对象以字符串保存
            },        //读取对象
            getObject: function (key) {
                return JSON.parse($window.localStorage[key] || '{}');//获取字符串并解析成对象
            }

        }
    }]);
app.controller("loginController", function($scope,$http,$window,$location,locals) {
	$scope.login=function(){
		 $http({
	            method:'POST',
	            url:'../../LoginServlet',
	            data:{
	            	name:"frontHome", 
	            	username:$scope.username,
	            	userpass:$scope.userpass,
	                }
	            }).success(function (data) {
	            	if(data==0){
	            		alert('登录失败');
	            	}
	            	else{
	            		console.log(data);
	            		locals.setObject("models",data.models);
	            		locals.set("username",data.name);
	            		$window.location="index.html";
	            	}
	            })
	}
})
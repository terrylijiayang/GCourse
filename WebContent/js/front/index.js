/**
 * 
 */

var app=angular.module('myapp', ['ui.router','myContruction','myResource','myProblem'])
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


 app.controller("HomeController", function($scope,$http) {
	 
	 //初加载
	 	angular.element(document).ready(function () {
	 	 
	 	});
	 
	 	//前台首页展示
        $http({
            method:'POST',
            url:'../../HomeServlet',
            data:{
            	name:"frontHome",         	
                }
            }).success(function (data) {
           	$scope.introduce = data.intro;
           	$scope.principal = data.principal;
           	$scope.sources = data.sources;
           	$scope.teams = data.teams;
          	$scope.dynamics=[];
           	if(data.dynamics.length>5){
           		for(var i=0;i<5;i++){
           			$scope.dynamics.push(data.dynamics[i]);
           		}
           	}else{
           		$scope.dynamics = data.dynamics;
           	}
           	
            })
 })
 
   /**
     * 初始化数据处理
     * @param  页面路由配置
     */
.config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider
        	//课程首页
            .state('index', {   
                url: '/index',
                templateUrl: 'home.html',
                controller:'HomeController',
            })
            //课程简介
            .state('construction', {    
                url: '/construction',
                templateUrl: 'course_construction.html',
            })
            .state("construction.intro", {
                url:"/intro",
                templateUrl: "contruction_intro.html"
             })
              .state("construction.dynamic", {
	            url:"/dynamic",
	            templateUrl: "construction_dynamic.html"
              })
              //课程资源      
             .state('resource', {    
                url: '/resource',
                templateUrl: 'course_resource.html',
            })
             .state("resource.courseware", {
                url:"/courseware",
                templateUrl: "resource_courseware.html"
             })
              .state("resource.vedio", {
                url:"/vedio",
                templateUrl: "resource_vedio.html"
             })
             //课程疑难解答
             .state('problem', {    
                url: '/problem',
                templateUrl: 'problem.html',
            })
             .state('problem.answer', {    
                url: '/answer',
                templateUrl: 'problem_answer.html',
            })
            .state('problem.tel', {    
                url: '/tel',
                templateUrl: 'problem_tel.html',
            })
            $urlRouterProvider.otherwise('/index');
})
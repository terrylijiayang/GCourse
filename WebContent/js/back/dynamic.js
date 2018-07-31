/**
 * 
 */
/**
 * 
 */
var app=angular.module('myapp', ['ui.router'])
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
app.controller("indexController", function($scope,$http,$window) {
			//显示数据
	        $http({
	            method:'POST',
	            url:'../../BackServlet',
	            data:{
	            	name:"Dyhome",
	            	
	                }
	            }).success(function (data) {
	            console.log(data);
	            $scope.names =data;
	            })
	            
            //删除
            $scope.delete_dy = function(id) {
        	  $http({
                  method:'POST',
                  url:'../../BackServlet',
                  data:{
                  	name:"Dydelete",
                  	id:id
                      }
                  }).success(function (data) {
                	  	if(data=="1"){
                	  	  $window.location.reload();
                	  	}
                  })
            }
        
        	//返回一行数据
	        	$scope.Id;
		        $scope.getRow = function(id) {
		        	$scope.Id=id;
		      	  $http({
		                method:'POST',
		                url:'../../BackServlet',
		                data:{
		                	name:"DygetRow",
		                	id:id
		                    }
		                }).success(function (data) {
		              	  $scope.dy_content = data[0].content;
		                  $scope.dy_time = data[0].time;
		                  $scope.h_id=data[0].id
		                })
		          }
        	
        	//修改
		        $scope.change = function(Id) {
		      	  $http({
		                method:'POST',
		                url:'../../BackServlet',
		                data:{
		                	name:"Dychange",
		                	id:$scope.h_id,
		                	content:$scope.dy_content,
		                	time:$scope.dy_time
		                    }
		                }).success(function (data) {
		                	 $window.location.reload();
		                })
		          }
		     //增加数据
		        $scope.add_data = function() {
		        	
			      	  $http({
			                method:'POST',
			                url:'../../BackServlet',
			                data:{
			                	name:"Dyadd",
			                	content:$scope.add_content2,
			                	time: $scope.add_time
			                    }
			                }).success(function (data) {
			              	  	/*$scope.names =data;*/
			                	 $window.location.reload();
			                })
			          }
		        //查看数据
		        $scope.data_row = function(id) {
			      	  $http({
			                method:'POST',
			                url:'../../BackServlet',
			                data:{
			                	id:id,
			                	name:"Dydata_row",
			                	content:$scope.content,
			                	time: $scope.time
			                    }
			                }).success(function (data) {
			              	  	/*$scope.names =data;*/
			                	  $scope.add_content1 = data[0].content;
				                  $scope.add_time1 = data[0].time;
			                })
			          }
        
 })
 
 /*.config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('index', {   
                url: '/index',
                templateUrl: 'home.html',
                controller:'indexController',
            })
            .state('intro', {    
                url: '/intro',
                templateUrl: 'webSet.html',
            })
             .state('resource', {    
                url: '/resource',
                templateUrl: 'course_resourse.html',
            })
             .state('dynamic', {    
                url: '/dynamic',
                templateUrl: 'course_dynamics.html',
            })
            $urlRouterProvider.otherwise('/index');
})
 */
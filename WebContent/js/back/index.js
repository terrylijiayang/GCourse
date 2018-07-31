/**
 * 
 */
var app=angular.module('myApp', ['ui.router','myModel','myPmanage','myProblem'])
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
//本地存储
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
app.controller("blackController", function($scope,$http,$window,$location, locals, $timeout, $compile) {
	$http({
        method:'POST',
        url:'../../BackServlet',
        data:{
        	name:"home",
            }
        }).success(function (data) {
   	   	$scope.models = locals.getObject("models");
   	   	$scope.username= locals.get("username");
        })
});
app.controller("indexController", function($scope,$http,$window,$location, locals, $timeout, $compile) {
		//显示数据
        $http({
            method:'POST',
            url:'../../BackServlet',
            data:{
            	name:"Cou_Dispaly",
                }
            }).success(function (data) {
            $scope.courses = data;
            })
            
    
    	//返回一行数据
        	$scope.Id;
	        $scope.getCoRow = function(id) {
	        	$scope.Id=id;
	      	  $http({
	                method:'POST',
	                url:'../../BackServlet',
	                data:{
	                	name:"Cou_GetRow",
	                	id:id
	                    }
	                }).success(function (data) {
	              	  $scope.edit_c_info = data[0].intro;
	                  $scope.edit_c_charge = data[0].principal;
	                  $scope.h_id=data[0].id
	                })
	          }
    	
    	//修改
	        $scope.Co_change = function(Id) {
	      	  $http({
	                method:'POST',
	                url:'../../BackServlet',
	                data:{
	                	name:"Co_change",
	                	id:$scope.h_id,
	                	intro:$scope.edit_c_info,
	                	principal:$scope.edit_c_charge
	                    }
	                }).success(function (data) {
	                	 $window.location.reload();
	                })
	          }
	        //查看数据
	        $scope.data_Corow = function(id) {
		      	  $http({
		                method:'POST',
		                url:'../../BackServlet',
		                data:{
		                	id:id,
		                	name:"Cou_data_row",
		                	/*add_course:$scope.Co_dis_info,
		                	add_course_person: $scope.Co_dis_charge*/
		                    }
		                }).success(function (data) {
		              	  	/*$scope.names =data;*/
		                	  $scope.Co_dis_info = data[0].intro;
			                  $scope.Co_dis_charge = data[0].principal;
		                })
		          }
 })
 
 //对课程动态进行操作
 app.controller('dynamicController',function($scope,$http,$window,$location, locals, $timeout, $compile){
     //显示数据
       $http({
           method:'POST',
           url:'../../BackServlet',
           data:{
           	name:"Dyhome",   	
               }
           }).success(function (data) {
           	$scope.dynamics =data;
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
	    
	    
	    
	    $scope.edit=function(id){
	    	 $http({
	             method:'POST',
	             url:'../../BackServlet',
	             data:{
	             	name:"DygetRow",
	             	id:id
	                 }
	             }).success(function (data) {
	           	  $scope.dy_Econtent = data[0].content;
	               $scope.dy_Etime = data[0].time;
	               $scope.h_id=data[0].id
	             })
	    }
		
		 $scope.getRow = function(id) {
		     	$scope.Id=id;
		   	 
		       }
		 
		 $scope.dy_add = function() {
			 $scope.operate= locals.get("username");
	    	$http({
	           method:'POST',
	           url:'../../BackServlet',
	           data:{
	           	name:"dy_add",
	           	content:$scope.adddy,
	           	time: $scope.adddytime,
	           	oper:$scope.operate
	               }
	           }).success(function (data) {
	           	if(data==1){
	           		 $window.location.reload();
	           	}
	           	
	           })
		 }
		 
		 //编辑返回一行数据
		 $scope.getdy_Row = function(Id) {
			 $http({
		            method:'POST',
		            url:'../../BackServlet',
		            data:{
		            	name:"getdy_Row",
		            	/*id:$scope.h_id,
		            	content:$scope.dy_Econtent,
		            	time:$scope.dy_Etime*/
		                }
		            }).success(function (data) {
		            	 $window.location.reload();
		            	 $scope.dy_Econtent=data.content
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
	            	content:$scope.dy_Econtent,
	            	time:$scope.dy_Etime
	                }
	            }).success(function (data) {
	            	 $window.location.reload();
	            })
	      }
	    
	    
	    
	    
	   
 })
 
 
 //课程资源
  app.controller("resourseController", function($scope,$http,$window,$location, locals, $timeout, $compile) {
		  //显示数据
	  	
	      $http({
	          method:'POST',
	          url:'../../BackServlet',
	          data:{
	          	name:"Sohome",
	          	
	              }
	          }).success(function (data) {
	          $scope.resourses =data;
	          
	        
	         /* */
	          })
	         
	          
	       //删除
            $scope.delete_reso = function(id) {
	    	  
        	  $http({
                  method:'POST',
                  url:'../../BackServlet',
                  data:{
                  	name:"Sodelete",
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
		        $scope.getre_Row = function(id) {
		        	$scope.Id=id;
		      	  $http({
		                method:'POST',
		                url:'../../BackServlet',
		                data:{
		                	name:"SogetRow",
		                	id:id
		                    }
		                }).success(function (data) {
		              	  $scope.edit_reso_file = data[0].img;
		                  $scope.edit_reso_name = data[0].name;
		                  $scope.h_id=data[0].id
		                })
		          }
		      //修改
		        $scope.change_reso = function(Id) {
		      	  $http({
		                method:'POST',
		                url:'../../BackServlet',
		                data:{
		                	name:"reso_change",
		                	id:$scope.h_id,
		                	file: $scope.add_reso_file,
		                	finame:$scope.edit_reso_name
		                    }
		                }).success(function (data) {
		                	 $window.location.reload();
		                })
		          }
		        
		        
		      //增加数据
		        $scope.res_add = function() {
		        	$scope.operate= locals.get("username");
			      	  $http({
			                method:'POST',
			                url:'../../BackServlet',
			                data:{
			                	name:"res_add",
			                	img:$scope.add_reso_file,
			                	rename: $scope.add_reso_name,
			                	oper: $scope.operate
			                    }
			                }).success(function (data) {
			              	  	/*$scope.names =data;*/
			                	 $window.location.reload();
			                })
			          }
		        
		        $scope.filechanged = function(file) {
		        $scope.add_reso_file = file.files[0].name;  
		        }
		        
		        
		        //查看数据
		        $scope.datare_row = function(id) {
			      	  $http({
			                method:'POST',
			                url:'../../BackServlet',
			                data:{
			                	id:id,
			                	name:"reso_data_row",
			                	content:$scope.content,
			                	time: $scope.time
			                    }
			                }).success(function (data) {
			              	  	/*$scope.names =data;*/
			                	  $scope.display_reso_file = data[0].img;
				                  $scope.dispaly_reso_name = data[0].name;
			                })
			          }
	 
 
  })
 
 .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider
	        .state('index', {   
	            url: '/index',
	            templateUrl: 'black.html',
	            controller:'blackController',
	        })
            .state('info', {   
                url: '/info',
                templateUrl: 'information.html',
                controller:'indexController',
            })
            .state('dynamic', {   
                url: '/dynamic',
                templateUrl: 'dynamic.html',
                controller:'dynamicController',
            })
            .state('resource', {   
                url: '/resource',
                templateUrl: 'resource.html',
            })
            .state('model',{
            	url:'/model',
            	templateUrl:'model.html',
            	controller:'modelController'
            })
            .state('pmanage',{
            	url:'/pmanage',
            	templateUrl:'pmanage.html',
            	controller:'personController'
            })
            .state('pro',{
            	url:'/pro',
            	templateUrl:'problem.html',
            	controller:'problemController'
            })
      
            $urlRouterProvider.otherwise('/index');
})
 
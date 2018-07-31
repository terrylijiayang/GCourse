/**
 * 
 */

var app=angular.module('myContruction', ['ui.router'])

app.filter('paging',function(){      //paging 过滤器
  return function(lists,start){ 
	  if (!lists) {
          return;
      }
    return lists.slice(start);     //分割
  };
});

 app.controller("introController", function($scope,$http) {
        $http({
            method:'POST',
            url:'../../HomeServlet',
            data:{
            	name:"frontHome",         	
                }
            }).success(function (data) {
            	$scope.introduce = data.intro;
            	$scope.dynamics = data.dynamics;
            	
            	 $scope.dataNum =  $scope.dynamics.length;  //获得数据总个数
            	    $scope.pages = Math.ceil($scope.dataNum/3);//按照每页显示3个数据，得到总页数
            	    $scope.pageNum = [];
            	    for(var i=0;i<$scope.pages;i++){
            	      $scope.pageNum.push(i);
            	    }

            	    $scope.currentPage = 0;//设置当前页是 0
            	    $scope.listsPerPage = 3; //设置每页显示 3 个

            	    $scope.setPage = function(num){// 当点击页码数字时执行的函数
            	      $scope.currentPage = num;//将当前页 设置为 页码数
            	    }
            	    
            	    $scope.prevPage = function(){ //点击上一页执行的函数
            	          if($scope.currentPage > 0){
            	              $scope.currentPage--;
            	          }
            	      }
            	     $scope.nextPage = function(){//点击下一页执行的函数
            	          if ($scope.currentPage < $scope.pages-1){
            	              $scope.currentPage++;
            	          }
            	      }
            })
 })
 
 

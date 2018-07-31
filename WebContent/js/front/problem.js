/**
 * 问题与答疑
 */

var app=angular.module('myProblem', ['ui.router'])

app.controller('problemController',function($scope,$http){
	//获取所有问题信息
	$http({
		method:'POST',
		url:'../../ProblemServlet',
		data:{
			name:"problemsAll",
		}
	}).success(function (data) {
		$scope.allProblems=data;
	})

	$scope.show=function(id){
		$scope.answerId=id;
	}
})
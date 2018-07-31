/**
 * 
 */
var app=angular.module('myProblem', ['ui.router'])
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

app.controller("problemController", function($scope,$http,$window,$location, locals, $timeout, $compile) {

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

	
//删除问题
	$scope.delProblem=function(id){
		var msg = "您真的确定要删除吗？\n\n请确认！"; 
		if (confirm(msg)==true){ 
			$http({
				method:'POST',
				url:'../../ProblemServlet',
				data:{
					name:"delProblem",
					problemId:id,
				}
			}).success(function (data) {
				if(data==1){
					$window.location.reload();
				}else{
					alert("删除失败");
				}
			})
			return true; 
		}else{ 
			return false; 
		} 
	}
//新增操作员
	$scope.addProblem=function(){
		$scope.choose="add";
	}
//编辑用户
	$scope.editProblem=function(id){
		$scope.choose="edit";
		$scope.chooseId=id;
		for(var i=0;i<$scope.allProblems.length;i++){
			if(id==$scope.allProblems[i].id){
				$scope.problem_question=$scope.allProblems[i].name;
				$scope.problem_answer=$scope.allProblems[i].content;
			}
		}
	}
//提交问题
	$scope.sendProblem=function(){
		var msg = "请确认上报信息！"; 
		if (confirm(msg)==true){ 
			if($scope.choose=="add"){
					$http({
						method:'POST',
						url:'../../ProblemServlet',
						data:{
							name:"addProblem",
							questionName:$scope.problem_question,
							content:$scope.problem_answer,
							operatorName:locals.get("username"),
						}
					}).success(function (data) {
							$window.location.reload();//重载页面
					})
			}else if($scope.choose=='edit'){
				$http({
					method:'POST',
					url:'../../ProblemServlet',
					data:{
						name:"eidtProblem",
						id:$scope.chooseId,
						questionName:$scope.problem_question,
						content:$scope.problem_answer,
						operatorName:locals.get("username"),
					}
				}).success(function (data) {
						$window.location.reload();//重载页面
				})
			}
		
		}
	}
	
});
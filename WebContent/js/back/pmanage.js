/**
 * 
 */
var app=angular.module('myPmanage', ['ui.router'])
app.controller("personController", function($scope,$http,$window,$location, locals, $timeout, $compile) {

		$http({
			method:'POST',
			url:'../../UserServlet',
			data:{
				name:"users",
			}
		}).success(function (data) {
			$scope.allUsers=data;
		})

		$http({
			method:'POST',
			url:'../../UserServlet',
			data:{
				name:"role",
			}
		}).success(function (data) {
			console.log(data);
			$scope.roleData=data;//保存角色信息
			$scope.names=[];
			for(var i=0;i<data.length;i++){
				if(data[i].role!=null)
					$scope.names.push(data[i].role);
			}

		})
		$scope.editOperator=function(id){
		$scope.chooseId=id;
		for(var i=0;i<$scope.allUsers.length;i++){
			if(id==$scope.allUsers[i].id){
				$scope.operatorName=$scope.allUsers[i].name;
				$scope.operatorUsername=$scope.allUsers[i].user;
				$scope.selectedName=$scope.allUsers[i].role.role;
			}
		}
	}
//删除操作员
	$scope.delOperator=function(id){
		var msg = "您真的确定要删除吗？\n\n请确认！"; 
		if (confirm(msg)==true){ 
			$http({
				method:'POST',
				url:'../../UserServlet',
				data:{
					name:"delOperator",
					operatorid:id,
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
	$scope.addOperator=function(){
		var msg = "请确认上报信息！"; 
		if (confirm(msg)==true){ 
		if($scope.pmanage_pass==$scope.pmanage_repass){
			var roleid;
			for(var i=0;i<$scope.roleData.length;i++){
				if($scope.roleData[i].role==$scope.selectedName)
					roleid=$scope.roleData[i].id;
			}
			
			$http({
				method:'POST',
				url:'../../UserServlet',
				data:{
					name:"addOperator",
					operatorname:$scope.pmanage_name,
					username:$scope.pmanage_username,
					userpass:$scope.pmanage_pass,
					operatorRoleid:roleid,
				}
			}).success(function (data) {
					$window.location.reload();//重载页面
			})
		}else{
			alert('密码输入有误');
		}
		
		}
	}
//编辑用户
	$scope.edit=function(){
		var msg = "请确认上报信息！"; 
		if (confirm(msg)==true){ 
			var roleid;
			for(var i=0;i<$scope.roleData.length;i++){
				if($scope.roleData[i].role==$scope.selectedName)
					roleid=$scope.roleData[i].id;
			}
			
			$http({
				method:'POST',
				url:'../../UserServlet',
				data:{
					name:"eidtOperator",
					chooseId:$scope.chooseId,
					operatorname:$scope.operatorName,
					username:$scope.operatorUsername,
					operatorRoleid:roleid,
				}
			}).success(function (data) {
					$window.location.reload();//重载页面
			})
		}
	}
	
});
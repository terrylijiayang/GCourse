/**
 * 
 */

var app=angular.module('myModel', ['ui.router'])
app.controller("modelController", function($scope,$http,$window,$location, locals, $timeout, $compile) {
	$scope.tesarry=[];
	//获取角色
	$http({
        method:'POST',
        url:'../../UserServlet',
        data:{
        	name:"role",
            }
        }).success(function (data) {
        	$scope.roles=data;
        })
     //获取模块
     $http({
        method:'POST',
        url:'../../UserServlet',
        data:{
        	name:"model",
            }
        }).success(function (data) {
        	$scope.allModels=data;
        	for(var i=0;i<data.length;i++){
        		if(data[i].name!=null)
        		$scope.tesarry.push(data[i].name);
        	}
        })
    $scope.choseArr=[];//定义数组用于存放前端显示
    var str="";//
   var len= $scope.tesarry.length;//初始化数据長度
    var flag='';//是否点击了全选，是为a
    $scope.x=false;//默认未选中
            
    $scope.all= function (c,v) {//全选
        if(c==true){
            $scope.x=true;
            $scope.choseArr = angular.copy(v);
                             flag='a';
        }else{
            $scope.x=false;
            $scope.choseArr=[];
                            flag='b';
        }
    };
    $scope.chk= function (z,x) {//单选或者多选

        if (x == true) {//选中
           $scope.choseArr.push(z)
            flag='c'
                if($scope.choseArr.length==len){
                    $scope.master=true
                }
        } else {
            $scope.choseArr.splice($scope.choseArr.indexOf(z),1);//取消选中
        }
        	if($scope.choseArr.length==0){
                        $scope.master=false
                    };
                

    };
    $scope.delete1= function () {// 操作CURD
        if($scope.choseArr[0]==""||$scope.choseArr.length==0){//没有选择一个的时候提示
            alert("请至少选中一条数据在操作！")
            return;
        };
        for(var i=0;i<$scope.choseArr.length;i++){
            console.log($scope.choseArr[i]);//遍历选中的id
            $scope.choseArr=[];
            $scope.x=false;//默认未选中
            flag='b';
            $scope.master=false;
        }
    };

    $scope.addRole=function(){
    	$scope.roleName='';
    	
    	$scope.choseArr=[];//置空
    	$scope.x=false;
    	$scope.master=false;
    	
    	$scope.choose='add';
    }
  //查看编辑  
    $scope.eidtRole=function(id){
    	$scope.choose='edit';
    	
    	$scope.chooseId=id;
    	$scope.choseArr=[];
    	
    	$scope.x=false;
    	$scope.master=false;
    	for(var i=0;i<$scope.roles.length;i++){
    		if($scope.roles[i].id==id){
    		  	$scope.roleName=$scope.roles[i].role;
    		  	for(var j=0;j<$scope.roles[i].models.length;j++){
    	    		$scope.choseArr.push($scope.roles[i].models[j].name);
    	            flag='c'
    	                if($scope.choseArr.length==len){
    	                    $scope.master=true;
    	                }
    	    	}
    		}
    	}
    }
    //提交
    $scope.submitModel=function(){
    	var submitArr=[];
    	for(var i=0;i<$scope.choseArr.length;i++){
    		for(var j=0;j<$scope.allModels.length;j++){
    			if($scope.allModels[j].name==$scope.choseArr[i]){
    				submitArr.push($scope.allModels[j].id);
    			}
    		}
    	}
    	if($scope.choose=='add'){
    		 $http({
    		        method:'POST',
    		        url:'../../UserServlet',
    		        data:{
    		        	name:"addModel",
    		        	roleName:$scope.roleName,
    		        	chooseArr:submitArr,
    		            }
    		        }).success(function (data) {	        	
    		        	$window.location.reload();//重载页面
    		     })
    	}else if($scope.choose='edit'){
    		$http({
		        method:'POST',
		        url:'../../UserServlet',
		        data:{
		        	name:"editModel",
		        	chooseId:$scope.chooseId,
		        	roleName:$scope.roleName,
		        	chooseArr:submitArr,
		            }
		        }).success(function (data) {	        	
		        	$window.location.reload();//重载页面
		     })
    	}  		
  
    }
    
    //删除
    $scope.delRole=function(id){
    	var msg = "您真的确定要删除吗？\n\n请确认！"; 
    	 if (confirm(msg)==true){ 
		 	$http({
	        method:'POST',
	        url:'../../UserServlet',
	        data:{
	        	name:"delRole",
	        	roleid:id,
	            }
	        }).success(function (data) {
	        	console.log(data);
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
        
        
})

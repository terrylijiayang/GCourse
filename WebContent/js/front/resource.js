/**
 * 
 */

var app=angular.module('myResource', ['ui.router'])

 app.controller("resourceController", function($scope,$http) {
        $http({
            method:'POST',
            url:'../../HomeServlet',
            data:{
            	name:"frontHome",         	
                }
            }).success(function (data) {
           	$scope.sources = data.sources;
            })
 })
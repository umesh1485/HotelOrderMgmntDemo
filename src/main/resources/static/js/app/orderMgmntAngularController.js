angular.module('TestApp', ['TestApp.controllers', 'datatables']);

angular.module('TestApp.controllers', []).controller('testController', function($scope, $http, DTOptionsBuilder, DTColumnBuilder) {

	$scope.orderMgmntAppApi = "http://localhost:8888/orderMgmnt/hotelOrders/";
	$scope.orderMgmntUsersAppApi = "http://localhost:8888/orderMgmnt/users/1";
	$scope.order={};
	$scope.order.orderBy="";
	$scope.user = {};
	$http.get($scope.orderMgmntUsersAppApi).then(function(response) {
		$scope.user = response.data;
    });
	
    $http.get($scope.orderMgmntAppApi).then(function(response) {
    	
        $scope.orders = response.data;
    });
    
    $scope.vm = {};
    
    $scope.save = function() {
    	$scope.order.orderBy=$scope.user.name;
    	$scope.order.updatedBy=$scope.user.name;
    	if($scope.orderForm.$valid){
	        if ($scope.order.id) {
	            $scope.update($scope.order.id);
	        } else {
	            $http.post($scope.orderMgmntAppApi, $scope.order).success(function(response) {
	                $http.get($scope.orderMgmntAppApi).then(function(response) {
	                    $scope.orders = response.data;
						$scope.order = angular.copy($scope.initial);
	                });
	            });
	        }
    	}else{
    		alert('Please enter all details.')
    	}
    }
    $scope.edit = function(id) {
        $http.get($scope.orderMgmntAppApi + id).then(function(response) {
            $scope.order = response.data
        });
    }
    $scope.trackStatus=function(status) {
    	alert('Current status of order is: '+status);
    }
    $scope.update = function(id) {
        $http.put($scope.orderMgmntAppApi + id, $scope.order).then(function(response) {
            $http.get($scope.orderMgmntAppApi).then(function(response) {
                $scope.orders = response.data;
                $scope.order = angular.copy($scope.initial);
            });
        });
    }

    $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption('order', [0, 'asc']);
});
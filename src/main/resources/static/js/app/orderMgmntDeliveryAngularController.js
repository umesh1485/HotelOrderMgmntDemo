angular.module('TestAppDelivery', ['TestAppDelivery.controllers', 'datatables']);

angular.module('TestAppDelivery.controllers', []).controller('deliveryController', function($scope, $http, DTOptionsBuilder, DTColumnBuilder) {

	$scope.orderMgmntAppApi = "http://localhost:8888/orderMgmnt/hotelOrders/";
	$scope.orderMgmntUsersAppApi = "http://localhost:8888/orderMgmnt/users/3";
	$scope.showEditForm=false;
	$scope.order={};
	$scope.order.orderBy={};
	$scope.user = {};
	$http.get($scope.orderMgmntUsersAppApi).then(function(response) {
		$scope.user = response.data;
        
    });
    $http.get($scope.orderMgmntAppApi).then(function(response) {
        $scope.orders = response.data;
    });
    $scope.vm = {};
    $scope.save = function() {
		
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
    	$scope.showEditForm = true;
	        $http.get($scope.orderMgmntAppApi + id).then(function(response) {
	            $scope.order = response.data
	        });
    	
    }
    $scope.update = function(id) {
    	if($scope.orderForm.$valid){
    		$scope.order.updatedBy = $scope.user.name;
	        $http.put($scope.orderMgmntAppApi + id, $scope.order).then(function(response) {
	        	$scope.showEditForm = false;
	            $http.get($scope.orderMgmntAppApi).then(function(response) {
	                $scope.orders = response.data;
	                $scope.order = angular.copy($scope.initial);
	            });
	        });
    	}else{
    		alert('Please enter all details.')
    	}
    }

    $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption('order', [0, 'asc']);
});
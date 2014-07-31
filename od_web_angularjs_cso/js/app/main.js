var app = angular.module('app', []);

app.controller('DemoCtrl', function($scope) {

	$scope.firstName = "Sven";

    $scope.doit = function (data) {
        console.log("hallo " + $scope.firstName);
    };

});

app.directive('odbind', function($parse) {
	return {
		restrict: 'E'
		,scope: {
			ngModel: '='
		}
		,link: function(scope, element, attrs){
			scope.$watch('ngModel', function(newVal, oldVal) {
				console.log("odbind: ", oldVal, " -> ",  newVal);
				//console.log("dolphin: ", dolphin);
			});
		}
	}
});






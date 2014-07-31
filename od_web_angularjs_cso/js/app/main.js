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
			ngModel: '=',
			pm: '='
		}
		,link: function(scope, element, attrs){
			scope.$watch('ngModel', function(newVal, oldVal) {
				console.log("odbind: ", oldVal, " -> ",  newVal);
				console.log("pm: ", attrs.pm);
				if (globalDolphin) {
					var pm = globalDolphin.getAt(attrs.pm);
					if (pm) {
						var attr = globalDolphin.getAt(attrs.pm).getAt('myAttribute');
						attr.setValue(newVal);
					}
					else {
						console.log("ERROR: cannot find pm: ", attrs.pm)
					}
				}
			});
		}
	}
});






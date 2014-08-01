var app = angular.module('app', []);

app.controller('DemoCtrl', function($scope) {

	$scope.init = function()
	{
		console.log("controller.init");
	};

	$scope.firstName = "Sven";

    $scope.appendLonger = function (data) {
        console.log("hallo " + $scope.firstName);
		var attr = globalDolphin.getAt('myPM').getAt('myAttribute');
		attr.setValue(attr.getValue() + ' longer');
    };


});


app.directive('odbind', function($parse, $timeout) {
	return {
		restrict: 'E'
		,scope: {
			ngModel: '=',
			pm: '=',
			attribute: '='
		}
		,link: function(scope, element, attrs) {
			scope.$watch('ngModel', function(newVal, oldVal) {
				console.log("odbind: ", oldVal, " -> ",  newVal);
				console.log("pm: ", attrs.pm);
				if (globalDolphin) {
					var pm = globalDolphin.getAt(attrs.pm);
					if (pm) {
						var attr = globalDolphin.getAt(attrs.pm).getAt(attrs.attribute);
						if (attr) {
							attr.setValue(newVal);
						}
						else {
							console.log("ERROR: cannot find attribute: ", attrs.attribute, " on pm: ", attrs.pm)
						}
					}
					else {
						console.log("ERROR: cannot find pm: ", attrs.pm)
					}
				}
			});

			// Bind value of attribute to ng-model:
			console.log("3");

			globalDolphin.getAt(attrs.pm).getAt(attrs.attribute).onValueChange(function (event) {
				console.log("dolphin model changed: ", event);
				if (event.newValue === null) {
					console.log("newValue is null");
					return;
				}
				if (event.newValue === event.oldValue) {
					console.log("values are the same");
					return;
				}
				if (event.newValue === scope.ngModel) {
					console.log("dolphin and ng-model are the same");
					return;
				}
				$timeout(function() { // prevent nested apply problem. See https://docs.angularjs.org/error/$rootScope/inprog?p0=$apply
					scope.ngModel = event.newValue;
				}, 0, true);
			});
			console.log("4");
		}
	}
});



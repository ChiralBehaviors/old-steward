/*global angular*/
angular.module('StewardApp.controllers', []).controller('stewardController', function ($scope) {
    $scope.add5Seconds = function () {
                        $scope.$broadcast('timer-add-cd-seconds', 5);
                    };
});
/*globals angular, alert, console*/
angular.module('StewardApp.controllers', []).controller('stewardController', function ($scope) {
    $scope.timerLength = 1500;
    $scope.startTimer = function () {
        $scope.$broadcast('timer-start');
        $scope.timerRunning = true;
    };

    $scope.stopTimer = function () {
        $scope.$broadcast('timer-stop');
        $scope.timerRunning = false;
    };

    $scope.resetTimer = function () {
        $scope.$broadcast('timer-reset');
        $scope.timerRunning = false;
    };

    $scope.fin = function () {
        console.log("FINISHED!");
    };

    $scope.$on('timer-finished', function () {
        console.log('create step here');
    });

    $scope.journies = [{
        "id": 1,
        "name": "My journey"
    }, {
        "id": 2,
        "name": "My other journey"
    }, {
        "id": 3,
        "name": "Don't Stop Believin'"
    }];
    $scope.$watch('selectedJourney', function (newVal, oldVal) {
        console.log("changed!" + newVal);
    })
});

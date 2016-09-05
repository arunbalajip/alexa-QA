/**
 * Created by arunbalajip on 9/4/2016.
 */
(function () {
    'use strict';
    angular
        .module("QA")
        .controller("TestController", TestController);

    TestController.$inject = ["testService"];
    function TestController(testService) {
        var vm = this;
        vm.getTest = getTest;
        vm.q = {};
        vm.score = 0;
        vm.submitScore = submitScore;
        getTest();
        function getTest() {
            testService
                .getTest()
                .then(function (response) {
                    vm.topic = response;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
        function submitScore() {
            calculateScore();
        }
        function calculateScore() {
            angular.forEach(vm.q, function (value, index) {
                if(value === vm.topic.questions[0].answer){
                    vm.score++;
                }
            });
            vm.q={};

        }

    }
})();
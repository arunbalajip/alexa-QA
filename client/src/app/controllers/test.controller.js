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
        getTest();
        console.log("test controller");
        function getTest() {
            testService
                .getTest()
                .then(function (response) {
                    vm.topic = response;
                    console.log(vm.topics);
                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                });
        }

    }
})();
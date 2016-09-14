/**
 * Created by arunbalajip on 9/4/2016.
 */
(function () {
    'use strict';
    angular
        .module("QA")
        .controller("AddController", AddController);

    AddController.$inject = ["testService"];
    function AddController(testService) {
        var vm = this;
        vm.addTopic = addTopic;

        function addTopic() {
            testService

           /* userService
                .getDetails()
                .then(function (response) {
                    vm.userdetails = response;
                    console.log(vm.userdetails);
                })
                .catch(function (error) {
                    console.log(error);
                });*/
        }

    }
})();
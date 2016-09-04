/**
 * Created by arunbalajip on 9/4/2016.
 */
(function () {
    'use strict';
    angular
        .module("QA")
        .controller("ToolbarController", ToolbarController);

    ToolbarController.$inject = ["testService"];
    function ToolbarController(testService) {
        var vm = this;
        getAllTopic();
        function getAllTopic() {
            testService
                .getAllTest()
                .then(function (response) {
                    vm.topics = response;

                })
                .catch(function (error) {
                    console.log(error);
                });
        }


    }
})();
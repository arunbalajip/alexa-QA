/**
 * Created by arunbalajip on 9/4/2016.
 */
(function () {
    'use strict';
    angular
        .module("QA")
        .controller("ScoreController", ScoreController);

    ScoreController.$inject = ["userService"];
    function ScoreController(userService) {
        var vm = this;
        vm.getDetails = getDetails;
        getDetails();
        function getDetails() {
            userService
                .getDetails()
                .then(function (response) {
                    vm.userdetails = response;
                    console.log(vm.userdetails);
                })
                .catch(function (error) {
                    console.log(error);
                });
        }

    }
})();
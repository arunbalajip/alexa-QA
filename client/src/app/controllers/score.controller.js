/**
 * Created by arunbalajip on 9/4/2016.
 */
(function () {
    'use strict';
    angular
        .module("QA")
        .controller("ScoreController", ScoreController);

    ScoreController.$inject = ["testService"];
    function ScoreController(testService) {
        var vm = this;
        vm.getAllTopic = getAllTopic;
        getTest();
        console.log("ScoreController controller");

    }
})();
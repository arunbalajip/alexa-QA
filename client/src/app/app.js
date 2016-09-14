(function () {
    'use strict';

    angular
        .module("QA", ['ngRoute','ui.filters'])
        .config(moduleConfig);

    moduleConfig.$inject = ['$routeProvider'];
    function moduleConfig($routeProvider){
        $routeProvider
            .when("/test", {
                templateUrl: "app/views/test.tmpl.html",
                controller: "TestController",
                controllerAs: "testVm"
            })
            .when("/score", {
                templateUrl: "app/views/scoresheet.tmpl.html",
                controller: "ScoreController",
                controllerAs: "scoreVm"
            })
            .when("/addtopic", {
                templateUrl: "app/views/addtopic.tmpl.html",
                controller: "AddController",
                controllerAs: "addVm"
            })
            .when("/addquestion", {
                templateUrl: "app/views/addquestion.tmpl.html",
                controller: "addController",
                controllerAs: "addVm"
            })
            .otherwise({
                templateUrl: "app/views/home.tmpl.html"
            });
    }

})();
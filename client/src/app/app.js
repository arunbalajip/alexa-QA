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
            .otherwise({
                templateUrl: "app/views/home.tmpl.html"
            });
    }

})();
(function () {
    'use strict';

    angular
        .module("QA", ['ngRoute'])
        .config(moduleConfig);

    moduleConfig.$inject = ['$routeProvider'];
    function moduleConfig($routeProvider){
        $routeProvider
            .when("/test", {
                templateUrl: "app/views/test.tmpl.html",
                controller: "TestController",
                controllerAs: "testVm"
            })
            .otherwise({
                templateUrl: "app/views/home.tmpl.html"
            });
    }

})();
/**
 * Created by arunbalajip on 9/4/2016.
 */
(function () {
    angular.module("QA")
        .service("testService", testService);

    testService.$inject = ["$http", "$q"];
    function testService($http, $q) {
        var vm = this;
        vm.getTest = getTest;
        function getTest() {
            return $http
                .get("http://n2sglobal.vc7nqzja6p.us-west-2.elasticbeanstalk.com/api/topic/"+"296cf830-8aaf-49f1-a479-e5267a305651")
                .then(success, error);
        }

        function success(response) {
            return response.data;
        }

        function error() {
            return $q.reject(error.status);
        }
    }
})();
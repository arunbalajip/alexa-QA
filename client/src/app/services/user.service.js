/**
 * Created by arunbalajip on 9/4/2016.
 */
(function () {
    angular.module("QA")
        .service("userService", userService);

    userService.$inject = ["$http", "$q"];
    function userService($http, $q) {
        var vm = this;
        vm.getDetails = getDetails;
        function getDetails() {
            return $http
                .get("http://n2sglobal.vc7nqzja6p.us-west-2.elasticbeanstalk.com/api/user/")
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
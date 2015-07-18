angular.module('URLShortener')
  .controller('HomeController', ['$scope', '$http', function ($scope, $http) {

    $scope.resetAll = function() {
									$scope.longURL = "";
									$scope.shortURL = "";
								};

  $scope.shortU = function() {
  									return "http://localhost:8080/convertShortUrl?longUrl="
  											+ $scope.longURL;
  								}

  								$scope.short = function() {

  									if ($scope.longURL.substring(0, 4)
  											.localeCompare("www.") == '0'
  											|| $scope.longURL.substring(0, 7)
  													.localeCompare("http://") == '0'
  											|| $scope.longURL.substring(0, 8)
  													.localeCompare("https://") == '0'
  											&& $scope.longURL.indexOf(" ") == '-1') {
  										$http
  												.get($scope.shortU())
  												.success(
  														function(risposta) {
  															$scope.ss = risposta.responseData;
  															if ($scope.ss.error == "okay") {
  																$scope.nuovo = $scope.ss.shortUrl;
  																$scope.risp = "Your short URL:  "
  																		+ $scope.nuovo;
  															}
  														});

  									} else {

  										$scope.risp = "Invalid long URL!";
  									}
  									$scope.resetAll();
  								}

  }]);

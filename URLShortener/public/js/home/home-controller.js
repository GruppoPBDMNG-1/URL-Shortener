angular.module('URLShortener')
  .controller('HomeController', ['$scope', '$http', function ($scope, $http) {

   $scope.resetUrl = function() {
   									$scope.longURL = "";
   									$scope.shortURL = "";
   								};

   								$scope.resetAlarm = function(){
   									$scope.error = "";
   									$scope.error2 = "";
   								}

   								$scope.generate = function() {
   									return "http://localhost:8080/convertToShortUrl?longUrl="
   											+ $scope.longURL;
   								}

   								$scope.genShort = function() {

   									$scope.risp="";
   									$scope.shortU = "";
   									$scope.link ="";
                                       if($scope.longURL==null){
                                       $scope.risp = "Empty long URL";
                                       }else{
   									if ( $scope.longURL.substring(0, 4)
   											.localeCompare("www.") == '0'
   											|| $scope.longURL.substring(0, 7)
   													.localeCompare("http://") == '0'
   											|| $scope.longURL.substring(0, 8)
   													.localeCompare("https://") == '0'
   											&& $scope.longURL.indexOf(" ") == '-1') {
   										$http
   												.get($scope.generate())
   												.success(
   														function(risposta) {
   															$scope.ss = risposta.responseData;
   															if ($scope.ss.result == "okay") {
   																$scope.nuovo = $scope.ss.shortUrl;
   																$scope.shortU = "Your short URL:  ";
   																$scope.link = $scope.nuovo;

   															}else{
   															    $scope.risp = $scope.ss.result;
   															}
   														});

   									} else {

   										$scope.risp = "Invalid long URL";
   									}
                                   }
   									$scope.resetUrl();
   								}
  }]);

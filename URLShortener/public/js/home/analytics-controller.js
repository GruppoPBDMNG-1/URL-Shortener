angular.module('URLShortener')
 .controller('AnalyticsController', ['$scope', '$http', '$window', function ($scope, $http, $window) {

//sample data
//richiamare la funzione getGraph
$scope.graphf = function(){
return "http://localhost:8080/getGraph";
}

$scope.prova= function() {
 $http
                                                .get($scope.graphf())
                                                .success(
                                                        function(risposta) {
                                                            $scope.nom = risposta.responseData;
                                                            if ($scope.nom.result == "okay") {
                                                            $scope.graph = $scope.nom.graph;


           var days =  [{
               period: '2010 Q2',    //es. var days= [{$scope.graph[0].data, $scope.graph[0].numeroclick}]
               iphone: 2778
           }, {
               period: '2010 Q3',
               iphone: 4912

           }, {
               period: '2010 Q4',
               iphone: 3767

           }, {
               period: '2011 Q1',
               iphone: 6810

           }, {
               period: '2011 Q2',
               iphone: 5670

           }, {
               period: '2011 Q3',
               iphone: 4820

           }, {
               period: '2011 Q4',
               iphone: 15073

           }, {
               period: '2012 Q1',
               iphone: 10687

           }, {
               period: '2012 Q2',
               iphone: 8432

           }];

           Morris.Area({
           element: 'morris-area-chart',
           data: days,
           xkey: 'period',
           ykeys: ['iphone'],
           labels: ['iPhone'],
           pointSize: 2,
           hideHover: 'auto',
           resize: true
       });

    } });


};


  }]);

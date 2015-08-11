    angular.module('URLShortener')
      .controller('HomeController', ['$scope', '$http', '$window', function ($scope, $http, $window) {

       $scope.resetUrl = function() {
                                        $scope.longURL = "";
                                        $scope.shortURL = "";
                                    };

                                    $scope.resetAlarm = function(){
                                        $scope.error = "";
                                        $scope.error2 = "";
                                        $scope.error3 = "";
                                    };

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

                                    $scope.sa = function() {
                                                                        return "http://localhost:8080/saveShort?shortUrl="
                                                                                + $scope.shortURL
                                                                                + "&longUrl="
                                                                                + $scope.longURL;
                                                                    }

                                                                    $scope.save = function() {
                                                                        $scope.ris="";
                                                                        $scope.detail = "";
                                                                        $scope.linkCustom ="";
                                                                        if($scope.longURL==null && $scope.shortURL==null){
                                                                         $scope.ris="Empty long URL and short URL";
                                                                        }else{
                                                                        if($scope.longURL==null){
                                                                            $scope.ris="Empty long URL";
                                                                        }else{
                                                                        if($scope.shortURL==null || $scope.shortURL.localeCompare(" ")=='0'){
                                                                            $scope.ris="Empty short URL";
                                                                        }else{
                                                                        if ($scope.longURL.substring(0, 4)
                                                                                .localeCompare("www.") == '0'
                                                                                || $scope.longURL.substring(0, 7)
                                                                                        .localeCompare("http://") == '0'
                                                                                || $scope.longURL.substring(0, 8)
                                                                                        .localeCompare("https://") == '0' ) {
                                                                            $http
                                                                                .get($scope.sa())
                                                                                .success(
                                                                                        function(response) {
                                                                                            $scope.res = response.responseData;
                                                                                            if ($scope.res.result != "okay") {
                                                                                                $scope.ris = $scope.res.result;
                                                                                            } else {
                                                                                                $scope.detail = "Your custom short URL: ";
                                                                                                $scope.linkCustom = $scope.shortURL;

                                                                                            }
                                                                                        });

                                                                    }else{
                                                                        $scope.ris="Invalid long URL";
                                                                    }

                                                                    }
                                                                    }
                                                                    }
                                                                }

                                                                    $scope.go = function() {
                                                                        return "http://localhost:8080/viewWindow?shortUrl="
                                                                                + $scope.getUrl;
                                                                    }

                                                                            <!-- funzione chiamata generate short URL -->
                                                                    $scope.goLong = function() {
                                                                        $scope.getUrl = $scope.link;
                                                                        $scope.name="";
                                                                        $scope.resetAlarm();
                                                                        $http
                                                                                .get($scope.go())
                                                                                .success(
                                                                                        function(response) {
                                                                                            $scope.aggiungi = response.responseData;
                                                                                            if ($scope.aggiungi.result == "okay") {
                                                                                                $window
                                                                                                        .open($scope.aggiungi.longUrl);
                                                                                            }
                                                                                        });
                                                                        $scope.resetUrl();
                                                                }


                                                                    $scope.goCustomLong = function() {
                                                                        $scope.getUrl=$scope.linkCustom;
                                                                        $scope.resetAlarm();
                                                                        $http
                                                                                .get($scope.go())
                                                                                .success(
                                                                                        function(response) {
                                                                                            $scope.aggiungi = response.responseData;
                                                                                            if ($scope.aggiungi.result == "okay") {
                                                                                                $window
                                                                                                        .open($scope.aggiungi.longUrl);
                                                                                            }
                                                                                        });
                                                                        $scope.resetUrl();
                                                                }

                                                                 <!-- funzione chiamata da view page -->
                                                                                                    $scope.goweb = function() {
                                                                                                        $scope.name="";
                                                                                                        $scope.resetAlarm();

                                                                                                        if ($scope.shortUrl == null || $scope.shortUrl.localeCompare(" ")!= '0')
                                                                                                        {
                                                                                                            $scope.getUrl= $scope.shortUrl;
                                                                                                        $http
                                                                                                                .get($scope.go())
                                                                                                                .success(
                                                                                                                        function(response) {
                                                                                                                            $scope.aggiungi = response.responseData;
                                                                                                                            if ($scope.aggiungi.result == "okay") {
                                                                                                                                $window
                                                                                                                                        .open($scope.aggiungi.longUrl);
                                                                                                                                $scope.name = "Redirect to "
                                                                                                                                        + $scope.aggiungi.longUrl
                                                                                                                            } else {
                                                                                                                                $scope.error2 = $scope.aggiungi.result;
                                                                                                                            }
                                                                                                                        });
                                                                                                    }
                                                                                                    else{
                                                                                                        $scope.name="Empty short URL";
                                                                                                    }
                                                                                                }

                                                                                                $scope.vGraph = function() {
                                                                                                                                    return "http://localhost:8080/getGraphPage?shortUrl="
                                                                                                                                            + $scope.shortUrl;
                                                                                                                                }

    $scope.viewPageGraph = function() {
     if($scope.shortUrl==null){
                                            $scope.error3="Empty short URL";
                                        }else{

                                        $scope.resetAlarm();
                                            $http
                                                .get($scope.vGraph())
                                                .success(
                                                        function(risposta) {
                                                            $scope.nom = risposta.responseData;
                                                            if ($scope.nom.result == "okay") {

                                                            $window.open("/#/analytics");
                                                            /*Morris.Area({
                                                                   element: 'morris-area-chart',
                                                                   data: [{
                                                                       period: '2010 Q1',
                                                                       iphone: 2666,
                                                                       ipad: null,
                                                                       itouch: 2647
                                                                   }, {
                                                                       period: '2010 Q2',
                                                                       iphone: 2778,
                                                                       ipad: 2294,
                                                                       itouch: 2441
                                                                   }, {
                                                                       period: '2010 Q3',
                                                                       iphone: 4912,
                                                                       ipad: 1969,
                                                                       itouch: 2501
                                                                   }, {
                                                                       period: '2010 Q4',
                                                                       iphone: 3767,
                                                                       ipad: 3597,
                                                                       itouch: 5689
                                                                   }, {
                                                                       period: '2011 Q1',
                                                                       iphone: 6810,
                                                                       ipad: 1914,
                                                                       itouch: 2293
                                                                   }, {
                                                                       period: '2011 Q2',
                                                                       iphone: 5670,
                                                                       ipad: 4293,
                                                                       itouch: 1881
                                                                   }, {
                                                                       period: '2011 Q3',
                                                                       iphone: 4820,
                                                                       ipad: 3795,
                                                                       itouch: 1588
                                                                   }, {
                                                                       period: '2011 Q4',
                                                                       iphone: 15073,
                                                                       ipad: 5967,
                                                                       itouch: 5175
                                                                   }, {
                                                                       period: '2012 Q1',
                                                                       iphone: 10687,
                                                                       ipad: 4460,
                                                                       itouch: 2028
                                                                   }, {
                                                                       period: '2012 Q2',
                                                                       iphone: 8432,
                                                                       ipad: 5713,
                                                                       itouch: 1791
                                                                   }],
                                                                   xkey: 'period',
                                                                   ykeys: ['iphone', 'ipad', 'itouch'],
                                                                   labels: ['iPhone', 'iPad', 'iPod Touch'],
                                                                   pointSize: 2,
                                                                   hideHover: 'auto',
                                                                   resize: true
                                                               });*/

                                                                }else{
                                                                $scope.error3 = $scope.nom.result;
                                                                }

                                                                });
                                                                }
                                                                }


      }]);

app.factory('EmpresasService', function ($q, $timeout) {

    return {
        empresas_json: null,

        empresas: function() {
            if(typeof empresas_json === 'undefined') {
               $http({
                  method: 'GET',
                  url: 'http://localhost:8080/index'
                }).then(function successCallback(response) {
                    console.log(response);
                    empresas_json = response.data;
                }, function errorCallback(response) {
                    console.log("Could not get riskIndex list from service: " + response);

                    // TODO: add fall safe risk index list
                    empresas_json = [{
                        "companyId": 1,
                        "companyName": "aeros",
                        "riskIndex": 4.3
                    }, {
                        "companyId": 2,
                        "companyName": "bombardier",
                        "riskIndex": 1.3
                    }, {
                        "companyId": 3,
                        "companyName": "medicare",
                        "riskIndex": 2.5
                    }, {
                        "companyId": 5,
                        "companyName": "bmer",
                        "riskIndex": 3.9
                    }];
                });
            };

            //ESTABLECE BADGES
            for (var i = 0; i < empresas_json.length; i++) {
                var emp = empresas_json[i];
                empresas_json[i].companyName = empresas_json[i].companyName.toUpperCase();
                if (emp.riskIndex >= 0 && emp.riskIndex <= 2.5) {
                    empresas_json[i].companyIdx = 'badge-verde';
                } else {
                    empresas_json[i].companyIdx = 'badge-rojo';
                }
            }

            return empresas_json;
        },

        consulta: function (txt) {
            var emp = empresas.filter(function (x) {
                if (x.companyName.toLowerCase().indexOf(txt.toLowerCase()) !== -1)
                    return true;
                return false;
            });
            var deferred = $q.defer();
            $timeout(function () {
                deferred.resolve(emp);
            }, 100);
            return deferred.promise;
        }
    };

});

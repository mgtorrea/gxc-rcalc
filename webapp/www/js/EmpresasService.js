app.factory('EmpresasService', function ($q, $timeout, $http) {

    return {
        empresas_json: null,

        getEmpresas: function () {
            var empresas_json = this.empresas_json;
            var deferred = $q.defer();
            if (empresas_json === null) {
                $http({
                    method: 'GET',
                    url: 'http://localhost:8080/index'
                }).then(function successCallback(response) {
                    empresas_json = response.data;
                    deferred.resolve(estableceBadges(empresas_json));
                }, function errorCallback(response) {
                    console.log(["Could not get riskIndex list from service: " , response]);

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
                    deferred.resolve(estableceBadges(empresas_json));
                });
            }else{
                deferred.resolve(estableceBadges(empresas_json));
            }

            function estableceBadges(empresas_json) {
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
            }
            return deferred.promise;
        },

        consulta: function (txt) {
            var deferred = $q.defer();
            this.getEmpresas().then(function (empresas) {
                var emp =empresas.filter(function (x) {
                    if (x.companyName.toLowerCase().indexOf(txt.toLowerCase()) !== -1)
                        return true;
                    return false;
                });
                $timeout(function () {
                    deferred.resolve(emp);
                }, 100);
            });
            return deferred.promise;
        }
    };

});

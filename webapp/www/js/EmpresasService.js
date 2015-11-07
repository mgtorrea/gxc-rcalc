var empresas = [{
    "id": 1,
    "name": "aeros",
    "riskIndex": 4.3
}, {
    "id": 2,
    "name": "bombardier",
    "riskIndex": 1.3
}, {
    "id": 3,
    "name": "medicare",
    "riskIndex": 2.5
}, {
    "id": 5,
    "name": "bmer",
    "riskIndex": 3.9
}];

//ESTABLECE BADGES
for (var i = 0; i < empresas.length; i++) {
    var emp = empresas[i];
    empresas[i].name=empresas[i].name.toUpperCase();
    if (emp.riskIndex >= 0 && emp.riskIndex <= 2.5) {
        empresas[i].idx = 'badge-verde';
    } else {
        empresas[i].idx = 'badge-rojo';
    }
}

app.factory('EmpresasService', function ($q, $timeout) {

    return {
        consulta: function (txt) {
            var emp = empresas.filter(function (x) {
                if (x.name.toLowerCase().indexOf(txt.toLowerCase()) !== -1)
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

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
                    console.log(["Could not get riskIndex list from service: ", response]);

                    // TODO: add fall safe risk index list
                    empresas_json = [{
                        "companyId": 1,
                        "companyName": "Banamex",
                        "riskIndex": 2.3333333,
                        "date": 1447301877379
                    }, {
                        "companyId": 2,
                        "companyName": "TELCEL",
                        "riskIndex": 2.1,
                        "date": 1447301877384
                    }, {
                        "companyId": 3,
                        "companyName": "Megacable",
                        "riskIndex": 2.2666667,
                        "date": 1447301877385
                    }, {
                        "companyId": 4,
                        "companyName": "Telmex",
                        "riskIndex": 1.9,
                        "date": 1447301877385
                    }, {
                        "companyId": 5,
                        "companyName": "Izzi Telecom",
                        "riskIndex": 1.1,
                        "date": 1447301877390
                    }, {
                        "companyId": 6,
                        "companyName": "Mercado Libre",
                        "riskIndex": 3.2666667,
                        "date": 1447301877391
                    }, {
                        "companyId": 7,
                        "companyName": "Bancomer",
                        "riskIndex": 1.4333333,
                        "date": 1447301877392
                    }, {
                        "companyId": 8,
                        "companyName": "Linio.com.mx",
                        "riskIndex": 1.2333333,
                        "date": 1447301877392
                    }, {
                        "companyId": 9,
                        "companyName": "IUSACELL",
                        "riskIndex": 1.3,
                        "date": 1447301877393
                    }, {
                        "companyId": 10,
                        "companyName": "Movistar",
                        "riskIndex": 1.2666667,
                        "date": 1447301877393
                    }, {
                        "companyId": 11,
                        "companyName": "Totalplay",
                        "riskIndex": 1.2,
                        "date": 1447301877394
                    }, {
                        "companyId": 12,
                        "companyName": "Estafeta",
                        "riskIndex": 1.8,
                        "date": 1447301877394
                    }, {
                        "companyId": 13,
                        "companyName": "Santander",
                        "riskIndex": 1.6666666,
                        "date": 1447301877396
                    }, {
                        "companyId": 14,
                        "companyName": "Walmart",
                        "riskIndex": 2.4,
                        "date": 1447301877396
                    }, {
                        "companyId": 15,
                        "companyName": "OXXO",
                        "riskIndex": 2.2666667,
                        "date": 1447301877397
                    }, {
                        "companyId": 16,
                        "companyName": "FedEx",
                        "riskIndex": 1.0666667,
                        "date": 1447301877397
                    }, {
                        "companyId": 17,
                        "companyName": "VivaAerobus",
                        "riskIndex": 0.96666664,
                        "date": 1447301877397
                    }, {
                        "companyId": 18,
                        "companyName": "Volaris",
                        "riskIndex": 1.3333334,
                        "date": 1447301877398
                    }, {
                        "companyId": 19,
                        "companyName": "Axtel",
                        "riskIndex": 1.6,
                        "date": 1447301877398
                    }, {
                        "companyId": 20,
                        "companyName": "Dish",
                        "riskIndex": 1.3,
                        "date": 1447301877398
                    }, {
                        "companyId": 21,
                        "companyName": "Cablevisión",
                        "riskIndex": 1.4,
                        "date": 1447301877398
                    }, {
                        "companyId": 22,
                        "companyName": "Liverpool",
                        "riskIndex": 2.1,
                        "date": 1447301877399
                    }, {
                        "companyId": 23,
                        "companyName": "Nissan",
                        "riskIndex": 1.6666666,
                        "date": 1447301877399
                    }, {
                        "companyId": 24,
                        "companyName": "D'Europe Muebles",
                        "riskIndex": 1.1,
                        "date": 1447301877399
                    }, {
                        "companyId": 25,
                        "companyName": "Aeroméxico",
                        "riskIndex": 2.0666666,
                        "date": 1447301877399
                    }, {
                        "companyId": 26,
                        "companyName": "Chevrolet",
                        "riskIndex": 1.4333333,
                        "date": 1447301877400
                    }, {
                        "companyId": 27,
                        "companyName": "Ingenia Muebles",
                        "riskIndex": 1.6,
                        "date": 1447301877400
                    }, {
                        "companyId": 28,
                        "companyName": "Nextel",
                        "riskIndex": 1.5666667,
                        "date": 1447301877400
                    }, {
                        "companyId": 29,
                        "companyName": "Banco Azteca",
                        "riskIndex": 2.2333333,
                        "date": 1447301877401
                    }, {
                        "companyId": 30,
                        "companyName": "DHL",
                        "riskIndex": 1.4666667,
                        "date": 1447301877401
                    }, {
                        "companyId": 31,
                        "companyName": "Coppel",
                        "riskIndex": 2.4333334,
                        "date": 1447301877402
                    }, {
                        "companyId": 32,
                        "companyName": "Banorte",
                        "riskIndex": 1.6666666,
                        "date": 1447301877402
                    }, {
                        "companyId": 33,
                        "companyName": "Volkswagen",
                        "riskIndex": 1.8,
                        "date": 1447301877402
                    }, {
                        "companyId": 34,
                        "companyName": "Telecable",
                        "riskIndex": 1.0666667,
                        "date": 1447301877402
                    }, {
                        "companyId": 35,
                        "companyName": "Privalia",
                        "riskIndex": 1.6666666,
                        "date": 1447301877403
                    }, {
                        "companyId": 36,
                        "companyName": "Cablemás",
                        "riskIndex": 1.0,
                        "date": 1447301877403
                    }, {
                        "companyId": 37,
                        "companyName": "IMSS",
                        "riskIndex": 2.1,
                        "date": 1447301877403
                    }, {
                        "companyId": 38,
                        "companyName": "Toyota",
                        "riskIndex": 1.4,
                        "date": 1447301877403
                    }, {
                        "companyId": 39,
                        "companyName": "Celulares Compramerica",
                        "riskIndex": 2.9333334,
                        "date": 1447301877404
                    }, {
                        "companyId": 40,
                        "companyName": "Muebles Dico",
                        "riskIndex": 2.0,
                        "date": 1447301877404
                    }, {
                        "companyId": 41,
                        "companyName": "CFE",
                        "riskIndex": 2.0,
                        "date": 1447301877404
                    }, {
                        "companyId": 42,
                        "companyName": "Domino's Pizza",
                        "riskIndex": 2.6333334,
                        "date": 1447301877404
                    }, {
                        "companyId": 43,
                        "companyName": "Soriana",
                        "riskIndex": 2.6,
                        "date": 1447301877404
                    }, {
                        "companyId": 44,
                        "companyName": "HSBC",
                        "riskIndex": 1.7,
                        "date": 1447301877405
                    }, {
                        "companyId": 45,
                        "companyName": "Best Buy",
                        "riskIndex": 1.6666666,
                        "date": 1447301877405
                    }, {
                        "companyId": 46,
                        "companyName": "Interjet",
                        "riskIndex": 1.5,
                        "date": 1447301877405
                    }, {
                        "companyId": 47,
                        "companyName": "KFC",
                        "riskIndex": 2.9333334,
                        "date": 1447301877414
                    }, {
                        "companyId": 48,
                        "companyName": "Royal Prestige",
                        "riskIndex": 1.5333333,
                        "date": 1447301877416
                    }, {
                        "companyId": 49,
                        "companyName": "Gas Natural",
                        "riskIndex": 1.8666667,
                        "date": 1447301877417
                    }, {
                        "companyId": 50,
                        "companyName": "Groupon",
                        "riskIndex": 1.1,
                        "date": 1447301877417
                    }, {
                        "companyId": 51,
                        "companyName": "Pizza Hut",
                        "riskIndex": 2.2333333,
                        "date": 1447301877417
                    }, {
                        "companyId": 52,
                        "companyName": "Cinépolis",
                        "riskIndex": 2.9333334,
                        "date": 1447301877417
                    }, {
                        "companyId": 53,
                        "companyName": "Sears",
                        "riskIndex": 1.7,
                        "date": 1447301877417
                    }, {
                        "companyId": 54,
                        "companyName": "Comercial Mexicana",
                        "riskIndex": 2.6666667,
                        "date": 1447301877418
                    }, {
                        "companyId": 55,
                        "companyName": "Ford",
                        "riskIndex": 2.1333334,
                        "date": 1447301877418
                    }, {
                        "companyId": 56,
                        "companyName": "Bodega Aurrerá",
                        "riskIndex": 2.4333334,
                        "date": 1447301877418
                    }, {
                        "companyId": 57,
                        "companyName": "ETN",
                        "riskIndex": 2.0,
                        "date": 1447301877419
                    }, {
                        "companyId": 58,
                        "companyName": "Renault",
                        "riskIndex": 2.1666667,
                        "date": 1447301877419
                    }, {
                        "companyId": 59,
                        "companyName": "Costco",
                        "riskIndex": 1.7333333,
                        "date": 1447301877419
                    }, {
                        "companyId": 60,
                        "companyName": "Sanborns",
                        "riskIndex": 2.5,
                        "date": 1447301877419
                    }, {
                        "companyId": 61,
                        "companyName": "Honda",
                        "riskIndex": 2.0,
                        "date": 1447301877419
                    }, {
                        "companyId": 62,
                        "companyName": "Superama",
                        "riskIndex": 2.4333334,
                        "date": 1447301877420
                    }, {
                        "companyId": 63,
                        "companyName": "Farmacias Guadalajara",
                        "riskIndex": 2.2,
                        "date": 1447301877420
                    }, {
                        "companyId": 64,
                        "companyName": "Quálitas",
                        "riskIndex": 2.2,
                        "date": 1447301877420
                    }, {
                        "companyId": 65,
                        "companyName": "SKY",
                        "riskIndex": 1.0,
                        "date": 1447301877420
                    }, {
                        "companyId": 66,
                        "companyName": "C&A",
                        "riskIndex": 1.9666667,
                        "date": 1447301877420
                    }, {
                        "companyId": 67,
                        "companyName": "Inbursa",
                        "riskIndex": 1.2666667,
                        "date": 1447301877421
                    }, {
                        "companyId": 68,
                        "companyName": "TicketBis",
                        "riskIndex": 1.6666666,
                        "date": 1447301877422
                    }, {
                        "companyId": 69,
                        "companyName": "Rentas Particulares",
                        "riskIndex": 2.375,
                        "date": 1447301877422
                    }, {
                        "companyId": 70,
                        "companyName": "GNP Seguros",
                        "riskIndex": 1.6,
                        "date": 1447301877422
                    }, {
                        "companyId": 71,
                        "companyName": "Samsung",
                        "riskIndex": 2.7333333,
                        "date": 1447301877422
                    }, {
                        "companyId": 72,
                        "companyName": "Suempresa.com",
                        "riskIndex": 1.7333333,
                        "date": 1447301877423
                    }, {
                        "companyId": 73,
                        "companyName": "Marke",
                        "riskIndex": 1.1333333,
                        "date": 1447301877423
                    }, {
                        "companyId": 74,
                        "companyName": "Sam's Club",
                        "riskIndex": 1.6,
                        "date": 1447301877424
                    }, {
                        "companyId": 75,
                        "companyName": "FAMSA",
                        "riskIndex": 1.8666667,
                        "date": 1447301877424
                    }, {
                        "companyId": 76,
                        "companyName": "Despegar.com",
                        "riskIndex": 0.7,
                        "date": 1447301877425
                    }, {
                        "companyId": 77,
                        "companyName": "Fundación Red Azul",
                        "riskIndex": 2.0666666,
                        "date": 1447301877425
                    }, {
                        "companyId": 78,
                        "companyName": "Cinemex",
                        "riskIndex": 2.4,
                        "date": 1447301877425
                    }, {
                        "companyId": 79,
                        "companyName": "Chedraui",
                        "riskIndex": 2.7333333,
                        "date": 1447301877426
                    }, {
                        "companyId": 80,
                        "companyName": "Farmacias del Ahorro",
                        "riskIndex": 2.4333334,
                        "date": 1447301877426
                    }, {
                        "companyId": 81,
                        "companyName": "Universidad del Valle de México",
                        "riskIndex": 2.7333333,
                        "date": 1447301877426
                    }, {
                        "companyId": 82,
                        "companyName": "HP",
                        "riskIndex": 2.3333333,
                        "date": 1447301877427
                    }, {
                        "companyId": 83,
                        "companyName": "Banco Walmart",
                        "riskIndex": 1.9666667,
                        "date": 1447301877427
                    }, {
                        "companyId": 84,
                        "companyName": "Cablecom",
                        "riskIndex": 1.0666667,
                        "date": 1447301877427
                    }, {
                        "companyId": 85,
                        "companyName": "Expedia",
                        "riskIndex": 1.625,
                        "date": 1447301877427
                    }, {
                        "companyId": 86,
                        "companyName": "Sport City",
                        "riskIndex": 2.8,
                        "date": 1447301877428
                    }, {
                        "companyId": 87,
                        "companyName": "INNOVA",
                        "riskIndex": 1.5,
                        "date": 1447301877428
                    }, {
                        "companyId": 88,
                        "companyName": "Uber Technologies",
                        "riskIndex": 2.3636363,
                        "date": 1447301877428
                    }, {
                        "companyId": 89,
                        "companyName": "Scotiabank",
                        "riskIndex": 3.1,
                        "date": 1447301877428
                    }, {
                        "companyId": 90,
                        "companyName": "Bimbo",
                        "riskIndex": 2.8,
                        "date": 1447301877428
                    }, {
                        "companyId": 91,
                        "companyName": "Profuturo GNP",
                        "riskIndex": 1.75,
                        "date": 1447301877428
                    }, {
                        "companyId": 92,
                        "companyName": "Resuelve Tu Deuda",
                        "riskIndex": 2.7037036,
                        "date": 1447301877429
                    }, {
                        "companyId": 93,
                        "companyName": "Teté Mueble Infantil",
                        "riskIndex": 2.1666667,
                        "date": 1447301877429
                    }, {
                        "companyId": 94,
                        "companyName": "Marcas de Renombre",
                        "riskIndex": 1.2666667,
                        "date": 1447301877429
                    }, {
                        "companyId": 95,
                        "companyName": "Maskota",
                        "riskIndex": 2.6333334,
                        "date": 1447301877429
                    }, {
                        "companyId": 96,
                        "companyName": "The Home Depot",
                        "riskIndex": 2.3666666,
                        "date": 1447301877430
                    }, {
                        "companyId": 97,
                        "companyName": "Transportes Castores",
                        "riskIndex": 1.2068965,
                        "date": 1447301877430
                    }, {
                        "companyId": 98,
                        "companyName": "Apestan.com",
                        "riskIndex": 2.7666667,
                        "date": 1447301877430
                    }, {
                        "companyId": 99,
                        "companyName": "OfficeMax",
                        "riskIndex": 2.5,
                        "date": 1447301877430
                    }, {
                        "companyId": 100,
                        "companyName": "Unefon",
                        "riskIndex": 1.4666667,
                        "date": 1447301877430
                    }];
                    deferred.resolve(estableceBadges(empresas_json));
                });
            } else {
                deferred.resolve(estableceBadges(empresas_json));
            }

            function estableceBadges(empresas_json) {
                //ESTABLECE BADGES
                for (var i = 0; i < empresas_json.length; i++) {
                    var emp = empresas_json[i];
                    empresas_json[i].companyName = empresas_json[i].companyName.toUpperCase();
                    if (emp.riskIndex >= 0 && emp.riskIndex <= 1.75) {
                        empresas_json[i].companyIdx = 'badge-verde';
                    } else if(emp.riskIndex>1.75 && emp.riskIndex<=3.75){
                        empresas_json[i].companyIdx = 'badge-amarillo';
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
                var emp = empresas.filter(function (x) {
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

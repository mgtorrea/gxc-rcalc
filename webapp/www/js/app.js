var app = angular.module('gxc-rcalc', ['ionic', 'ionic.rating']);

var CTE_PESO_ESTANDAR = 1,
    CTE_PESO_SENSIBLE = 2,
    CTE_PESO_ESPECIAL = 3;
var UM__UNIDAD_MONETARIA_SIGNIFICATIVA = 150;
var EP__PRINCIPIOS_USUARIO = 3; //<--ESTE DEPENDE DEL RESPONSABLE


var categorias = {
    estandar: ['nombre', 'teléfono', 'edad', 'sexo', 'Registro Federal de Contribuyentes (RFC)', 'Clave Única de Registro de Población (CURP)', 'estado civil', 'correo electrónico', 'lugar y fecha de nacimiento', 'nacionalidad', 'puesto de trabajo', 'lugar de trabajo', 'experiencia laboral', 'datos de contacto laborales', 'idioma o lengua', 'escolaridad', 'trayectoria educativa', 'títulos', 'certificados', 'cédula profesional'],
    sensible: ['ubicación', 'dirección', 'información de tránsito, viajes, etc.', 'saldos bancarios', 'número de cuenta', 'estado de cuenta', 'cuenta de inversión', 'bienes muebles e inmuebles', 'información fiscal', 'historial crediticio', 'ingresos', 'egresos', 'buró de crédito', 'seguros', 'afores', 'fianzas', 'número de tarjeta bancaria (crédito/débito)', 'nombre de usuario', 'contraseña', 'huellas dactilares', 'información biométrica (iris,voz)', 'firma autógrafa', 'antecedentes penales', 'amparos', 'contratos', 'litigios', 'origen racial/étnico', 'estado de salud', 'información genética', 'creencias religiosas/religión', 'afiliación sindical', 'opiniones políticas', 'preferencia sexual', 'hábitos sexuales'],
    especial: ['fecha vencimiento de tarjeta', 'datos de banda magnética', 'PIN de tarjeta']
};

var mensajes = ["Esa promoción en la que ganaste algo 'gratis' no necesariamente fue tan gratuita después de todo. ¿Revisaste el aviso de privacidad? ¿Sabes cuánto puede valer tu información?", "¿Te pidieron tu dirección y teléfono para recibir promociones? ¿Sabes cuánto puede valer tu información?", "¿Para participar en una promoción te piden tu correo electrónico? ¿Sabes cuánto puede valer tu información?", "Tu información es muy valiosa en el mercado negro. También ten precauciones sobre lo que compartes.", "No sólo te preocupes de los 'acechadores' o 'chismosos' en tus redes sociales. Hay para quienes es más valiosa tu información.", "Qué curioso que los anuncios que te aparecen en internet se relacionen con tus hábitos de navegación, ¿no crees?", "¿Te clonaron tu tarjeta? Una simple fotografía de ella puede dar más información de lo que crees.", "La suplantación de identidad está más cerca de lo que crees, sólo se requiere un descuido."];

var cat_proc = [];

for (var i = 0; i < categorias.estandar.length; i++) {
    cat_proc.push({
        name: categorias.estandar[i],
        cat: 'std',
        badge: '$',
        checked: false
    });
}
for (var i = 0; i < categorias.sensible.length; i++) {
    cat_proc.push({
        name: categorias.sensible[i],
        cat: 'stv',
        badge: '$$',
        checked: false
    });
}
for (var i = 0; i < categorias.especial.length; i++) {
    cat_proc.push({
        name: categorias.especial[i],
        cat: 'spc',
        badge: '$$$',
        checked: false
    });
}

cat_proc = cat_proc.sort(function (a, b) {
    return a.name.localeCompare(b.name)
});

setTimeout(function () {
    document.querySelector('#gxc-loader').style.display = 'none';
}, 1000);


app.controller('RiskCalculatorController', function ($scope, $ionicPopup, $ionicModal, $http, EmpresasService, $ionicLoading, $ionicPopup, $http) {

    var alertPopup = $ionicPopup.alert({
        title: 'Consejo',
        template: mensajes[Math.floor(Math.random() * mensajes.length)]
    }).then(function () {})

    var od = new Odometer({
        el: document.querySelector('.calc-res-costo .content'),
        value: 0,
        format: '(,ddd).dd',
        duration: 1000,
        animation: 'count'
    });

    $scope.items = cat_proc;
    $scope.resultado = 0;
    $scope.items_seleccionados = [];
    $scope.empresas = [];
    $scope.blurredClass = " blurred";
    $scope.EP = EP__PRINCIPIOS_USUARIO;
    $scope.UM = UM__UNIDAD_MONETARIA_SIGNIFICATIVA;

    $scope.clickItem = function (item) {
        if (item.checked) {
            $scope.items_seleccionados.push(item);
            $scope.items.splice($scope.items.indexOf(item), 1);
        } else {
            $scope.items_seleccionados.splice($scope.items_seleccionados.indexOf(item), 1);
            $scope.items.push(item);
        }
        $scope.items.sort(function (a, b) {
            return a.name.localeCompare(b.name)
        });
        $scope.items_seleccionados.sort(function (a, b) {
            return a.name.localeCompare(b.name)
        });
        $scope.actualizaCalculo();
    }

    $scope.actualizaCalculo = function () {
        $scope.resultado = $scope.recalcula();
        od.update($scope.resultado);
    }

    $scope.configuraEmpresa = function () {
        $ionicLoading.show({
            template: '<ion-spinner icon="spiral" class="spinner-light"></ion-spinner><br/>Cargando empresas...'
        });
        EmpresasService.getEmpresas().then(function (res) {
            $scope.empresas = res;
            $ionicModal.fromTemplateUrl('modal-empresa.html', {
                scope: $scope,
                animation: 'slide-in-up'
            }).then(function (modal) {
                $scope.filtro.nombre = "";
                $ionicLoading.hide();
                $scope.modal = modal;
                $scope.modal.show();
            });
            $scope.closeModal = function () {
                $scope.modal.hide();
                $scope.modal.remove();
            };
        });
    }

    $scope.muestraTestConfianza = function () {
        $scope.modal.hide();
        $scope.modal.remove().then(function () {
            $ionicModal.fromTemplateUrl('modal-test-confianza.html', {
                scope: $scope,
                animation: 'slide-in-up'
            }).then(function (modal) {
                $scope.modal = modal;
                $scope.modal.show();
            });
            $scope.closeModal = function () {
                $scope.modal.hide();
                $scope.modal.remove();
            };
        });
    }

    $scope.testConfianza = {
        c1: {
            rate: 0,
            max: 5
        },
        transparencia: false,
        confianza: false,
        control: false,
        valoracion: false
    };

    $scope.evaluaTestConfianza = function () {
        var num_no = 0;
        if (!$scope.testConfianza.transparencia)
            num_no++;
        if (!$scope.testConfianza.confianza)
            num_no++;
        if (!$scope.testConfianza.control)
            num_no++;
        if (!$scope.testConfianza.valoracion)
            num_no++;
        $scope.EP = num_no + 1;
        $scope.testConfianza.rating = $scope.EP;
        $scope.data.empresaSeleccionada = {
            "companyName": "Personalizada",
            "riskIndex": $scope.EP
        };
        var pop = $ionicPopup.show({
            templateUrl: 'popup-reporte.html',
            title: 'Su opinión sobre la empresa es importante y nos serviría para evaluarla. ¿Desea compartir el resultado de su test y reportar la empresa?',
            scope: $scope,
            buttons: [
                {
                    text: 'No enviar',
                    onTap: function (e) {
                        return false;
                    }
                },
                {
                    text: '<b>Enviar</b>',
                    type: 'button-positive',
                    onTap: function (e) {
                        return true;
                    }
                }
            ]
        }).then(function (res) {
            if (res) {
                $scope.data.empresaSeleccionada.companyName = $scope.testConfianza.nombreEmpresa;
                $scope.reportaEmpresa();
            }
            $scope.closeModal();
            $scope.blurredClass = "";
            $scope.actualizaCalculo();
        });
    }

    $scope.reportaEmpresa = function () {
        $scope.testConfianza;
        $ionicLoading.show({
            template: '<ion-spinner icon="spiral" class="spinner-light"></ion-spinner><br/>Enviando reporte...'
        });
        $http({
            method: 'PUT',
            url: 'https://frozen-plateau-1742.herokuapp.com/admin/complains',
            data: {
                complain: {
                    companyName: $scope.testConfianza.nombreEmpresa,
                    rating: $scope.testConfianza.rating,
                    comments: $scope.testConfianza.comentarios
                }
            }
        }).then(function successCallback(response) {
            $ionicLoading.hide();
            $ionicPopup.alert({
                title: 'Gracias',
                template: 'Gracias por el reporte. Nos será de mucha utilidad para calcular el índice de confianza de la empresa.'
            });
            console.log("done");
        }, function errorCallback(response) {
            $ionicLoading.hide();
            console.log("err", response);
        });
    }

    $scope.muestraDetalleCalculo = function () {
        $ionicModal.fromTemplateUrl('modal-detalle-calculo.html', {
            scope: $scope,
            animation: 'slide-in-up'
        }).then(function (modal) {
            $scope.modal = modal;
            $scope.modal.show().then(function () {
                katex.render("V \\approx \\frac{\\sum\\limits_{i=1}^{a}{Ei}+\\sum\\limits_{j=1}^{b}{Sj}+\\sum\\limits_{k=1}^{c}{Pk}}{a+b+c}*EP*UM", document.querySelector("#formula"), {
                    displayMode: true
                });

            });
        });
        $scope.closeModal = function () {
            $scope.modal.hide();
            $scope.modal.remove();
        };
    }

    $scope.filtro = {
        nombre: ''
    };
    $scope.data = {
        empresaSeleccionada: ""
    };

    $scope.busca = function () {
        EmpresasService.consulta($scope.filtro.nombre).then(function (res) {
            $scope.empresas = res;
        });
    }

    $scope.seleccionaEmpresa = function (empresa) {
        $scope.data.empresaSeleccionada = empresa;
        $scope.EP = empresa.riskIndex;
        $scope.blurredClass = "";
        //$scope.busca();
        $scope.actualizaCalculo();
        $scope.closeModal();
    }

    $scope.recalcula = function () {
        $scope.conteos = {
            num_std: 0,
            num_stv: 0,
            num_spc: 0
        }
        for (var i = 0; i < $scope.items_seleccionados.length; i++) {
            var item = $scope.items_seleccionados[i];
            if (item.checked) {
                if (item.cat == 'std') {
                    $scope.conteos.num_std++;
                }
                if (item.cat == 'stv') {
                    $scope.conteos.num_stv++;
                }
                if (item.cat == 'spc') {
                    $scope.conteos.num_spc++;
                }
            }
        }

        var s_p = ($scope.conteos.num_std + $scope.conteos.num_stv + $scope.conteos.num_spc);
        if (s_p == 0) {
            return 0;
        }
        var suma_pesos = ($scope.conteos.num_std * ($scope.conteos.num_std + 1) * CTE_PESO_ESTANDAR) + ($scope.conteos.num_stv * ($scope.conteos.num_stv + 1) * CTE_PESO_SENSIBLE) + ($scope.conteos.num_spc * ($scope.conteos.num_spc + 1) * CTE_PESO_ESPECIAL);
        suma_pesos = suma_pesos / (2 * s_p);
        return suma_pesos * $scope.EP * $scope.UM;
    }

});

app.filter('filtroCategoria', function () {
    return function (items, cat) {
        var filtered = [];

        angular.forEach(items, function (item) {
            if (cat === item.cat) {
                filtered.push(item);
            }
        });

        return filtered;
    }
});

app.controller('MenuController', function ($scope, $ionicModal) {
    $scope.muestraAyuda = function () {
        $ionicModal.fromTemplateUrl('modal-help.html', {
            scope: $scope,
            animation: 'slide-in-up'
        }).then(function (modal) {
            $scope.modal = modal;
            $scope.modal.show();
        });
        $scope.closeModal = function () {
            $scope.modal.hide();
            $scope.modal.remove();
        };
    }

    $scope.muestraAcercaDe = function () {
        $ionicModal.fromTemplateUrl('modal-about.html', {
            scope: $scope,
            animation: 'slide-in-up'
        }).then(function (modal) {
            $scope.modal = modal;
            $scope.modal.show();
        });
        $scope.closeModal = function () {
            $scope.modal.hide();
            $scope.modal.remove();
        };
    }

    $scope.muestraNewsfeed = function () {
        $ionicModal.fromTemplateUrl('modal-newsfeed.html', {
            scope: $scope,
            animation: 'slide-in-up'
        }).then(function (modal) {
            $scope.modal = modal;
            $scope.modal.show();
        });
        $scope.closeModal = function () {
            $scope.modal.hide();
            $scope.modal.remove();
        };
    }

});

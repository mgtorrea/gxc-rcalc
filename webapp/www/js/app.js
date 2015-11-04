var app = angular.module('gxc-rcalc', ['ionic']);

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

var mensajes=["Esa promoción en la que ganaste algo 'gratis' no necesariamente fue tan gratuita después de todo. ¿Revisaste el aviso de privacidad? ¿Sabes cuánto puede valer tu información?","¿Te pidieron tu dirección y teléfono para recibir promociones? ¿Sabes cuánto puede valer tu información?","¿Para participar en una promoción te piden tu correo electrónico? ¿Sabes cuánto puede valer tu información?","Tu información es muy valiosa en el mercado negro. También ten precauciones sobre lo que compartes.","No sólo te preocupes de los 'acechadores' o 'chismosos' en tus redes sociales. Hay para quienes es más valiosa tu información.","Qué curioso que los anuncios que te aparecen en internet se relacionen con tus hábitos de navegación, ¿no crees?","¿Te clonaron tu tarjeta? Una simple fotografía de ella puede dar más información de lo que crees.","La suplantación de identidad está más cerca de lo que crees, sólo se requiere un descuido."];

var cat_proc = [];

for (var i = 0; i < categorias.estandar.length; i++) {
    cat_proc.push({
        name: categorias.estandar[i],
        cat: 'std',
        checked: false
    });
}
for (var i = 0; i < categorias.sensible.length; i++) {
    cat_proc.push({
        name: categorias.sensible[i],
        cat: 'stv',
        checked: false
    });
}
for (var i = 0; i < categorias.especial.length; i++) {
    cat_proc.push({
        name: categorias.especial[i],
        cat: 'spc',
        checked: false
    });
}

cat_proc = cat_proc.sort(function (a, b) {
    return a.name.localeCompare(b.name)
});

setTimeout(function(){
    document.querySelector('#gxc-loader').style.display='none';
},1000);


app.controller('RiskCalculatorController', function ($scope,$ionicPopup) {
    
    var alertPopup = $ionicPopup.alert({
     title: 'Aviso',
     template: mensajes[Math.floor(Math.random()*mensajes.length)]
   }).then(function(){})

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

    $scope.clickItem = function (item) {
        if (item.checked) {
            $scope.items_seleccionados.push(item);
            $scope.items.splice($scope.items.indexOf(item), 1);
        } else {
            $scope.items_seleccionados.splice($scope.items_seleccionados.indexOf(item), 1);
            $scope.items.push(item);
        }
        console.log(item);
        $scope.items.sort(function (a, b) {
            return a.name.localeCompare(b.name)
        });
        $scope.items_seleccionados.sort(function (a, b) {
            return a.name.localeCompare(b.name)
        });
        $scope.resultado = $scope.recalcula();
        od.update($scope.resultado);
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
        var suma_pesos = ($scope.conteos.num_std * CTE_PESO_ESTANDAR) + ($scope.conteos.num_stv * CTE_PESO_SENSIBLE) + ($scope.conteos.num_spc * CTE_PESO_ESPECIAL);
        suma_pesos = suma_pesos / s_p;
        return suma_pesos * EP__PRINCIPIOS_USUARIO * UM__UNIDAD_MONETARIA_SIGNIFICATIVA;
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
        };
    }
});

function obtenerNumero() {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve(10);
        }, 2000);
    });
}

function multiplicarPorDos(numero) {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve(numero * 2);
        }, 2000);
    });
}

obtenerNumero()
    .then((numero) => {
        return multiplicarPorDos(numero);
    })
    .then((resolve) => {
        console.log(resolve);
    });
// ¿Qué imprime?
// R/ undefined

// ¿Por qué?
// por que la cuando el primer .then 
// llama a la funcion que puede devolver una promesa,
// pero en este caso no se retorna

// ¿Qué faltó?
// Faltó retornar la promesa dentro del primer .then()
// para que pase el resultado al siguiente .then

// ¿Qué retorna realmente .then() ?
// retorna una nueva promesa.
function obtenerEdad() {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve(19);
        }, 2000);
    });
}
obtenerEdad()
.then((edad) => {
return verificarMayorDeEdad(edad);
})
.then((mensaje) => {
console.log(mensaje);
})
.catch((error) => {
console.error(error);
});

function verificarMayorDeEdad(edad) {
    return new Promise((mayor,menor) => {
        if(edad >= 18){
            mayor("Eres mayor de edad")
        }else{
            menor("Eres menor de edad")
        }
    });
}
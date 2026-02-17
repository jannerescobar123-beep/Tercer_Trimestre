function saludar(nombre) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (nombre === "Janner") {
                resolve("Hola " + nombre + ", Bienvenido")
            } else {
                    reject("No te conozco")
            }
        },2000)
    })
}
saludar("Janner")
.then((resolve) => {
console.log(resolve);
})
.catch((reject) => {
console.error(reject);
});


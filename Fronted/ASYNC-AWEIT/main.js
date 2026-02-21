async function saludar(nombre) {
    return new Promise((res, rej) => {
        setTimeout(() => {
            if (nombre === "Alejo") {
                res("Nombre correcto");
            } else {
                rej();
            }
        }, 1000);
    });
}

async function main() {
    try {
        const respuesta = await saludar("Alejo");
        console.log(respuesta);
        console.log("Hola");
    } catch {
        console.log("Ocurrió un error");
    }
}

main();
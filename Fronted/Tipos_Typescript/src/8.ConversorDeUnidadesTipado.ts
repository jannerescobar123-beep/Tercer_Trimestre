type unidad = "cm" | "m" | "km";

function convertirDistancia(valor: number, desde: unidad, hasta: unidad): number {
    if (desde === "cm") {
        valor /= 100;
    } else if (desde === "km") {
        valor *= 1000;
    }

    if (hasta === "cm") {
        return valor * 100;
    } else if (hasta === "km") {
        return valor / 1000;
    }
    return valor;
}

console.log(convertirDistancia(100, "cm", "m"));
console.log(convertirDistancia(1, "m", "cm"));
console.log(convertirDistancia(1, "km", "m"));
console.log(convertirDistancia(1000, "m", "km"));
console.log("holi");

type Transaccion = {
  monto: number
  tipo: "ingreso" | "egreso"
  categoria: string
}

function agruparTransacciones(transacciones: Transaccion[]): { ingreso: number, egreso: number } {
  let resultado = {
    ingreso: 0,
    egreso: 0
  }

  transacciones.forEach((t) => {
    if (t.tipo === "ingreso") {
      resultado.ingreso += t.monto
    } else {
      resultado.egreso += t.monto
    }
  })

  return resultado
}

let datos: Transaccion[] = [
  { monto: 200000, tipo: "ingreso", categoria: "salario" },
  { monto: 50000, tipo: "egreso", categoria: "comida" },
  { monto: 200000, tipo: "egreso", categoria: "negocio" },
  { monto: 40000, tipo: "egreso", categoria: "transporte" }
]

let resultadoFinal = agruparTransacciones(datos)
console.log(resultadoFinal)
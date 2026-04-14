interface Calificacion {
  estudianteId: number
  materia: string
  categoria: "tareas" | "quices" | "examen"
  nota: number
}

function promedioPorCategoria(
  calificaciones: Calificacion[],
  estudianteId: number
): { tareas: number; quices: number; examen: number } {

  let suma = {
    tareas: 0,
    quices: 0,
    examen: 0
  }

  let conteo = {
    tareas: 0,
    quices: 0,
    examen: 0
  }

  calificaciones.forEach((c) => {
    if (c.estudianteId === estudianteId) {

      if (c.categoria === "tareas") {
        suma.tareas += c.nota
        conteo.tareas++

      } else if (c.categoria === "quices") {
        suma.quices += c.nota
        conteo.quices++

      } else if (c.categoria === "examen") {
        suma.examen += c.nota
        conteo.examen++
      }

    }
  })

  return {
    tareas: conteo.tareas ? suma.tareas / conteo.tareas : 0,
    quices: conteo.quices ? suma.quices / conteo.quices : 0,
    examen: conteo.examen ? suma.examen / conteo.examen : 0
  }
}
// datos de prueba
const datos: Calificacion[] = [
  { estudianteId: 1, materia: "math", categoria: "tareas", nota: 4 },
  { estudianteId: 1, materia: "math", categoria: "tareas", nota: 3 },
  { estudianteId: 1, materia: "math", categoria: "quices", nota: 5 },
  { estudianteId: 1, materia: "math", categoria: "examen", nota: 4.5 },

  { estudianteId: 2, materia: "math", categoria: "tareas", nota: 2 }
]
const resultado2 = promedioPorCategoria(datos, 1)

console.log(resultado2)
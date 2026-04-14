type CambioNombre = {
  tipo: "nombre"
  anterior: string
  nuevo: string 
}

type CambioCorreo = {
  tipo: "correo"
  anterior: string
  nuevo: string
}

type CambioPassword = {
  tipo: "password"
  anterior: string
  nuevo: string
}

type CambioUsuario = CambioNombre | CambioCorreo | CambioPassword

function resumenCambios(cambios: CambioUsuario[]): string[] {
  let resumen: string[] = []

  cambios.forEach((cambio) => {
    if (cambio.tipo === "nombre") {
      resumen.push(`Se cambió el nombre de "${cambio.anterior}" a "${cambio.nuevo}"`)

    } else if (cambio.tipo === "correo") {
      resumen.push(`Se cambió el correo de "${cambio.anterior}" a "${cambio.nuevo}"`)

    } else if (cambio.tipo === "password") {
      resumen.push(`Se cambió la contraseña`)
    }
  })

  return resumen
}

// datos de prueba
const historial: CambioUsuario[] = [
  { tipo: "nombre", anterior: "Juan", nuevo: "Carlos" },
  { tipo: "correo", anterior: "juan@gmail.com", nuevo: "carlos@gmail.com" },
  { tipo: "password", anterior: "1234", nuevo: "abcd" }
]

const resultados = resumenCambios(historial)

resultados.forEach((r) => console.log(r))
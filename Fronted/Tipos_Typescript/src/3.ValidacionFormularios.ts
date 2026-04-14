interface campoFormulario {
    nombre: string;
    tipo:"texto"|"numero"|"email"
    valor: string|number
}
function validarCampos(campos: campoFormulario[]): string[] {
  let camposInvalidos: string[] = []

  campos.forEach((campo) => {

    if (campo.tipo === "texto") {
      if (typeof campo.valor !== "string" || campo.valor.trim() === "") {
        camposInvalidos.push(campo.nombre)
      }

    } else if (campo.tipo === "numero") {
      if (typeof campo.valor !== "number") {
        camposInvalidos.push(campo.nombre)
      }

    } else if (campo.tipo === "email") {
      if (typeof campo.valor !== "string" || !campo.valor.includes("@")) {
        camposInvalidos.push(campo.nombre)
      }
    }

  })

  return camposInvalidos
}
 // datos de prueba 
const formulario: campoFormulario[] = [
  { nombre: "nombre", tipo: "texto", valor: "Carlos Pérez" },      
  { nombre: "edad", tipo: "numero", valor: 25 },                     //estan bien
  { nombre: "correo", tipo: "email", valor: "carlos@gmail.com" },   

  { nombre: "mensaje", tipo: "texto", valor: "" },                   
  { nombre: "telefono", tipo: "numero", valor: "12345" },            // estan mal
  { nombre: "correo2", tipo: "email", valor: "correo.com" }          
]

const resultado = validarCampos(formulario)

resultado.forEach((campo) => {
  console.log(`El campo ${campo} es inválido`)
})
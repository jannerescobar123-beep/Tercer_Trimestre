interface campoFormulario {
    nombre: string;
    tipo:"texto"|"numero"|"email"
    valor: string|number
}
function validacion (campos:campoFormulario[]): string[] {
    let camposInvalidos : string [] = []
    campos.forEach((campo)=>{
        if (campo.tipo === "texto"){
            if(typeof campo.valor !== "string" || campo.valor.trim() === ""){
                camposInvalidos.push(campo.nombre)
            }
        }
    })
}
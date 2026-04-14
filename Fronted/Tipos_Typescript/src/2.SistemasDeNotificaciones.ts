type Email = {
    tipo: "email"
    email: string
    mensaje: string
}
type SMS = {
    tipo: "SMS"
    telefono: number
    mensaje: string
}
 type Notificacion = Email | SMS

function enviarNotificacion(notificacion: Notificacion) {
  if (notificacion.tipo === "email") {
    console.log(`Enviando email a ${notificacion.email} con el mensaje: ${notificacion.mensaje}`)
  } else {
    console.log(`Enviando SMS al número ${notificacion.telefono} con el mensaje: ${notificacion.mensaje}`)
  }
}
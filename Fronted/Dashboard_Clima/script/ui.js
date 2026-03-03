export function mostrarLoader() {
  const contenedor = document.getElementById("weather-result");
  contenedor.innerHTML = `
    <p>Buscando ciudad...</p>`;
}
export function mostrarError(mensaje) {
  const contenedor = document.getElementById("weather-result");
  contenedor.innerHTML = `
    <p style="color: red;">⚠️ ${mensaje}</p>`;
}
export function mostrarClima(datosCiudad, datosClima) {
  const contenedor = document.getElementById("weather-result");
  contenedor.innerHTML = `
    <div class="weather-card">
      <h2>${datosCiudad.nombre}, ${datosCiudad.pais}</h2>
      <p>🌡️ Temperatura: ${datosClima.temperatura} °C</p>
      <p>💨 Viento: ${datosClima.viento} km/h</p>
      <p>🕐 Hora: ${datosClima.hora}</p>
    </div>`;
}


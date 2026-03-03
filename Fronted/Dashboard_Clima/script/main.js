import { obtenerCoordenadas, obtenerClima } from './service.js';
import { mostrarLoader, mostrarError, mostrarClima } from './ui.js';

async function buscarClima(nombreCiudad) {
  mostrarLoader();

  const ciudad = await obtenerCoordenadas(nombreCiudad);

  if (ciudad === null) {
    mostrarError(`No se encontró la ciudad "${nombreCiudad}"`);
    return;
  }

  const clima = await obtenerClima(ciudad.latitud, ciudad.longitud);

  if (clima === null) {
    mostrarError("No se pudo obtener el clima. Intenta de nuevo.");
    return;
  }

  mostrarClima(ciudad, clima);
}

document.getElementById("search-form").addEventListener("submit", function(evento) {
  evento.preventDefault();

  const nombreCiudad = document.getElementById("city-input").value.trim();

  if (nombreCiudad === "") {
    mostrarError("Por favor escribe el nombre de una ciudad.");
    return;
  }

  buscarClima(nombreCiudad);
});
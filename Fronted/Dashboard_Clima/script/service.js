export async function obtenerCoordenadas(nombreCiudad) {
  try {
    const url = `https://geocoding-api.open-meteo.com/v1/search?name=${encodeURIComponent(nombreCiudad)}&count=1&language=es&format=json`;

    const respuesta = await fetch(url);

    if (!respuesta.ok) {
      return null;
    }

    const datos = await respuesta.json();

    if (!datos.results || datos.results.length === 0) {
      return null;
    }

    const ciudad = datos.results[0];

    return {
      nombre: ciudad.name,
      pais: ciudad.country,
      latitud: ciudad.latitude,
      longitud: ciudad.longitude,
    };
  } catch (error) {
    console.error("Error al obtener coordenadas:", error);
    return null;
  }
}

export async function obtenerClima(latitud, longitud) {
  try {
    const url = `https://api.open-meteo.com/v1/forecast?latitude=${latitud}&longitude=${longitud}&current=temperature_2m,wind_speed_10m,weather_code&temperature_unit=celsius&wind_speed_unit=kmh&timezone=auto`;

    const respuesta = await fetch(url);

    if (!respuesta.ok) {
      return null;
    }

    const datos = await respuesta.json();

    const actual = datos.current;

    return {
      temperatura: actual.temperature_2m,
      viento: actual.wind_speed_10m,
      codigo: actual.weather_code,
      hora: actual.time,
    };
  } catch (error) {
    console.error("Error al obtener el clima:", error);
    return null;
  }
}

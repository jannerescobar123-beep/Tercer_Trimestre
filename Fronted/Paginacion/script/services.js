const URL_BASE = "https://spapi.dev/api/";

export async function obtenerItems(endpoint, pagina, limite) {
  const respuesta = await fetch(`${URL_BASE}${endpoint}?page=${pagina}`);
  if (!respuesta.ok) throw new Error(`Error ${respuesta.status}`);
  const datos = await respuesta.json();
  const items = Array.isArray(datos) ? datos : (datos.data ?? []);
  return items.slice(0, limite);
}

export async function obtenerTotal(endpoint) {
  let ultimaPagina = 1;
  for (let pagina = 1; pagina <= 50; pagina++) {
    try {
      const respuesta = await fetch(`${URL_BASE}${endpoint}?page=${pagina}`);
      if (!respuesta.ok) break;
      const datos = await respuesta.json();
      const items = Array.isArray(datos) ? datos : (datos.data ?? []);
      if (items.length === 0) break;
      ultimaPagina = pagina;
    } catch {
      break;
    }
  }
  const respuesta = await fetch(`${URL_BASE}${endpoint}?page=${ultimaPagina}`);
  const datos = await respuesta.json();
  const items = Array.isArray(datos) ? datos : (datos.data ?? []);
  return (ultimaPagina - 1) * 10 + items.length;
}

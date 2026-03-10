const URL_BASE = "https://spapi.dev/api/";

export async function obtenerTodos(endpoint) {
  let todos = [];
  for (let pagina = 1; pagina <= 50; pagina++) {
    try {
      const respuesta = await fetch(`${URL_BASE}${endpoint}?page=${pagina}`);
      if (!respuesta.ok) break;
      const datos = await respuesta.json();
      const items = Array.isArray(datos) ? datos : (datos.data ?? []);
      if (items.length === 0) break;
      todos = todos.concat(items);
    } catch {
      break;
    }
  }
  return todos;
}

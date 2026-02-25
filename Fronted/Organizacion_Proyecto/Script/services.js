export async function obtenerTareasDesdeAPI() {
    const respuesta = await fetch(
        "https://jsonplaceholder.typicode.com/todos?_limit=5"
    );

    return await respuesta.json();
}
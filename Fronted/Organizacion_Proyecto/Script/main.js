import { agregarTarea } from "./state.js";
import { guardarTareas, cargarTareas } from "./persistence.js";
import { renderizarTareas } from "./ui.js";
import { obtenerTareasDesdeAPI } from "./services.js";

const formulario = document.getElementById("task-form");
const inputTarea = document.getElementById("task-input");
const listaTareas = document.getElementById("task-list");
const botonAPI = document.getElementById("load-api");


formulario.addEventListener("submit", (e) => {
    e.preventDefault();

    const valor = inputTarea.value.trim();
    if (!valor) return;

    agregarTarea({
        titulo: valor,
        completada: false,
    });

    inputTarea.value = "";
    guardarTareas();
    renderizarTareas(listaTareas);
});

botonAPI.addEventListener("click", async () => {
    try {
        const tareasAPI = await obtenerTareasDesdeAPI();

        tareasAPI.forEach((t) => {
            agregarTarea({
                titulo: t.title,
                completada: t.completed,
            });
        });

        guardarTareas();
        renderizarTareas(listaTareas);
    } catch {
        alert("Error cargando tareas");
    }
});
cargarTareas();
renderizarTareas(listaTareas);
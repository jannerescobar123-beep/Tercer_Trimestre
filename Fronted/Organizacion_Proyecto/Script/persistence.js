import { tareas, establecerTareas } from "./state.js";

export function guardarTareas() {
    localStorage.setItem("tareas", JSON.stringify(tareas));
}

export function cargarTareas() {
    const datos = localStorage.getItem("tareas");
    if (datos) {
        establecerTareas(JSON.parse(datos));
    }
}
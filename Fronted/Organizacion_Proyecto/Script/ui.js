import { tareas, alternarTarea, eliminarTarea } from "./state.js";
import { guardarTareas } from "./persistence.js";

export function renderizarTareas(elementoLista) {
    elementoLista.innerHTML = "";

    tareas.forEach((tarea, índice) => {
        const li = document.createElement("li");
        li.textContent = tarea.titulo;

        if (tarea.completada) {
            li.classList.add("completada");
        }

        li.addEventListener("click", () => {
            alternarTarea(índice);
            guardarTareas();
            renderizarTareas(elementoLista);
        });

        const botonEliminar = document.createElement("button");
        botonEliminar.textContent = "X";

        botonEliminar.addEventListener("click", (e) => {
            e.stopPropagation();
            eliminarTarea(índice);
            guardarTareas();
            renderizarTareas(elementoLista);
        });

        li.appendChild(botonEliminar);
        elementoLista.appendChild(li);
    });
}
export let tareas = [];

export function agregarTarea(tarea) {
    tareas.push(tarea);
}

export function eliminarTarea(índice) {
    tareas.splice(índice, 1);
}

export function alternarTarea(índice) {
    tareas[índice].completada = !tareas[índice].completada;
}

export function establecerTareas(nuevasTareas) {
    tareas = nuevasTareas;
}
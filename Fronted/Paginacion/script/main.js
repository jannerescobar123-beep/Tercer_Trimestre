import { estado } from "./state.js";
import { obtenerTodos } from "./services.js";
import { mostrarCargador, ocultarCargador, mostrarError, renderizarTarjetas, renderizarInfo, renderizarPaginacion } from "./ui.js";

let todosLosItems = [];

async function cargarPagina() {
  mostrarCargador();
  try {
    if (todosLosItems.length === 0) {
      todosLosItems = await obtenerTodos(estado.endpoint);
    }

    estado.totalPaginas = Math.ceil(todosLosItems.length / estado.limite);
    estado.paginaActual = Math.min(Math.max(1, estado.paginaActual), estado.totalPaginas);

    const desde = (estado.paginaActual - 1) * estado.limite;
    const items = todosLosItems.slice(desde, desde + estado.limite);

    ocultarCargador();
    renderizarTarjetas(items);
    renderizarInfo(estado.paginaActual, estado.totalPaginas, todosLosItems.length, estado.limite);
    renderizarPaginacion(estado.paginaActual, estado.totalPaginas, irAPagina);

  } catch (error) {
    mostrarError("⚠️ " + error.message);
  }
}

function irAPagina(numeroPagina) {
  estado.paginaActual = Math.min(Math.max(1, numeroPagina), estado.totalPaginas);
  cargarPagina();
  window.scrollTo({ top: 0, behavior: "smooth" });
}

document.getElementById("boton-buscar").addEventListener("click", () => {
  estado.endpoint = document.getElementById("selector-endpoint").value;
  estado.limite = Number(document.getElementById("selector-limite").value);
  estado.paginaActual = 1;
  todosLosItems = [];
  cargarPagina();
});

document.getElementById("selector-limite").addEventListener("change", () => {
  estado.limite = Number(document.getElementById("selector-limite").value);
  estado.paginaActual = 1;
  cargarPagina();
});

document.getElementById("boton-reintentar").addEventListener("click", cargarPagina);
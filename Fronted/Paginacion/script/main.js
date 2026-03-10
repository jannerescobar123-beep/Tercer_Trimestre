import { estado } from "./state.js";
import { obtenerItems, obtenerTotal } from "./services.js";
import { mostrarCargador, ocultarCargador, mostrarError, renderizarTarjetas, renderizarInfo, renderizarPaginacion } from "./ui.js";

async function cargarPagina() {
  mostrarCargador();
  try {
    if (!estado.totalItems) {
      estado.totalItems = await obtenerTotal(estado.endpoint);
    }
    estado.totalPaginas = Math.ceil(estado.totalItems / estado.limite);
    estado.paginaActual = Math.min(Math.max(1, estado.paginaActual), estado.totalPaginas);

    const items = await obtenerItems(estado.endpoint, estado.paginaActual, estado.limite);

    ocultarCargador();
    renderizarTarjetas(items);
    renderizarInfo(estado.paginaActual, estado.totalPaginas, estado.totalItems, estado.limite);
    renderizarPaginacion(estado.paginaActual, estado.totalPaginas, irAPagina);

  } catch (error) {
    mostrarError(error.message);
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
  estado.totalItems = 0;
  cargarPagina();
});

document.getElementById("boton-reintentar").addEventListener("click", cargarPagina);
export function mostrarCargador() {
  document.getElementById("cargador").style.display = "flex";
  document.getElementById("grilla-tarjetas").style.display = "none";
  document.getElementById("caja-error").style.display = "none";
}

export function ocultarCargador() {
  document.getElementById("cargador").style.display = "none";
  document.getElementById("grilla-tarjetas").style.display = "grid";
}

export function mostrarError(mensaje) {
  document.getElementById("cargador").style.display = "none";
  document.getElementById("grilla-tarjetas").style.display = "none";
  document.getElementById("caja-error").style.display = "block";
  document.getElementById("mensaje-error").textContent = mensaje;
}

export function renderizarTarjetas(items) {
  const grilla = document.getElementById("grilla-tarjetas");
  grilla.innerHTML = "";
  items.forEach((item) => {
    const tarjeta = document.createElement("div");
    tarjeta.className = "tarjeta";
    tarjeta.innerHTML = `
      ${item.img ? `<img src="${item.img}" alt="${item.name}">` : ""}
      <div class="cuerpo-tarjeta">
        <span class="id-tarjeta">#${item.id}</span>
        <h3 class="titulo-tarjeta">${item.name || "Sin nombre"}</h3>
        ${item.occupation ? `<p class="detalle-tarjeta">💼 ${item.occupation}</p>` : ""}
        ${item.season ? `<p class="detalle-tarjeta">📺 T${item.season} E${item.episode}</p>` : ""}
        ${item.country ? `<p class="detalle-tarjeta">🌎 ${item.country}</p>` : ""}
        ${item.members ? `<p class="detalle-tarjeta">👥 ${item.members.length} miembros</p>` : ""}
      </div>`;
    grilla.appendChild(tarjeta);
  });
}

export function renderizarInfo(paginaActual, totalPaginas, totalItems, limite) {
  const desde = (paginaActual - 1) * limite + 1;
  const hasta = Math.min(paginaActual * limite, totalItems);
  document.getElementById("info-pagina").innerHTML =
    `Página <strong>${paginaActual}</strong> de <strong>${totalPaginas}</strong>
     &nbsp;·&nbsp; <strong>${desde}–${hasta}</strong> de <strong>${totalItems}</strong>`;
}

export function renderizarPaginacion(
  paginaActual,
  totalPaginas,
  alCambiarPagina,
) {
  const contenedor = document.getElementById("controles-paginacion");
  contenedor.innerHTML = "";

  const crearBtn = (texto, destino, deshabilitado, claseExtra = "") => {
    const boton = document.createElement("button");
    boton.textContent = texto;
    boton.disabled = deshabilitado;
    boton.className = `btn-pagina ${claseExtra}`;
    if (!deshabilitado) boton.onclick = () => alCambiarPagina(destino);
    contenedor.appendChild(boton);
  };

  crearBtn("First", 1, paginaActual === 1, "btn-primero");
  crearBtn("<<", paginaActual - 1, paginaActual === 1);

  for (
    let p = Math.max(1, paginaActual - 3);
    p <= Math.min(totalPaginas, paginaActual + 3);
    p++
  ) {
    crearBtn(p, p, false, p === paginaActual ? "btn-activo" : "");
  }

  crearBtn(">>", paginaActual + 1, paginaActual === totalPaginas);
  crearBtn("Last", totalPaginas, paginaActual === totalPaginas, "btn-ultimo");

  const saltos = document.createElement("div");
  saltos.className = "saltos";
  [-5, -2, +2, +5].forEach((salto) => {
    const destino = paginaActual + salto;
    if (destino >= 1 && destino <= totalPaginas) {
      const boton = document.createElement("button");
      boton.textContent = salto > 0 ? `+${salto}` : `${salto}`;
      boton.className = "btn-pagina";
      boton.onclick = () => alCambiarPagina(destino);
      saltos.appendChild(boton);
    }
  });
  contenedor.appendChild(saltos);
}

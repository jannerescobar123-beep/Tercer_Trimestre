function agregarNotas() {
    const titulo = document.getElementById("titulo").value;
    const contenido = document.getElementById("contenido").value;

    if (titulo === "" || contenido === "") {
        alert("llene los campos mk");
        return;
    }

    const nota = document.createElement("div");
    nota.classList.add("nota", "normal");

    nota.innerHTML = 
    `<h3>${titulo}</h3>
    <p>${contenido}</p>
        <div class="acciones">
            <span onclick="marcarImportante(this)">★</span>
            <span onclick="eliminarNota(this)">🗑️</span>
            </div>`;
    document.getElementById("notas").appendChild(nota);

    document.getElementById("titulo").value = "";
    document.getElementById("contenido").value = "";

}
function marcarImportante(elemento) {
    const nota = elemento.parentElement.parentElement;
    nota.classList.toggle("importante");
    nota.classList.toggle("normal");
}
function eliminarNota(elemento) {
    elemento.parentElement.parentElement.remove();
}

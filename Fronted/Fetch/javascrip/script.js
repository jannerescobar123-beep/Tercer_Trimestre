import { printUsers, clearUsers } from "./manejoDom.js";

const btnConsultar = document.querySelector("#btnConsultar");
const btnLimpiar = document.querySelector("#btnLimpiar");

btnConsultar.addEventListener("click", async () => {

    try {

        const response = await fetch("https://spapi.dev/api/characters");

        const data = await response.json();

        printUsers(data.data);

    } catch (error) {
        console.error("Error:", error);
    }

});

btnLimpiar.addEventListener("click", () => {
    clearUsers();
});
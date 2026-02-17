const users = [
    {
        "id": 1,
        "name": "Alejandro Gómez",
        "email": "alejandro.gomez@example.com"
    },
    {
        "id": 2,
        "name": "María Fernanda López",
        "email": "maria.lopez@example.com"
    },
    {
        "id": 3,
        "name": "Carlos Andrés Ruiz",
        "email": "carlos.ruiz@example.com"
    },
    {
        "id": 4,
        "name": "Laura Daniela Martínez",
        "email": "laura.martinez@example.com"
    },
    {
        "id": 5,
        "name": "Juan Sebastián Torres",
        "email": "juan.torres@example.com"
    }
]
function buscarUsuario(id) {
    return new Promise((resolve, reject) => {

        setTimeout(() => {

            const user = users.find(u => u.id === id);

            if (user) {
                resolve(user);
            } else {
                reject("Usuario no encontrado");
            }

        }, 2000);

    });
}

const btn = document.getElementById("btn");
const result = document.getElementById("result");
const spinner = document.getElementById("spinner");


btn.addEventListener("click", () => {

    const idInput = document.getElementById("searchUser").value;

    if (idInput.trim() === "") {
        result.innerHTML = "Ingrese un ID válido";
        return;
    }

    const id = parseInt(idInput); // Convertir a número

    spinner.style.display = "block";
    result.innerHTML = "Buscando usuario...";

    buscarUsuario(id)
        .then(user => {
            result.innerHTML =
            `Usuario encontrado:<br>
            Nombre: ${user.name}<br>
            Email: ${user.email}`;
        })
        .catch(error => {
            result.innerHTML = error;
        })
        .finally(() => {
            spinner.style.display = "none";
        });
});

interface Usuario {
    nombre: string;
    edad: number;
    estadoActivo: boolean;
    rol: "admin" | "editor" | "visitante";
}

function mayorEdad(usuario: Usuario[]): Usuario[] {
    return usuario.filter((u) => u.edad >= 18 && u.estadoActivo && (u.rol === "admin" || u.rol === "editor"));
}

console.log(mayorEdad([
    { nombre: "Juan", edad: 25, estadoActivo: true, rol: "admin" },
    { nombre: "María", edad: 17, estadoActivo: true, rol: "editor" },
    { nombre: "Pedro", edad: 20, estadoActivo: false, rol: "admin" },
    { nombre: "Ana", edad: 19, estadoActivo: true, rol: "visitante" }
]));
const users = [
    { id: 1, name: "Carlos Pérez", email: "carlos.perez@email.com" },
    { id: 2, name: "María Gómez", email: "maria.gomez@email.com" },
    { id: 3, name: "Juan Rodríguez", email: "juan.rodriguez@email.com" },
    { id: 4, name: "Ana Martínez", email: "ana.martinez@email.com" },
    { id: 5, name: "Luis Fernández", email: "luis.fernandez@email.com" },
    { id: 6, name: "Laura Torres", email: "laura.torres@email.com" },
    { id: 7, name: "Andrés Ramírez", email: "andres.ramirez@email.com" },
    { id: 8, name: "Sofía Morales", email: "sofia.morales@email.com" },
    { id: 9, name: "Diego Herrera", email: "diego.herrera@email.com" },
    { id: 10, name: "Valentina Ríos", email: "valentina.rios@email.com" },
];

const purchases = [
    { id: 1, userId: 1, product: "Laptop", price: 3500, quantity: 1 },
    { id: 2, userId: 2, product: "Mouse", price: 80, quantity: 2 },
    { id: 3, userId: 1, product: "Teclado", price: 150, quantity: 1 },
    { id: 4, userId: 3, product: "Monitor", price: 900, quantity: 1 },
    { id: 5, userId: 4, product: "Audífonos", price: 200, quantity: 1 },
    { id: 6, userId: 5, product: "Tablet", price: 1200, quantity: 1 },
    { id: 7, userId: 6, product: "Celular", price: 2500, quantity: 1 },
    { id: 8, userId: 7, product: "Cargador", price: 60, quantity: 3 },
    { id: 9, userId: 8, product: "Smartwatch", price: 800, quantity: 1 },
    { id: 10, userId: 9, product: "Parlante", price: 300, quantity: 2 },
    { id: 11, userId: 10, product: "Cámara web", price: 400, quantity: 1 },
];

async function getUser(userId) {
    return new Promise((res, rej) => {
        setTimeout(() => {
            const user = users.find((user) => user.id === userId);
            user ? res(user) : rej("El usuario no existe");
        }, 1000);
    });
}

async function getUserProducts(userId) {
    return new Promise((res) => {
        setTimeout(() => {
            const products = purchases.filter(
                (product) => product.userId === userId,
            );
            res(products);
        }, 1000);
    });
}

async function calculateAverage(productList) {
    return new Promise((res) => {
        setTimeout(() => {
            const sum = productList.reduce((acc, cur) => {
                acc += cur.price;
                return acc;
            }, 0);
            res(sum / productList.length);
        }, 1000);
    });
}

async function main() {
    try {
        const user = await getUser(1);
        console.log("Usuario encontrado: ", user);
        const products = await getUserProducts(user.id);
        console.log("Producstos del usuario: ", products);
        const avg = await calculateAverage(products);
        console.log("Promedio de los productos: ", avg);
    } catch (err) {
        console.log(err);
    }
}

main();
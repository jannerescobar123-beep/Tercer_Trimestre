interface Producto{
    id: number;
    nombre: string;
    cantidad: number;
    categoria: "alimentos" | "tecnologia" | "papeleria";
}

function stockBajo(productos: Producto[], categoria: Producto["categoria"]): Producto[] {
    if (categoria === "alimentos") {
        return productos.filter(p => p.cantidad < 20);
    } else if (categoria === "tecnologia") {
        return productos.filter(p => p.cantidad < 5);
    } else if (categoria === "papeleria") {
        return productos.filter(p => p.cantidad < 50);
    } else {
        return [];
    }
}

const productos: Producto[] = [
    { id: 1, nombre: "alimento", cantidad: 20, categoria: "alimentos" },
    { id: 2, nombre: "tecnologia", cantidad: 5, categoria: "tecnologia" },
    { id: 3, nombre: "papeleria", cantidad: 50, categoria: "papeleria" },];

    console.log(stockBajo(productos, "alimentos"));
    console.log(stockBajo(productos, "tecnologia"));
    console.log(stockBajo(productos, "papeleria"));
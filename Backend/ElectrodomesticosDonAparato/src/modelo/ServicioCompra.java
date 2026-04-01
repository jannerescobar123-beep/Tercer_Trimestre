package modelo;

import modelo.dto.CompraDTO;

public class ServicioCompra {

    public CompraDTO calcularCompra(CompraDTO c){

        System.out.println("Aplicando descuento...");

        c.total = c.precio * c.cantidad;

        if(c.tipo.equals("A")){
            c.descuento = c.total * 0.4;
        }else{
            c.descuento = 0;
        }

        c.totalFinal = c.total - c.descuento;

        return c;
    }
}

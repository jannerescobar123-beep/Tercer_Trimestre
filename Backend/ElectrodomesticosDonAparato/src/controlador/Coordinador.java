package controlador;

import gui.*;
import modelo.*;
import modelo.dto.*;

public class Coordinador {

    private VentanaPrincipal principal;
    private VentanaLogin login;
    private VentanaRegistro registro;
    private VentanaAdmin admin;
    private VentanaCompra compra;

    private ServicioUsuario servicio;
    private ServicioCompra servicioCompra;

    public void setPrincipal(VentanaPrincipal v){ principal=v; }
    public void setLogin(VentanaLogin v){ login=v; }
    public void setRegistro(VentanaRegistro v){ registro=v; }
    public void setAdmin(VentanaAdmin v){ admin=v; }
    public void setCompra(VentanaCompra v){ compra=v; }

    public void setServicio(ServicioUsuario s){ servicio=s; }
    public void setServicioCompra(ServicioCompra s){ servicioCompra=s; }

    public void mostrarLogin(){ login.setVisible(true); }
    public void mostrarRegistro(){ registro.setVisible(true); }
    public void mostrarAdmin(){ admin.setVisible(true); }
    public void mostrarCompra(){ compra.setVisible(true); }

    public void login(){
        UsuarioDTO u = servicio.login(login.getUser(), login.getPass());

        if(u != null){
            login.dispose();

            if(u.getRol().equals("admin")){
                mostrarAdmin();
            }else{
                mostrarCompra();
            }
        }else{
            login.mensaje("Datos incorrectos");
        }
    }

    public void registrar(){
        servicio.registrar(registro.getUser(),registro.getPass(),registro.getRol());
        registro.mensaje("Usuario guardado");
        registro.dispose();
    }

    public void listarUsuarios(){
        String txt="";
        for(UsuarioDTO u: servicio.listar()){
            txt += u.getUsername()+" - "+u.getRol()+"\n";
        }
        admin.mostrarUsuarios(txt);
    }

    public void realizarCompra(){

        if(compra.getCliente().isEmpty()){
            compra.mensaje("Por favor complete todos los datos");
            return;
        }

        System.out.println("Calculando compra...");

        CompraDTO c = new CompraDTO();

        c.nombre = compra.getCliente();
        c.edad = Integer.parseInt(compra.getEdad());
        c.telefono = compra.getTelefono();
        c.tipo = compra.getTipo();

        c.producto = compra.getProducto();
        c.precio = Double.parseDouble(compra.getPrecio());
        c.cantidad = Integer.parseInt(compra.getCantidad());

        c = servicioCompra.calcularCompra(c);

        String r = "Cliente: "+c.nombre+
                "\nTotal: "+c.total+
                "\nDescuento: "+c.descuento+
                "\nPagar: "+c.totalFinal;

        compra.mostrarResultado(r);
    }

    public void limpiar(){
        compra.limpiar();
    }
}

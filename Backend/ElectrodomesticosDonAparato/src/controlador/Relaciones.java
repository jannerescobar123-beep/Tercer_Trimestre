package controlador;

import gui.*;
import modelo.*;

public class Relaciones {

    public void iniciar(){

        Coordinador c = new Coordinador();

        VentanaPrincipal vp = new VentanaPrincipal();
        VentanaLogin vl = new VentanaLogin(vp,true);
        VentanaRegistro vr = new VentanaRegistro(vp,true);
        VentanaAdmin va = new VentanaAdmin(vp,true);
        VentanaCompra vc = new VentanaCompra(vp,true);

        ServicioUsuario su = new ServicioUsuario();
        ServicioCompra sc = new ServicioCompra();

        vp.setCoordinador(c);
        vl.setCoordinador(c);
        vr.setCoordinador(c);
        va.setCoordinador(c);
        vc.setCoordinador(c);

        c.setPrincipal(vp);
        c.setLogin(vl);
        c.setRegistro(vr);
        c.setAdmin(va);
        c.setCompra(vc);

        c.setServicio(su);
        c.setServicioCompra(sc);

        vp.setVisible(true);
    }
}

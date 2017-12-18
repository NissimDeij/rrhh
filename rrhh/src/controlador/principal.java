package controlador;
import vista.menuPrincipal;
import vista.menuTrabajador;

public class principal {

    
    public static void main(String[] args) {
        menuPrincipal p = new menuPrincipal();
        controlador c = new controlador(p);
        c.iniciar();
    }
    
}

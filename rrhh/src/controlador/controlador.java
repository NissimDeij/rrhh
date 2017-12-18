/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import modelo.modelo;
import vista.menuPrincipal;
import vista.menuTrabajador;

/**
 *
 * @author Duoc UC
 */
public class controlador implements ActionListener,MouseListener{
    private menuPrincipal vistaPrincipal;
    private menuTrabajador vistaTrabajador;
    
    private modelo modelo= new modelo();
    
    public enum accion{
        //menu-items
        itmSalir, //salir
        itmListar, //listar nomina
        itmBuscar, //buscar trabajador por codigo
        itmEditar, //editar trabajador por codigo
        itmEliminar, //eliminar trab por codigo
        //botones
        btnGuardar, //guardar (o ingresar) nuevo trabajador
        btnGuardarCambios, //guardar cambios (al editar trabajador)
        btnEliminar, //eliminar trabajador
        btnLimpiar //limpiar campos (para agregar o editar trabajador)
    };
}

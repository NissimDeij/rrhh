/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelo.modelo;
import vista.menuPrincipal;
import vista.menuTrabajador;

/**
 *
 * @author Duoc UC
 */
public class controlador implements ActionListener, MouseListener {

    //vistas
    private menuPrincipal vistaPrincipal;
    private menuTrabajador vistaTrabajador = new menuTrabajador();

    //modelo
    private modelo modelo = new modelo();

    //acciones que se ejecutaran por cada vista
    public enum accion {
        //menu-items
        itmSalir, //salir
        itmListar, //listar nomina
        itmBuscar, //buscar trabajador por codigo
        itmAgregar, //agregar trabajador
        itmEditar, //editar trabajador por codigo
        itmEliminar, //eliminar trab por codigo
        //botones
        btnGuardar, //guardar (o ingresar) nuevo trabajador
        btnGuardarCambios, //guardar cambios (al editar trabajador)
        btnEliminar, //eliminar trabajador
        btnLimpiar, //limpiar campos (para agregar o editar trabajador)
        btnVolver //vuelve a la ventana principal
    };

    //constructor de clase
    public controlador(JFrame padre) {
        this.vistaPrincipal = (menuPrincipal) padre;
    }

    //inicia todas las acciones y listeners de las vistas
    public void iniciar() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vistaPrincipal);
            SwingUtilities.updateComponentTreeUI(this.vistaTrabajador);
            this.vistaPrincipal.setLocationRelativeTo(null);
            this.vistaPrincipal.setTitle("Human Resources ERP");
            this.vistaPrincipal.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }

        //escuchamos los botones
        //item salir
        this.vistaPrincipal.itmSalir.setActionCommand("itmSalir");
        this.vistaPrincipal.itmSalir.addActionListener(this);

        //item buscar
        this.vistaPrincipal.itmBuscar.setActionCommand("itmBuscar");
        this.vistaPrincipal.itmBuscar.addActionListener(this);

        //item agregar
        this.vistaPrincipal.itmAgregar.setActionCommand("itmAgregar");
        this.vistaPrincipal.itmAgregar.addActionListener(this);

        //item editar
        this.vistaPrincipal.itmEditar.setActionCommand("itmEditar");
        this.vistaPrincipal.itmEditar.addActionListener(this);

        //item eliminar
        this.vistaPrincipal.itmEliminar.setActionCommand("itmEliminar");
        this.vistaPrincipal.itmEliminar.addActionListener(this);

        //item listar
        this.vistaPrincipal.itmListar.setActionCommand("itmListar");
        this.vistaPrincipal.itmListar.addActionListener(this);

        //boton volver
        this.vistaTrabajador.btnVolver.setActionCommand("btnVolver");
        this.vistaTrabajador.btnVolver.addActionListener(this);
        
        //boton grabar
        this.vistaTrabajador.btnGrabar.setActionCommand("btnGrabar");
        this.vistaTrabajador.btnGrabar.addActionListener(this);
        
        //boton modificar
        this.vistaTrabajador.btnModificar.setActionCommand("btnModificar");
        this.vistaTrabajador.btnModificar.addActionListener(this);
        
        //boton modificar
        this.vistaTrabajador.btnEliminar.setActionCommand("btnEliminar");
        this.vistaTrabajador.btnEliminar.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {       
        switch (accion.valueOf(e.getActionCommand())) {
            case itmSalir:
                this.vistaPrincipal.setVisible(false);
                System.exit(0);
                break;

            case itmBuscar:
                this.vistaTrabajador.setLocationRelativeTo(null);
                this.vistaTrabajador.setTitle("Buscar trabajador por codigo");
                this.vistaTrabajador.setVisible(true);
                this.vistaTrabajador.lblTituloVentana.setText("Buscar trabajador");
                this.vistaTrabajador.panelCampos.setEnabled(false); //deshabilitamos todos los campos
                this.vistaTrabajador.txtCodigo.setEnabled(true); //habilitamos solo el campo codigo
                this.vistaTrabajador.btnGrabar.setEnabled(false);
                this.vistaTrabajador.btnEliminar.setEnabled(false);
                break;

            case itmAgregar:
                this.vistaTrabajador.setLocationRelativeTo(null);
                this.vistaTrabajador.setTitle("Agregar trabajador");
                this.vistaTrabajador.setVisible(true);
                this.vistaTrabajador.lblTituloVentana.setText("Agregar trabajador");
                this.vistaTrabajador.btnEliminar.setEnabled(false);
                this.vistaTrabajador.btnModificar.setEnabled(false);
                break;

            case itmEditar:
                this.vistaTrabajador.setLocationRelativeTo(null);
                this.vistaTrabajador.setTitle("Editar trabajador por codigo");
                this.vistaTrabajador.setVisible(true);
                this.vistaTrabajador.lblTituloVentana.setText("Editar trabajador");
                this.vistaTrabajador.txtCodigo.setEnabled(false); //habilitamos solo el campo codigo
                this.vistaTrabajador.btnGrabar.setEnabled(false);
                this.vistaTrabajador.btnEliminar.setEnabled(false);
                break;

            case itmEliminar:
                this.vistaTrabajador.setLocationRelativeTo(null);
                this.vistaTrabajador.setTitle("Eliminar trabajador por codigo");
                this.vistaTrabajador.setVisible(true);
                this.vistaTrabajador.lblTituloVentana.setText("Eliminar trabajador");
                this.vistaTrabajador.panelCampos.setEnabled(false); //deshabilitamos todos los campos
                this.vistaTrabajador.txtCodigo.setEnabled(true); //habilitamos solo el campo codigo
                this.vistaTrabajador.btnGrabar.setEnabled(false);
                this.vistaTrabajador.btnModificar.setEnabled(false);
                break;
                
            case itmListar:
                this.vistaPrincipal.tblTrabajadores.setModel(this.modelo.ListarTrabajadores()); //actualiza JTable
                this.vistaTrabajador.setVisible(false);
                
            case btnGuardar:
                //Enviamos datos del formulario Agregar Trabajador a metodo agregarTrabajador
                if (this.modelo.agregarTrabajador(
                        Integer.parseInt(this.vistaTrabajador.txtCodigo.getText()),
                        this.vistaTrabajador.txtRut.getText(),
                        this.vistaTrabajador.txtDv.getText(),
                        this.vistaTrabajador.txtNombre.getText(),
                        this.vistaTrabajador.txtApellido.getText(),
                        Integer.parseInt(this.vistaTrabajador.txtCelular.getText()),
                        this.vistaTrabajador.txtEmail.getText(),
                        Integer.parseInt(this.vistaTrabajador.txtSueldo.getText()),
                        this.vistaTrabajador.cmbEstadoCivil.getSelectedItem().toString(),
                        this.vistaTrabajador.cmbDepartamento.getSelectedItem().toString()
                )) {
                    JOptionPane.showMessageDialog(null, "Trabajador agregado correctamente");
                    //Limpiamos textField
                    this.vistaTrabajador.txtCodigo.setText("");
                    this.vistaTrabajador.txtRut.setText("");
                    this.vistaTrabajador.txtDv.setText("");
                    this.vistaTrabajador.txtNombre.setText("");
                    this.vistaTrabajador.txtApellido.setText("");
                    this.vistaTrabajador.txtCelular.setText("");
                    this.vistaTrabajador.txtEmail.setText("");
                    this.vistaTrabajador.txtSueldo.setText("");
                    this.vistaTrabajador.cmbEstadoCivil.setSelectedIndex(0);
                    this.vistaTrabajador.cmbDepartamento.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo agregar trabajador");
                }
                break;

            case btnGuardarCambios:
                if (this.modelo.editarTrabajador(
                        Integer.parseInt(this.vistaTrabajador.txtCodigo.getText()),
                        this.vistaTrabajador.txtRut.getText(),
                        this.vistaTrabajador.txtDv.getText(),
                        this.vistaTrabajador.txtNombre.getText(),
                        this.vistaTrabajador.txtApellido.getText(),
                        Integer.parseInt(this.vistaTrabajador.txtCelular.getText()),
                        this.vistaTrabajador.txtEmail.getText(),
                        Integer.parseInt(this.vistaTrabajador.txtSueldo.getText()),
                        this.vistaTrabajador.cmbEstadoCivil.getSelectedItem().toString(),
                        this.vistaTrabajador.cmbDepartamento.getSelectedItem().toString()
                )) {
                    this.vistaPrincipal.tblTrabajadores.setModel(this.modelo.ListarTrabajadores()); //actualiza JTable
                    JOptionPane.showMessageDialog(null, "Listado de trabajadores actualizado");
                    //Limpiamos textField
                    this.vistaTrabajador.txtCodigo.setText("");
                    this.vistaTrabajador.txtRut.setText("");
                    this.vistaTrabajador.txtDv.setText("");
                    this.vistaTrabajador.txtNombre.setText("");
                    this.vistaTrabajador.txtApellido.setText("");
                    this.vistaTrabajador.txtCelular.setText("");
                    this.vistaTrabajador.txtEmail.setText("");
                    this.vistaTrabajador.txtSueldo.setText("");
                    this.vistaTrabajador.cmbEstadoCivil.setSelectedIndex(0);
                    this.vistaTrabajador.cmbDepartamento.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo modificar trabajador");
                }
                break;

            case btnEliminar:
                if (this.modelo.eliminarTrabajador(
                       Integer.parseInt(this.vistaTrabajador.txtCodigo.getText())
                        )){
                    JOptionPane.showMessageDialog(null, "Trabajador eliminado correctamente");
                    //Limpiamos textField
                    this.vistaTrabajador.txtCodigo.setText("");
                    this.vistaTrabajador.txtRut.setText("");
                    this.vistaTrabajador.txtDv.setText("");
                    this.vistaTrabajador.txtNombre.setText("");
                    this.vistaTrabajador.txtApellido.setText("");
                    this.vistaTrabajador.txtCelular.setText("");
                    this.vistaTrabajador.txtEmail.setText("");
                    this.vistaTrabajador.txtSueldo.setText("");
                    this.vistaTrabajador.cmbEstadoCivil.setSelectedIndex(0);
                    this.vistaTrabajador.cmbDepartamento.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar trabajador");
                }
                break;

            case btnLimpiar:
                this.vistaTrabajador.txtCodigo.setText("");
                this.vistaTrabajador.txtRut.setText("");
                this.vistaTrabajador.txtDv.setText("");
                this.vistaTrabajador.txtNombre.setText("");
                this.vistaTrabajador.txtApellido.setText("");
                this.vistaTrabajador.txtCelular.setText("");
                this.vistaTrabajador.txtEmail.setText("");
                this.vistaTrabajador.txtSueldo.setText("");
                this.vistaTrabajador.cmbEstadoCivil.setSelectedIndex(0);
                this.vistaTrabajador.cmbDepartamento.setSelectedIndex(0);
                break;

            case btnVolver:
                this.vistaTrabajador.setVisible(false);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1)//boton izquierdo
        {
            //Muestro datos de producto a modificar
            int fila = this.vistaPrincipal.tblTrabajadores.rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.vistaTrabajador.txtCodigo.setText(String.valueOf(this.vistaPrincipal.tblTrabajadores.getValueAt(fila, 1)));
                this.vistaTrabajador.txtRut.setText(String.valueOf(this.vistaPrincipal.tblTrabajadores.getValueAt(fila, 2)));
                this.vistaTrabajador.txtDv.setText(String.valueOf(this.vistaPrincipal.tblTrabajadores.getValueAt(fila, 2)));
                this.vistaTrabajador.txtNombre.setText(String.valueOf(this.vistaPrincipal.tblTrabajadores.getValueAt(fila, 3)));
                this.vistaTrabajador.txtApellido.setText(String.valueOf(this.vistaPrincipal.tblTrabajadores.getValueAt(fila, 4)));
                this.vistaTrabajador.txtCelular.setText(String.valueOf(this.vistaPrincipal.tblTrabajadores.getValueAt(fila, 5)));
                this.vistaTrabajador.txtEmail.setText(String.valueOf(this.vistaPrincipal.tblTrabajadores.getValueAt(fila, 6)));
                this.vistaTrabajador.txtSueldo.setText(String.valueOf(this.vistaPrincipal.tblTrabajadores.getValueAt(fila, 7)));
                this.vistaTrabajador.cmbEstadoCivil.setSelectedItem(String.valueOf(this.vistaPrincipal.tblTrabajadores.getValueAt(fila, 8)));
                this.vistaTrabajador.cmbDepartamento.setSelectedItem(String.valueOf(this.vistaPrincipal.tblTrabajadores.getValueAt(fila, 9)));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

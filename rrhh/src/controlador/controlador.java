/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
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
public class controlador implements ActionListener, MouseListener, FocusListener {

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
        btnBuscar, //buscar un trabajador por codigo
        btnGrabar, //guardar (o ingresar) nuevo trabajador
        btnGuardarCambios, //guardar cambios (al editar trabajador)
        btnEliminar, //eliminar trabajador
        btnLimpiar, //limpiar campos (para agregar o editar trabajador)
        btnVolver, //vuelve a la ventana principal
        //JTextFields
        txtCodigo, // campo de ingreso para el código del trabajador
        txtRut, // campo de ingreso para el rut del trabajador
        txtDv, // campo de ingreso del dígito verificador para el rut del trabajador
        txtNombre, // campo de ingreso para el nombre del trabajador
        txtApellido, // campo de ingreso para el apellido del trabajador
        txtCelular, // campo de ingreso para el celular del trabajador
        txtEmail, // campo de ingreso para el email del trabajador
        txtSueldo, // campo de ingreso para el sueldo del trabajador
        
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
            this.vistaPrincipal.tblTrabajadores.setModel(this.modelo.ListarTrabajadores()); //actualiza JTable
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
        
        //agregamos focus listeners para los campos del formulario ingresar
        this.vistaTrabajador.txtCodigo.addFocusListener(this);
        this.vistaTrabajador.txtRut.addFocusListener(this);
        this.vistaTrabajador.txtNombre.addFocusListener(this);
        this.vistaTrabajador.txtApellido.addFocusListener(this);
        this.vistaTrabajador.txtCelular.addFocusListener(this);
        this.vistaTrabajador.txtEmail.addFocusListener(this);
        this.vistaTrabajador.txtSueldo.addFocusListener(this);
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
                
                //habilitamo y deshabilitamos los campos requeridos
                this.vistaTrabajador.txtCodigo.setEditable(true);
                this.vistaTrabajador.txtRut.setEditable(false);
                this.vistaTrabajador.txtNombre.setEditable(false);
                this.vistaTrabajador.txtApellido.setEditable(false);
                this.vistaTrabajador.txtCelular.setEditable(false);
                this.vistaTrabajador.txtEmail.setEditable(false);
                this.vistaTrabajador.txtSueldo.setEditable(false);
                this.vistaTrabajador.cmbEstadoCivil.setEnabled(false);
                this.vistaTrabajador.cmbDepartamento.setEnabled(false);
                
                this.vistaTrabajador.btnBuscar.setEnabled(true);
                this.vistaTrabajador.btnGrabar.setEnabled(false);
                this.vistaTrabajador.btnModificar.setEnabled(false);
                this.vistaTrabajador.btnEliminar.setEnabled(false);
                break;

            case itmAgregar:
                this.vistaTrabajador.setLocationRelativeTo(null);
                this.vistaTrabajador.setTitle("Agregar trabajador");
                this.vistaTrabajador.setVisible(true);
                this.vistaTrabajador.lblTituloVentana.setText("Agregar trabajador");
                
                //habilitamo y deshabilitamos los campos requeridos
                this.vistaTrabajador.txtCodigo.setEditable(true);
                this.vistaTrabajador.txtRut.setEditable(true);
                this.vistaTrabajador.txtNombre.setEditable(true);
                this.vistaTrabajador.txtApellido.setEditable(true);
                this.vistaTrabajador.txtCelular.setEditable(true);
                this.vistaTrabajador.txtEmail.setEditable(true);
                this.vistaTrabajador.txtSueldo.setEditable(true);
                this.vistaTrabajador.cmbEstadoCivil.setEnabled(true);
                this.vistaTrabajador.cmbDepartamento.setEnabled(true);
                
                this.vistaTrabajador.btnBuscar.setEnabled(false);
                this.vistaTrabajador.btnGrabar.setEnabled(true);
                this.vistaTrabajador.btnModificar.setEnabled(false);
                this.vistaTrabajador.btnEliminar.setEnabled(false);
                break;

            case itmEditar:
                this.vistaTrabajador.setLocationRelativeTo(null);
                this.vistaTrabajador.setTitle("Editar trabajador por codigo");
                this.vistaTrabajador.setVisible(true);
                this.vistaTrabajador.lblTituloVentana.setText("Editar trabajador");
                
                //habilitamo y deshabilitamos los campos requeridos
                this.vistaTrabajador.txtCodigo.setEditable(false);
                this.vistaTrabajador.txtRut.setEditable(true);
                this.vistaTrabajador.txtNombre.setEditable(true);
                this.vistaTrabajador.txtApellido.setEditable(true);
                this.vistaTrabajador.txtCelular.setEditable(true);
                this.vistaTrabajador.txtEmail.setEditable(true);
                this.vistaTrabajador.txtSueldo.setEditable(true);
                this.vistaTrabajador.cmbEstadoCivil.setEnabled(true);
                this.vistaTrabajador.cmbDepartamento.setEnabled(true);
                
                this.vistaTrabajador.btnBuscar.setEnabled(false);
                this.vistaTrabajador.btnGrabar.setEnabled(false);
                this.vistaTrabajador.btnModificar.setEnabled(true);
                this.vistaTrabajador.btnEliminar.setEnabled(false);
                break;

            case itmEliminar:
                this.vistaTrabajador.setLocationRelativeTo(null);
                this.vistaTrabajador.setTitle("Eliminar trabajador por codigo");
                this.vistaTrabajador.setVisible(true);
                this.vistaTrabajador.lblTituloVentana.setText("Eliminar trabajador");
                
                this.vistaTrabajador.txtCodigo.setEditable(true); //habilitamos solo el campo codigo
                this.vistaTrabajador.txtRut.setEditable(false);                
                this.vistaTrabajador.txtNombre.setEditable(false);
                this.vistaTrabajador.txtApellido.setEditable(false);
                this.vistaTrabajador.txtCelular.setEditable(false);
                this.vistaTrabajador.txtEmail.setEditable(false);
                this.vistaTrabajador.txtSueldo.setEditable(false);
                this.vistaTrabajador.cmbEstadoCivil.setEnabled(false);
                this.vistaTrabajador.cmbDepartamento.setEnabled(false);
                
                this.vistaTrabajador.btnBuscar.setEnabled(false);
                this.vistaTrabajador.btnGrabar.setEnabled(false);
                this.vistaTrabajador.btnModificar.setEnabled(false);
                this.vistaTrabajador.btnEliminar.setEnabled(true);
                break;
                
            case itmListar:
                this.vistaPrincipal.tblTrabajadores.setModel(this.modelo.ListarTrabajadores()); //actualiza JTable
                this.vistaTrabajador.setVisible(false);
            
            case btnBuscar:
                String[] arreglin = new String[8];
                arreglin = this.modelo.buscarTrabajador(Integer.parseInt(this.vistaTrabajador.txtCodigo.getText()));
                this.vistaTrabajador.txtRut.setText(String.valueOf(arreglin[0]));
                this.vistaTrabajador.txtNombre.setText(String.valueOf(arreglin[1]));
                this.vistaTrabajador.txtApellido.setText(String.valueOf(arreglin[2]));
                this.vistaTrabajador.txtCelular.setText(String.valueOf(arreglin[3]));
                this.vistaTrabajador.txtEmail.setText(String.valueOf(arreglin[4]));
                this.vistaTrabajador.txtSueldo.setText(String.valueOf(arreglin[5]));
                this.vistaTrabajador.cmbEstadoCivil.setSelectedItem(String.valueOf(arreglin[6]));
                this.vistaTrabajador.cmbDepartamento.setSelectedItem(String.valueOf(arreglin[7]));
                
            case btnGrabar:
                //Enviamos datos del formulario Agregar Trabajador a metodo agregarTrabajador
                if (this.modelo.agregarTrabajador(
                        Integer.parseInt(this.vistaTrabajador.txtCodigo.getText()),
                        this.vistaTrabajador.txtRut.getText(),
                        this.vistaTrabajador.txtNombre.getText(),
                        this.vistaTrabajador.txtApellido.getText(),
                        Integer.parseInt(this.vistaTrabajador.txtCelular.getText()),
                        this.vistaTrabajador.txtEmail.getText(),
                        Integer.parseInt(this.vistaTrabajador.txtSueldo.getText()),
                        this.vistaTrabajador.cmbEstadoCivil.getSelectedItem().toString().substring(0, 1),
                        this.vistaTrabajador.cmbDepartamento.getSelectedItem().toString()
                )) {
                    JOptionPane.showMessageDialog(null, "Trabajador agregado correctamente");
                    //Limpiamos textField
                    this.vistaTrabajador.txtCodigo.setText("");
                    this.vistaTrabajador.txtRut.setText("");
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
    @Override
    public void focusGained(FocusEvent fe) {}

    @Override
    public void focusLost(FocusEvent fe) {
        // Validación instantanea al salir del campo código
        if(fe.getSource()==this.vistaTrabajador.txtCodigo){
            if(!this.vistaTrabajador.txtCodigo.getText().equals("") && this.vistaTrabajador.lblTituloVentana.getText().equals("Agregar trabajador")){
                if(!modelo.validarCodigoEntre(this.vistaTrabajador.txtCodigo.getText())){
                    JOptionPane.showMessageDialog(null, "El código ingresado no es válido.\nPor favor ingrese solo números entre 1 y 100.");
                    this.vistaTrabajador.txtCodigo.requestFocus();
                }
                if(!modelo.validarCodigoExiste(this.vistaTrabajador.txtCodigo.getText())){
                    JOptionPane.showMessageDialog(null, "El código ingresado ya existe en la base de datos.\nPor favor intente otro código.");
                    this.vistaTrabajador.txtCodigo.requestFocus();
                }
            }    
        }
        // Validación instantanea al salir del campo rut
        else if(fe.getSource()==this.vistaTrabajador.txtRut){
            if(!this.vistaTrabajador.txtRut.getText().equals("")){
                if(!modelo.validarRut(this.vistaTrabajador.txtRut.getText())){
                    JOptionPane.showMessageDialog(null, "El rut ingresado no es válido.\nPor favor ingrese el rut sin puntos ni dígito verificador.");
                    this.vistaTrabajador.txtRut.requestFocus();
                }
            }    
        }
        // Validación instantanea al salir del campo nombre
        else if(fe.getSource()==this.vistaTrabajador.txtNombre){
            if(!this.vistaTrabajador.txtNombre.getText().equals("")){
                if(!modelo.validarLargoString(this.vistaTrabajador.txtNombre.getText())){
                    JOptionPane.showMessageDialog(null, "El nombre ingresado exede el número máximo de caracteres permitido.\nPor favor ingrese el un nombre de no más de 20 caracteres.");
                    this.vistaTrabajador.txtNombre.requestFocus();
                }
            }    
        }
        // Validación instantanea al salir del campo apellido
        else if(fe.getSource()==this.vistaTrabajador.txtApellido){
            if(!this.vistaTrabajador.txtApellido.getText().equals("")){
                if(!modelo.validarLargoString(this.vistaTrabajador.txtApellido.getText())){
                    JOptionPane.showMessageDialog(null, "El apellido ingresado exede el número máximo de caracteres permitido.\nPor favor ingrese el un apellido de no más de 20 caracteres.");
                    this.vistaTrabajador.txtApellido.requestFocus();
                }
            }    
        }
        // Validación instantanea al salir del campo celular
        else if(fe.getSource()==this.vistaTrabajador.txtCelular){
            if(!this.vistaTrabajador.txtCelular.getText().equals("")){
                if(!modelo.validarCelular(this.vistaTrabajador.txtCelular.getText())){
                    JOptionPane.showMessageDialog(null, "El número celular ingresado no coincide con el formato esperado (9 dígitos).\nPor favor ingrese solo números.");
                    this.vistaTrabajador.txtCelular.requestFocus();
                }
            }    
        }
        // Validación instantanea al salir del campo email
        else if(fe.getSource()==this.vistaTrabajador.txtEmail){
            if(!this.vistaTrabajador.txtEmail.getText().equals("")){
                
                JOptionPane.showMessageDialog(null, "Validación email pendiente.");
                /*
                if(!modelo.validarEmail(this.vistaTrabajador.txtEmail.getText())){
                    JOptionPane.showMessageDialog(null, "El número celular ingresado no coincide con el formato esperado (9 dígitos).\nPor favor ingrese solo números.");
                    this.vistaTrabajador.txtEmail.requestFocus();
                }
                */
            }    
        }
        // Validación instantanea al salir del campo sueldo
        else if(fe.getSource()==this.vistaTrabajador.txtSueldo){
            if(!this.vistaTrabajador.txtSueldo.getText().equals("")){
                if(!modelo.validarSueldo(this.vistaTrabajador.txtSueldo.getText())){
                    JOptionPane.showMessageDialog(null, "El sueldo ingresado no es válido.\nPor favor ingrese solo valores mayores a $120.000");
                    this.vistaTrabajador.txtSueldo.requestFocus();
                }
            }    
        }
    }
}

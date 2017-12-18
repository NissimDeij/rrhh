package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import sql.conexion;

public class modelo extends conexion{    
    
    //metodo para agregar trabajador
    public boolean agregarTrabajador(
            int codigo,
            String rut,
            String nombre,
            String apellido,
            int celular,
            String email,
            int sueldoBruto,
            String estadoCivil,
            String nomDepto){
        String query = "INSERT INTO rrhh_bd.empleados("+
                "codigo,rut,nombre,apellido,celular,email,sueldoBruto,estadoCivil,nomDepto)" +
                "VALUES('"+codigo+
                "','"+rut+
                "','"+nombre+
                "','"+apellido+
                "','"+celular+
                "','"+email+
                "','"+sueldoBruto+
                "','"+estadoCivil+
                "','"+nomDepto+
                "') ;";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    
    //metodo para mostrar los trabajadores ya ingresados
        public DefaultTableModel ListarTrabajadores(){
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Codigo","Rut","Nombre","Apellido","Celular","Email","Sueldo Bruto","Estado Civil","Departamento"};
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM rrhh_bd.empleados;");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
      Object[][] data = new String[registros][9];
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM rrhh_bd.empleados;");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "codigo" );
                data[i][1] = res.getString( "rut" );
                data[i][2] = res.getString( "nombre" );
                data[i][3] = res.getString( "apellido" );
                data[i][4] = res.getString( "celular" );
                data[i][5] = res.getString( "email" );
                data[i][6] = res.getString( "sueldoBruto" );
                data[i][7] = res.getString( "estadoCivil" );
                data[i][8] = res.getString( "nomDepto" );
            i++;
         }
         res.close();
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
    
        
    //metodo para editar un trabajador
    public boolean modificarTrabajador(
            int codigo,
            String rut,
            String nombre,
            String apellido,
            int celular,
            String email,
            int sueldoBruto,
            String estadoCivil,
            String nomDepto){
        String query= "UPDATE rrhh_bd.empleados SET "+
                "rut='"+nombre+
                "', nombre='"+nombre+
                "', apellido='"+apellido+
                "', celular='"+celular+
                "', email='"+email+
                "', sueldoBruto='"+sueldoBruto+
                "', estadoCivil='"+estadoCivil+
                "', nomDepto='"+nomDepto+
                "' WHERE codigo='"+codigo+
                "' ;";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.execute();
            pstm.close();
            return true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return false;
    }
    
    
    //metodo para eliminar trabajador
    public boolean eliminarTrabajador(int codigo){
        String query = "DELETE FROM rrhh_bd.empleados WHERE codigo ="+codigo+";";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
   
    
    //metodo para validar codigo no exista
    public boolean validarCodigoExiste(String codigo){
        int registros=1; //inicializado en 1 para descartar falsos negativos al comprobar existencia del codigo en la bd
        String query = "SELECT count(*) as total FROM rrhh_bd.empleados WHERE codigo="+codigo+";";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        if(registros==0){ //verifica que no exista el codigo
            return true;
        }    
        return false;
    }
    
    
    //metodo para validar que el codigo sea mayor a 0 y menor o igual a 100
    public boolean validarCodigoEntre(String codigo){
        if (Integer.parseInt(codigo)>0 && Integer.parseInt(codigo)<=100){ //  ]0,100]
            return true;
        }
        return false;
    }
    
    
    //metodo para validar el largo de los String (para capturar el error de la base de datos si excede 20 caracteres)
    public boolean validarLargoString (String varchar){
        if (varchar.length()<=20){
            return true;
        }
        return false;
    }
    
    
    //metodo para validar que el digito verificador del rut sea 0-9, K o k
    public boolean validarDigitoVerificador(String digito){
        String arreglo[] = new String[]{"0","1","2","3","4","5","6","7","8","9","K","k"};
        if(!Arrays.asList(arreglo).contains(digito)){
            return false; 
        }
        return true;
    }
    
    
    //metodo para validar que el celular este compuesto por digitos y que sea de largo 9
    public boolean validarCelular(String celular){
        if(celular.length()!=9){
            return false;
        }else {
            for (int i = 0; i < celular.length(); i++) {
                if(!Character.isDigit(celular.charAt(i))) {
                    return false;    
                }
            }
        }
        return true;
    }
    
    
    //metodo para validar que el sueldo bruto sea mayor o igual a $120.000
    public boolean validarSueldo(String sueldoBruto){
        if (Integer.parseInt(sueldoBruto)>=120000){
            return true;
        }
        return false;
    }
    
    
}

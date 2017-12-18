package modelo;

import java.util.Arrays;

public class modelo {    
    
    //metodo para agregar trabajador
    
    
    //metodo para eliminar trabajador
    
   
    
    //metodo para validar codigo sea mayor a 0 y menor o igual a 100
    public boolean validarCodigo(String codigo){
        if (Integer.parseInt(codigo)>0 && Integer.parseInt(codigo)<=100){
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
    public boolean validarSueldo(String sueldo){
        if (Integer.parseInt(sueldo)>=120000){
            return true;
        }
        return false;
    }
    
    
}

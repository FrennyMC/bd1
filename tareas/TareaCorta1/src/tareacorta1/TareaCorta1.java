/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tareacorta1;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * Frenny Montezuma Castillo 
 */
public class TareaCorta1 {

    public static void main(String[] args) {
        List<Cuenta> cuentas = new ArrayList<>();

        cuentas.add(new Cuenta("Rafael Astorga"));
        cuentas.add(new Cuenta("María López", 15000));
        cuentas.add(new Cuenta("Sara Carzo", 10000));
        cuentas.add(new Cuenta("Isaac Vega", 50000));
        cuentas.add(new Cuenta("Daniel Rugama", 850000));
        cuentas.add(new Cuenta("Diego Estrada", 64000));
        cuentas.add(new Cuenta("Pamela Espinoza", 96000));
        cuentas.add(new Cuenta("Karol Lopez", 32000));
        
        // Mostrar información de las cuentas
        // Recorrer el arraylist con el método toString
        System.out.println("--------------------");
        System.out.println("Datos Inicio");
        System.out.println("--------------------");
        for (Cuenta cuenta : cuentas) {
            System.out.println(cuenta.toString());
        }
        
        // Cambiar el nombre del cliente y saldo
        System.out.println("--------------------");
        System.out.println("Alterar Datos");
        System.out.println("--------------------");
        cuentas.get(0).setCliente("Nuevo Cliente");
        cuentas.get(0).setSaldo(20000);

        System.out.println("Cambio de datos:");
        System.out.println("Cliente anterior: " + cuentas.get(0).getCliente());
        System.out.println("Saldo anterior: " + cuentas.get(0).getSaldo());

        for (Cuenta cuenta : cuentas) {
            System.out.println(cuenta.toString());
        } 
        System.out.println("--------------------");
        System.out.println("Depósitos y Retiros");
        System.out.println("--------------------");
        // Realizar depósitos en cuentas específicas
        // En la cuenta 1
        if (cuentas.size() > 0) {
            cuentas.get(0).depositar(5000);
        }
        // En la cuenta 2
        if (cuentas.size() > 1) {
            cuentas.get(1).depositar(2000);
        }
        // En la cuenta 3
        if (cuentas.size() > 2) {
            cuentas.get(2).depositar(7000);
        }
        // En la cuenta 4
        if (cuentas.size() > 3) {
            cuentas.get(3).depositar(6000);
        }

        // Realizar retiros en cuentas específicas
        // En la cuenta 5
        if (cuentas.size() > 4) {
            cuentas.get(4).retirar(3000);
        }
        // En la cuenta 6
        if (cuentas.size() > 5) {
            cuentas.get(5).retirar(2000);
        }
        // En la cuenta 7
        if (cuentas.size() > 6) {
            cuentas.get(6).retirar(1500);
        }
        // En la cuenta 8
        if (cuentas.size() > 7) {
            cuentas.get(7).retirar(4000);
        }

        // Mostrar información de las cuentas
        for (Cuenta cuenta : cuentas) {
            System.out.println(cuenta.toString());
        }
    }
}



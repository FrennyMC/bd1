/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareacorta1;

import java.time.LocalDateTime;

/**
 *
 * @Frenny Montezuma Castillo
 */
public class Cuenta {
    private static int cantCuentas = 0;
    private int numCuenta;
    private String cliente;
    private double saldo;
    private LocalDateTime fechaCreacion;

    public Cuenta(String pNombre) {
        cantCuentas++;
        numCuenta = cantCuentas;
        cliente = pNombre;
        saldo = 0;
        fechaCreacion = LocalDateTime.now();
    }

    public Cuenta(String pNombre, double pMonto) {
        cantCuentas++;
        numCuenta = cantCuentas;
        cliente = pNombre;
        saldo = pMonto;
        fechaCreacion = LocalDateTime.now();
    }
    
    // Getters and Setters 
    
    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int pNumCuenta) {
        numCuenta = pNumCuenta;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String pCliente) {
        cliente = pCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double pSaldo) {
        saldo = pSaldo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    // Funciones 
    
    public void depositar(double pMonto) {
        if (pMonto > 0) {
            saldo += pMonto;
        } else {
            throw new IllegalArgumentException("El monto a retirar no puede ser negativo..");
        }
    }

    public void retirar(double pMonto) {
        if (pMonto > 0 && pMonto <= saldo) {
            saldo -= pMonto;
        } else {
            throw new IllegalArgumentException("Monto a retirar inválido.");
        }
    }
    
    // Método toString 
    
    @Override
    public String toString() {
        return "Cuenta número: " + numCuenta +
               "\nCliente: " + cliente +
               "\nSaldo: " + saldo +
               "\nFecha creación: " + fechaCreacion;
    }
    
}
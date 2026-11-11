package org.example.servicios;

import java.util.List;

public class CalculadoraVenta {
    private List<Venta> ventas;

    public class CalculadoraVenta(List<Venta> ventas){
        this.ventas = ventas;
    }
    public double totalVentas(){
        double total = 0;
        if (ventas == null || ventas.isEmpty() ){
            return 0;
        }
        for (Venta venta : ventas){
            if (venta == null){
                System.out.println("Se encontro una venta con registro invalido");
                continue;
            }
            double valor = venta.getValor;
            if (valor< 0 || Double.isFinite(valor) == false ){
                System.out.println("Valor de la venta invalido");
            }
            total  += valor;


        }
        return total;
    }
    public int canidadTotalvendidaPorProducto(String producto){
       int totalVendidos = 0;
        for(Venta venta : ventas){
            if(venta.getProducto == producto){
                totalVendidos += venta.getCantidad;
            }

        }
    }
}

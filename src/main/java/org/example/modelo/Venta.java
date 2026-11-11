package org.example.modelo;
import java.time.LocalDate;

public class Venta {
    private final LocalDate fecha;
    private final String producto;
    private final int cantidad;
    private final double valor;

    public Venta(LocalDate fecha, String producto, int cantidad, double valor) {
        this.fecha = fecha;
        this.producto = producto;
        this.cantidad = cantidad;
        this.valor = valor;
    }

    public LocalDate getFecha() { return fecha; }
    public String getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getValor() { return valor; }

    @Override
    public String toString() {
        return"Venta{" +
                "fecha: " + fecha + ", producto: '" + producto + "', cantidad: "
                + cantidad + ", valor: " + valor +
                "}";
    }

}




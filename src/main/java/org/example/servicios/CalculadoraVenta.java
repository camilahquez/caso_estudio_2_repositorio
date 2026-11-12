
package org.example.servicios;

import org.example.modelo.Venta;

import java.util.HashMap;
import java.util.List;
import java.util.HashSet;
import java.util.Collections;
import java.util.Map;

public class CalculadoraVenta {

    // Almacena la lista de registros de ventas que recibe la calculadora.
    private List<Venta> ventas;

    // Inicializa la calculadora y recibe la lista de ventas que va a procesar.
    public CalculadoraVenta(List<Venta> ventas) {
        this.ventas = ventas;
    }

    // Calcula el valor total de todas las ventas registradas.
    public double totalVentas() {
        double total = 0;
        // Verifica si la lista no existe o se encuentra vacía.
        if (ventas == null || ventas.isEmpty()) {
            return 0;
        }
        // Recorre cada registro de venta.
        for (Venta venta : ventas) {
            // Verifica si el registro es nulo y lo omite.
            if (venta == null) {
                System.out.println("Se encontro una venta con registro invalido");
                continue;
            }
            double valor = venta.getValor();

            // Comprueba que el valor no sea negativo ni infinito o NaN.
            if (valor < 0 || Double.isFinite(valor) == false) {
                System.out.println("Valor de la venta invalido");
                continue;
            }

            // Acumula el valor de la venta en el total.
            total += valor;
        }

        return total;
    }
    public int cantidadTotalVentas(){
        // Verifica si la lista de ventas no existe o está vacía.
        if (ventas == null || ventas.isEmpty()) {
            return 0;
        }

        int cantidadRegistros = 0;

        // Recorre cada registro de venta.
        for (Venta venta : ventas) {
            if (venta == null) {
                continue;
            }
            cantidadRegistros++;
        }
        return cantidadRegistros;
    }

    // Calcula la cantidad total de unidades vendidas de un producto específico.
    public int canidadTotalvendidaPorProducto(String producto) {
        int totalVendidos = 0;

        // Verifica que la lista exista y que el producto no sea nulo.
        if (ventas == null || producto == null) {
            return 0;
        }

        // Recorre cada registro de venta.
        for (Venta venta : ventas) {

            // Omite los registros nulos.
            if (venta == null) {
                continue;
            }

            if (venta.getProducto() != null && venta.getProducto().equals(producto)) {


                if (venta.getCantidad() <= 0 || !Double.isFinite(venta.getValor()) || venta.getValor() < 0) {
                    continue;
                }

                // Acumula las unidades vendidas del producto.
                totalVendidos += venta.getCantidad();
            }
        }

        return totalVendidos;
    }

    // Construye un mapa que relaciona cada producto con su cantidad total vendida.
    public HashMap<String, Integer> cantidadesVendidad() {
        HashMap<String, Integer> listaCantidadesVendidas = new HashMap<>();

        // Obtiene el conjunto de nombres de los productos registrados.
        HashSet<String> nombresProductos = listaProductos();

        if (ventas == null || ventas.isEmpty()) {
            return listaCantidadesVendidas;
        }
        for (String nombre : nombresProductos) {
            listaCantidadesVendidas.put(nombre, canidadTotalvendidaPorProducto(nombre));
        }

        return listaCantidadesVendidas;
    }

    // Obtiene un conjunto con los nombres de los productos registrados.
    public HashSet<String> listaProductos() {
        HashSet<String> nombresProductos = new HashSet<>();
        if (ventas == null) {
            return nombresProductos;
        }

        // Recorre cada registro de venta.
        for (Venta venta : ventas) {
            if (venta != null && venta.getProducto() != null) {
                // El HashSet evita almacenar nombres repetidos.
                nombresProductos.add(venta.getProducto());
            }
        }

        return nombresProductos;
    }

    // Calcula el valor total vendido de un producto específico.
    public double valorVendidoTotalProducto(String producto) {
        double total = 0;

        // Verifica que la lista exista y que el producto no sea nulo.
        if (ventas == null || producto == null) {
            return 0;
        }
        for (Venta venta : ventas) {
            if (venta == null) {
                continue;
            }
            if (venta.getProducto() != null &&
                    venta.getProducto().equals(producto)) {

                // Verifica que la cantidad y el valor del registro sean válidos.
                if (venta.getCantidad() <= 0 || venta.getValor() < 0 || !Double.isFinite(venta.getValor())) {
                    continue;
                }

                // Acumula el valor de las ventas correspondientes al producto.
                total += venta.getValor();
            }
        }

        return total;
    }

    // Construye un mapa que relaciona cada producto con su valor total vendido.
    public HashMap<String, Double> listaValorVentas() {
        HashMap<String, Double> listadoGananciasBrutasPorProdctos = new HashMap<>();
        // Obtiene los nombres de los productos registrados.
        HashSet<String> nombresProductos = listaProductos();

        for (String nombre : nombresProductos) {
            listadoGananciasBrutasPorProdctos.put(nombre, valorVendidoTotalProducto(nombre));
        }

        return listadoGananciasBrutasPorProdctos;
    }

    // Identifica el producto que acumula la mayor cantidad de unidades vendidas.
    public String productoMasVendido() {
        HashMap<String, Integer> listaCantidadesVendidas = cantidadesVendidad();
        // Comprueba si existen productos registrados.
        if (listaCantidadesVendidas.isEmpty()) {
            return "No hay productos vendidos";
        }
        // Obtiene la cantidad más alta entre todos los productos.
        int mayor = Collections.max(listaCantidadesVendidas.values());
        String resultado = "";

        for (Map.Entry<String, Integer> entry : listaCantidadesVendidas.entrySet()) {

            // Comprueba cuáles productos tienen la cantidad máxima.
            if (entry.getValue() == mayor) {

                // Agrega el nombre del producto y su cantidad al resultado.
                resultado += entry.getKey() + ": " + entry.getValue() + "\n";
            }
        }

        return resultado;
    }

    // Identifica el producto que acumula el mayor valor de ventas.
    public String productoMayoresGananciasBrutas() {
        HashMap<String, Double> listadoGananciasBrutasPorProductos = listaValorVentas();
        // Comprueba si existen productos registrados.
        if (listadoGananciasBrutasPorProductos.isEmpty()) {
            return "No hay productos vendidos";
        }
        // Obtiene el valor de ventas más alto entre los productos.
        double mayor = Collections.max(listadoGananciasBrutasPorProductos.values());
        String resultado = "";
        for (Map.Entry<String, Double> entry : listadoGananciasBrutasPorProductos.entrySet()) {

            // Comprueba cuáles productos tienen el mayor valor vendido.
            if (entry.getValue() == mayor) {

                resultado += entry.getKey() + ": " + entry.getValue() + "\n";
            }
        }

        return resultado;
    }

    // Calcula el valor promedio de venta por cada registro válido.
    public double valorPromedioVentas() {
        int cantidadRegistros = cantidadTotalVentas();
        double total = totalVentas();
        return total / cantidadRegistros;
    }
}
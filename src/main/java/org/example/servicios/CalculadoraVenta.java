package org.example.servicios;
import org.example.modelo.Venta;
import java.util.HashMap;;
import java.util.List;
import java.util.HashSet;
import java.util.Collections;
import java.util.Map;


public class CalculadoraVenta {
    private List<Venta> ventas;

    public  CalculadoraVenta(List<Venta> ventas){
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
            double valor = venta.getValor();
            if (valor< 0 || Double.isFinite(valor) == false ){
                System.out.println("Valor de la venta invalido");
                continue;
            }
            total  += valor;


        }
        return total;
    }
    public int canidadTotalvendidaPorProducto(String producto) {
        int totalVendidos = 0;

        if (ventas == null || producto == null) {
            return 0;
        }

        for (Venta venta : ventas) {
            if (venta == null) {
                continue;
            }

            if (venta.getProducto() != null &&
                    venta.getProducto().equals(producto)) {

                if (venta.getCantidad() <= 0 ||
                        !Double.isFinite(venta.getValor()) ||
                        venta.getValor() < 0) {
                    continue;
                }

                totalVendidos += venta.getCantidad();
            }
        }

        return totalVendidos;
    }

    public HashMap<String, Integer> cantidadesVendidad(){
        HashMap<String, Integer> listaCantidadesVendidas = new HashMap<>();
        HashSet<String> nombresProductos = listaProductos();
        if (ventas == null || ventas.isEmpty() ){
            return listaCantidadesVendidas;
        }

        for (String nombre : nombresProductos){
            listaCantidadesVendidas.put(nombre,canidadTotalvendidaPorProducto(nombre));
        }
        return listaCantidadesVendidas;
    }
    public HashSet<String> listaProductos() {
        HashSet<String> nombresProductos = new HashSet<>();

        if (ventas == null) {
            return nombresProductos;
        }

        for (Venta venta : ventas) {
            if (venta != null && venta.getProducto() != null) {
                nombresProductos.add(venta.getProducto());
            }
        }

        return nombresProductos;
    }
    public double valorVendidoTotalProducto(String producto) {
        double total = 0;

        if (ventas == null || producto == null) {
            return 0;
        }

        for (Venta venta : ventas) {
            if (venta == null) {
                continue;
            }

            if (venta.getProducto() != null &&
                    venta.getProducto().equals(producto)) {

                if (venta.getCantidad() <= 0 ||
                        venta.getValor() < 0 ||
                        !Double.isFinite(venta.getValor())) {
                    continue;
                }

                total += venta.getValor();
            }
        }

        return total;
    }
    public HashMap<String, Double> listaValorVentas(){
        HashMap<String, Double> listadoGananciasBrutasPorProdctos = new HashMap<>();
        HashSet<String> nombresProductos = listaProductos();
        for (String nombre : nombresProductos){
            listadoGananciasBrutasPorProdctos.put(nombre,valorVendidoTotalProducto(nombre));
        }
        return listadoGananciasBrutasPorProdctos;
    }
    public String productoMasVendido() {
        HashMap<String, Integer> listaCantidadesVendidas = cantidadesVendidad();

        if (listaCantidadesVendidas.isEmpty()) {
            return "No hay productos vendidos";
        }

        int mayor = Collections.max(listaCantidadesVendidas.values());

        String resultado = "";

        for (Map.Entry<String, Integer> entry : listaCantidadesVendidas.entrySet()) {
            if (entry.getValue() == mayor) {
                resultado += entry.getKey() + ": " + entry.getValue() + "\n";
            }
        }

        return resultado;
    }
    public String productoMayoresGananciasBrutas() {
        HashMap<String, Double> listadoGananciasBrutasPorProductos = listaValorVentas();

        if (listadoGananciasBrutasPorProductos.isEmpty()) {
            return "No hay productos vendidos";
        }

        double mayor = Collections.max(listadoGananciasBrutasPorProductos.values());

        String resultado = "";

        for (Map.Entry<String, Double> entry : listadoGananciasBrutasPorProductos.entrySet()) {
            if (entry.getValue() == mayor) {
                resultado += entry.getKey() + ": " + entry.getValue() + "\n";
            }
        }

        return resultado;
    }
    public double valorPromedioVentas() {
        if (ventas == null || ventas.isEmpty()) {
            return 0;
        }

        double total = 0;
        int cantidadRegistros = 0;

        for (Venta venta : ventas) {
            if (venta == null) {
                continue;
            }

            double valor = venta.getValor();

            if (valor < 0 || !Double.isFinite(valor) || venta.getCantidad() <= 0) {
                continue;
            }

            total += valor;
            cantidadRegistros++;
        }

        if (cantidadRegistros == 0) {
            return 0;
        }

        return total / cantidadRegistros;
    }

    }


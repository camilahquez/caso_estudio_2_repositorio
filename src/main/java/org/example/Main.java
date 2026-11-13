package org.example;
import org.example.servicios.GeneradorDePrompt;
import org.example.reporte.GeneradorDeGrafica;
import org.example.servicios.CalculadoraVenta;
import org.example.servicios.LectorCSV;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
         LectorCSV lectorCSV = new LectorCSV();
        // Inicializa el objeto encargado de calcular los indicadores.
        CalculadoraVenta calculadoraVenta = new CalculadoraVenta( LectorCSV.leer("datos/ventas.csv"));
        // Inicializa el objeto encargado de generar las gráficas.
        GeneradorDeGrafica generadorDeGrafica = new GeneradorDeGrafica();
        // Inicializa el objeto encargado de construir el prompt.
        GeneradorDePrompt generadorDePrompt = new GeneradorDePrompt(calculadoraVenta);
        System.out.println("========== RESUMEN DE VENTAS ==========");

        System.out.println("Valor total de ventas: $ " + String.format("%.1f", calculadoraVenta.totalVentas()));
        System.out.println("Cantidad total de unidades vendidas: " + calculadoraVenta.cantidadTotalVentas());
        System.out.println("Producto más vendido: $ " + calculadoraVenta.productoMasVendido());
        System.out.println("Producto con mayor valor acumulado: $ " + calculadoraVenta.productoMayoresGananciasBrutas());
        System.out.println("Valor promedio de ventas: $ " + calculadoraVenta.valorPromedioVentas());
        // Muestra la tabla de cantidades vendidas por producto.
        System.out.println("\n========== CANTIDADES VENDIDAS POR PRODUCTO ==========");
        System.out.println("PRODUCTO\tCANTIDAD");

        for (var entry : calculadoraVenta.cantidadesVendidad().entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }

// Muestra la tabla de valores acumulados por producto.
        System.out.println("\n========== VALORES DE VENTAS POR PRODUCTO ==========");
        System.out.println("PRODUCTO\tVALOR TOTAL");

        for (var entry : calculadoraVenta.listaValorVentas().entrySet()) {
            System.out.println(entry.getKey() + "\t" + String.format("%.1f", entry.getValue()));
        }
        // Genera la gráfica del valor total de ventas por producto.
        generadorDeGrafica.crearGraficaBarrasProductoxTotalVendido(calculadoraVenta.listaValorVentas());

       // Genera la gráfica de las cantidades vendidas por producto.
        generadorDeGrafica.crearGraficaBarrasProductoxCantida(calculadoraVenta.cantidadesVendidad());
        String prompt = generadorDePrompt.generarPrompt();

        System.out.println("\n========== PROMPT PARA LA IA ==========");
        System.out.println(prompt);
    }


}
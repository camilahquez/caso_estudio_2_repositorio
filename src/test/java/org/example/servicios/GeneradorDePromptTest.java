package org.example.servicios;

import org.example.modelo.Venta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeneradorDePromptTest {

    private GeneradorDePrompt generadorDePrompt;

    // Prepara los registros de ventas antes de cada prueba.
    @BeforeEach
    void setUp() {
        List<Venta> ventas = Arrays.asList(new Venta(LocalDate.of(2026, 10, 1), "Laptop", 2, 7000000), new Venta(LocalDate.of(2026, 10, 1), "Mouse", 10, 800000), new Venta(LocalDate.of(2026, 10, 2), "Monitor", 4, 3600000), new Venta(LocalDate.of(2026, 10, 2), "Teclado", 7, 1050000), new Venta(LocalDate.of(2026, 10, 3), "Mouse", 6, 480000));
        CalculadoraVenta calculadoraVenta = new CalculadoraVenta(ventas);
        generadorDePrompt = new GeneradorDePrompt(calculadoraVenta);
    }

    // Comprueba que se genere correctamente la tabla de cantidades vendidas.
    @Test
    void probarGenerarTablaCantidades() {
        String tabla = generadorDePrompt.generarTablaCantidades();

        assertTrue(tabla.startsWith("Producto | Cantidad vendida"));
        assertTrue(tabla.contains("Laptop | 2"));
        assertTrue(tabla.contains("Mouse | 16"));
        assertTrue(tabla.contains("Monitor | 4"));
        assertTrue(tabla.contains("Teclado | 7"));
    }

    // Comprueba que se genere correctamente la tabla de valores de ventas.
    @Test
    void probarGenerarTablaValores() {
        String tabla = generadorDePrompt.generarTablaValores();

        assertTrue(tabla.startsWith("Producto | Valor total vendido"));
        assertTrue(tabla.contains("Laptop | 7000000.0"));
        assertTrue(tabla.contains("Mouse | 1280000.0"));
        assertTrue(tabla.contains("Monitor | 3600000.0"));
        assertTrue(tabla.contains("Teclado | 1050000.0"));
    }

    // Comprueba que se genere el prompt con los indicadores y las instrucciones.
    @Test
    void probarGenerarPrompt() {
        String prompt = generadorDePrompt.generarPrompt();

        assertTrue(prompt.contains("Actúa como un analista de datos"));
        assertTrue(prompt.contains("RESUMEN DE INDICADORES DE VENTAS"));

        assertTrue(prompt.contains("Valor total de ventas: 1.293E7"));
        assertTrue(prompt.contains("Cantidad total de unidades vendidas: 5"));

        assertTrue(prompt.contains("Laptop: 7000000.0"));
        assertTrue(prompt.contains("Mouse: 16"));

        assertTrue(prompt.contains("TABLA DE CANTIDADES VENDIDAS POR PRODUCTO"));
        assertTrue(prompt.contains("TABLA DE VALORES TOTALES DE VENTAS POR PRODUCTO"));

        assertTrue(prompt.contains("máximo tres párrafos"));
        assertTrue(prompt.contains("No inventes cifras"));
    }
}
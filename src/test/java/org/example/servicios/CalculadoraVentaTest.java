package org.example.servicios;

import org.example.modelo.Venta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraVentaTest {

    private CalculadoraVenta calculadoraVenta;

    // Prepara los registros de ventas antes de cada prueba.
    @BeforeEach
    void setUp() {
        List<Venta> ventas = Arrays.asList(new Venta(LocalDate.of(2026, 10, 1), "Laptop", 2, 7000000), new Venta(LocalDate.of(2026, 10, 1), "Mouse", 10, 800000), new Venta(LocalDate.of(2026, 10, 2), "Monitor", 4, 3600000), new Venta(LocalDate.of(2026, 10, 2), "Teclado", 7, 1050000), new Venta(LocalDate.of(2026, 10, 3), "Mouse", 6, 480000));
        calculadoraVenta = new CalculadoraVenta(ventas);
    }

    @Test
    void probarTotalVentas() {
        assertEquals(12930000, calculadoraVenta.totalVentas(), 0.001);
    }

    @Test
    void probarCantidadTotalVentas() {
        assertEquals(5, calculadoraVenta.cantidadTotalVentas());
    }

    @Test
    void probarCantidadTotalVendidaPorProducto() {
        assertEquals(16, calculadoraVenta.canidadTotalvendidaPorProducto("Mouse"));
    }

    @Test
    void probarCantidadesVendidas() {
        HashMap<String, Integer> esperado = new HashMap<>();
        esperado.put("Laptop", 2);
        esperado.put("Mouse", 16);
        esperado.put("Monitor", 4);
        esperado.put("Teclado", 7);
        assertEquals(esperado, calculadoraVenta.cantidadesVendidad());
    }

    @Test
    void probarListaProductos() {
        assertEquals(4, calculadoraVenta.listaProductos().size());

        assertTrue(calculadoraVenta.listaProductos().contains("Laptop"));
        assertTrue(calculadoraVenta.listaProductos().contains("Mouse"));
        assertTrue(calculadoraVenta.listaProductos().contains("Monitor"));
        assertTrue(calculadoraVenta.listaProductos().contains("Teclado"));
    }

    @Test
    void probarValorVendidoTotalProducto() {
        assertEquals(1280000, calculadoraVenta.valorVendidoTotalProducto("Mouse"), 0.001);
    }

    @Test
    void probarListaValorVentas() {
        HashMap<String, Double> esperado = new HashMap<>();
        esperado.put("Laptop", 7000000.0);
        esperado.put("Mouse", 1280000.0);
        esperado.put("Monitor", 3600000.0);
        esperado.put("Teclado", 1050000.0);

        assertEquals(esperado, calculadoraVenta.listaValorVentas());
    }

    @Test
    void probarProductoMasVendido() {
        String resultado = calculadoraVenta.productoMasVendido();

        assertTrue(resultado.contains("Mouse: 16"));
    }

    @Test
    void probarProductoMayoresGananciasBrutas() {
        String resultado = calculadoraVenta.productoMayoresGananciasBrutas();

        assertTrue(resultado.contains("Laptop: 7000000.0"));
    }

    @Test
    void probarValorPromedioVentas() {
        assertEquals(2586000, calculadoraVenta.valorPromedioVentas(), 0.001);
    }

    @Test
    void probarListaVacia() {
        CalculadoraVenta calculadoraVacia =
                new CalculadoraVenta(Collections.emptyList());

        assertEquals(0, calculadoraVacia.totalVentas());
        assertEquals(0, calculadoraVacia.cantidadTotalVentas());
        assertTrue(calculadoraVacia.listaProductos().isEmpty());
        assertTrue(calculadoraVacia.cantidadesVendidad().isEmpty());
        assertTrue(calculadoraVacia.listaValorVentas().isEmpty());
        assertEquals("No hay productos vendidos", calculadoraVacia.productoMasVendido());
        assertEquals("No hay productos vendidos", calculadoraVacia.productoMayoresGananciasBrutas());
    }
}
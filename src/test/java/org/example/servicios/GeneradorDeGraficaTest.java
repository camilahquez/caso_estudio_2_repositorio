package org.example.servicios;
import java.io.IOException;

import org.example.reporte.GeneradorDeGrafica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GeneradorDeGraficaTest {

    private GeneradorDeGrafica generadorDeGrafica;

    // Inicializa el generador antes de cada prueba.
    @BeforeEach
    void setUp() {
        generadorDeGrafica = new GeneradorDeGrafica();
    }

    // Comprueba que se genere y guarde la gráfica de valores de ventas.
    @Test
    void probarGraficaTotalVendido() throws IOException {

        HashMap<String, Double> valores = new HashMap<>();
        valores.put("Laptop", 7000000.0);
        valores.put("Mouse", 1280000.0);
        valores.put("Monitor", 3600000.0);
        valores.put("Teclado", 1050000.0);

        generadorDeGrafica.crearGraficaBarrasProductoxTotalVendido(valores);

        assertTrue(Files.exists(Paths.get("graficas/grafica_ventas.png")));

        assertTrue(Files.exists(Paths.get("graficas/grafica_ventas.png")) && Files.size(Paths.get("graficas/grafica_ventas.png")) > 0);
    }

    // Comprueba que se genere y guarde la gráfica de cantidades vendidas.
    @Test
    void probarGraficaCantidadesVendidas() throws IOException {

        HashMap<String, Integer> cantidades = new HashMap<>();
        cantidades.put("Laptop", 2);
        cantidades.put("Mouse", 16);
        cantidades.put("Monitor", 4);
        cantidades.put("Teclado", 7);

        generadorDeGrafica.crearGraficaBarrasProductoxCantida(cantidades);

        assertTrue(Files.exists(Paths.get("graficas/grafica_cantidades_vendidas.png")));

        assertTrue(Files.size(Paths.get("graficas/grafica_cantidades_vendidas.png")) > 0);
    }
}
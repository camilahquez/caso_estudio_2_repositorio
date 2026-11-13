package org.example.servicios;

import org.example.modelo.Venta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LectorCSVTest {

    @TempDir
    Path carpetaTemporal;

    private String crearCsv(String contenido) throws IOException {
        Path archivo = carpetaTemporal.resolve("ventas_test.csv");
        Files.writeString(archivo, contenido);
        return archivo.toString();
    }

    @Test
    void leerCsvValidoDevuelveTodasLasVentas() throws IOException {
        String ruta = crearCsv("""
                fecha,producto,cantidad,valor
                2026-10-01,Laptop,2,7000000
                2026-10-01,Mouse,10,800000
                2026-10-02,Monitor,4,3600000
                """);

        List<Venta> ventas = LectorCSV.leer(ruta);

        assertEquals(3, ventas.size());
        Venta primera = ventas.get(0);
        assertEquals(LocalDate.of(2026, 10, 1), primera.getFecha());
        assertEquals("Laptop", primera.getProducto());
        assertEquals(2, primera.getCantidad());
        assertEquals(7000000, primera.getValor(), 0.001);
    }

    @Test
    void filaInvalidaSeOmiteYLasDemasSeLeen() throws IOException {
        String ruta = crearCsv("""
                fecha,producto,cantidad,valor
                2026-10-01,Laptop,2,7000000
                2026-10-01,Mouse,diez,800000
                2026-10-02,Monitor,4,3600000
                """);

        List<Venta> ventas = LectorCSV.leer(ruta);

        assertEquals(2, ventas.size());
        assertEquals("Laptop", ventas.get(0).getProducto());
        assertEquals("Monitor", ventas.get(1).getProducto());
    }

    @Test
    void archivoInexistenteLanzaIOException() {
        String rutaInexistente = carpetaTemporal.resolve("no_existe.csv").toString();

        assertThrows(IOException.class, () -> LectorCSV.leer(rutaInexistente));
    }

    @Test
    void csvSoloConEncabezadoDevuelveListaVacia() throws IOException {
        String ruta = crearCsv("fecha,producto,cantidad,valor\n");

        List<Venta> ventas = LectorCSV.leer(ruta);

        assertTrue(ventas.isEmpty());
    }
}
package org.example;
import org.example.modelo.Venta;
import org.example.service.LectorCSV;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {

        List<Venta> ventas = LectorCSV.leer("datos/ventas.csv");
        System.out.println("Registros leídos: " + ventas.size());
        ventas.forEach(System.out::println);
    }
}
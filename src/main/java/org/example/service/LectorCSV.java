package org.example.service;

import org.example.modelo.Venta;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {

    public static List<Venta> leer(String ruta) throws IOException {
        List<Venta> ventas = new ArrayList<>();

        CSVFormat formato = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setTrim(true)
                .build();

        try (Reader reader = Files.newBufferedReader(Paths.get(ruta));
             CSVParser parser = formato.parse(reader)) {

            for (CSVRecord fila : parser) {
                try {
                    LocalDate fecha = LocalDate.parse(fila.get("fecha"));
                    String producto = fila.get("producto");
                    int cantidad = Integer.parseInt(fila.get("cantidad"));
                    double valor = Double.parseDouble(fila.get("valor"));

                    ventas.add(new Venta(fecha, producto, cantidad, valor));
                } catch (Exception e) {
                    System.err.println("Fila " + fila.getRecordNumber()
                            + " inválida,se omite: " + e.getMessage());
                }
            }
        }
        return ventas;
    }
}

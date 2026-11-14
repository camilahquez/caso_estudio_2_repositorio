package org.example.reporte;
import org.knowm.xchart.*;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import java.io.IOException;
import java.util.HashMap;
import java.util.Arrays;
public class GeneradorDeGrafica {
    // Recibe un mapa que contiene el nombre de cada productoy el valor total de sus ventas acumuladas.
    public void crearGraficaBarrasProductoxTotalVendido( HashMap<String, Double> listaValorVentas){
        // Obtiene los nombres de los productos y los almacena en un arreglo.
        String[] productos = listaValorVentas.keySet().toArray(new String[0]);
        //genera una lista de igual tamaño qie productos
        double[] totalValores = new double[productos.length];
        // agregga correspondientes de los productos los valores en la listaValores
        for (int i = 0; i < productos.length; i++) {
            totalValores[i] = listaValorVentas.get(productos[i]);
        }
        CategoryChart grafica = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title("Ventas por producto")
                .xAxisTitle("Productos")
                .yAxisTitle("Valor de acumulado ventas")
                .build();

        grafica.getStyler().setYAxisDecimalPattern("#,##0");
        grafica.addSeries("Ventas", Arrays.asList(productos), Arrays.stream(totalValores).boxed().toList());

        new SwingWrapper<>(grafica).displayChart();
        try {
            BitmapEncoder.saveBitmap(grafica, "graficas/grafica_ventas", BitmapFormat.PNG);

        } catch (IOException e) {
            System.out.println("Error al guardar la gráfica: " + e.getMessage());
        }
    }

    public void crearGraficaBarrasProductoxCantida( HashMap<String, Integer> cantidadesVendidad ){
        // Obtiene los nombres de los productos y los almacena en un arreglo.
        String[] productos = cantidadesVendidad.keySet().toArray(new String[0]);
        //genera una lista de igual tamaño qie productos
        double[] totalCantidades = new double[productos.length];
        // agregga correspondientes de los productos los valores en la listaValores
        for (int i = 0; i < productos.length; i++) {
            totalCantidades[i] = cantidadesVendidad.get(productos[i]);
        }
        CategoryChart grafica = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title("Ventas por producto")
                .xAxisTitle("Productos")
                .yAxisTitle("cantidades vendidas")
                .build();
        //Se utilizan arreglos porque primero necesitas organizar los datos de los productos y sus valores en dos colecciones que tengan la misma cantidad de elementos y mantengan una correspondencia por posición.
        grafica.addSeries("Ventas", Arrays.asList(productos), Arrays.stream(totalCantidades).boxed().toList());

        new SwingWrapper<>(grafica).displayChart();
        try {
            BitmapEncoder.saveBitmap(grafica, "graficas/grafica_cantidades_vendidas", BitmapFormat.PNG);

        } catch (IOException e) {
            System.out.println("Error al guardar la gráfica: " + e.getMessage());
        }
    }


}

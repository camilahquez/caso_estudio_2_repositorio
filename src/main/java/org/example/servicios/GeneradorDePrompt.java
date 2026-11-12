package org.example.servicios;
import java.util.HashMap;

public class GeneradorDePrompt {
    private CalculadoraVenta calculadoraVenta;
    public GeneradorDePrompt(CalculadoraVenta calculadoraVenta){
        this.calculadoraVenta = calculadoraVenta;
    }
    // Genera una tabla con el nombre de cada producto y la cantidad total de unidades vendidas.
    public String generarTablaCantidades() {

        HashMap<String, Integer> cantidades = calculadoraVenta.cantidadesVendidad();
        String tabla = "Producto | Cantidad vendida\n";
        for (String producto : cantidades.keySet()) {
            tabla += producto + " | " + cantidades.get(producto) + "\n";
        }

        return tabla;
    }
    // Genera una tabla con el nombre de cada producto y el valor total acumulado de sus ventas.
    public String generarTablaValores() {
        HashMap<String, Double> valores = calculadoraVenta.listaValorVentas();
        String tabla = "Producto | Valor total vendido\n";
        for (String producto : valores.keySet()) {
            tabla += producto + " | " + valores.get(producto) + "\n";
        }

        return tabla;
    }
    public String generarPrompt(){
        double valorTotalVentas = calculadoraVenta.totalVentas();
        int cantidadTotalVendida = calculadoraVenta.cantidadTotalVentas();
        String productoMayoresGananciasBrutas = calculadoraVenta.productoMayoresGananciasBrutas();
        String productoMasVendido = calculadoraVenta.productoMasVendido();
        double valorPromedioVentas = calculadoraVenta.valorPromedioVentas();
        String tablaProductosxCantidades = generarTablaCantidades();
        String tablaProductosxValoresTotales = generarTablaValores();
        String resumen =  """
            RESUMEN DE INDICADORES DE VENTAS

            1. Valor total de ventas: %s
            2. Cantidad total de unidades vendidas: %d
            3. producto con mayor valor acumulado de ventas : %s
            4. Producto más vendido por cantidad: %s
            5. Valor promedio de ventas por registro: %s

            TABLA DE CANTIDADES VENDIDAS POR PRODUCTO:
            %s

            TABLA DE VALORES TOTALES DE VENTAS POR PRODUCTO:
            %s
            """.formatted(
                valorTotalVentas,
                cantidadTotalVendida,
                productoMayoresGananciasBrutas,
                productoMasVendido,
                valorPromedioVentas,
                tablaProductosxCantidades,
                tablaProductosxValoresTotales
        );
        String prompt =
                """
             Actúa como un analista de datos especializado en el área comercial.
             A partir del siguiente resumen de indicadores y tablas de ventas,
             redacta un reporte ejecutivo que describa el comportamiento de las ventas.
 
             %s
 
             INSTRUCCIONES:
             1. Analiza e interpreta los indicadores generales de ventas.
             2. Compara las cantidades vendidas y los valores totales de cada producto.
             3. Identifica los productos con mejor y menor desempeño comercial.
             4. Explica qué información relevante se puede obtener de los resultados.
             5. Propón recomendaciones que puedan contribuir a mejorar el desempeño de las ventas.
 
             El reporte debe tener máximo tres párrafos, utilizar un lenguaje
             profesional, claro y comprensible, y estar dirigido a la toma de
             decisiones empresariales.
 
             Basa el análisis exclusivamente en los datos proporcionados.
             No inventes cifras ni información que no esté disponible.
             Si identificas alguna inconsistencia entre los indicadores y las tablas,
             señálala en el reporte.
             """.formatted(resumen);
       return prompt;
    }

}

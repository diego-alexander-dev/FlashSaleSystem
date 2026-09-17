package pe.edu.utp.flashsale.services;

import pe.edu.utp.flashsale.config.DatabaseConnection;
import pe.edu.utp.flashsale.models.OrdenCompra;
import pe.edu.utp.flashsale.payments.FabricaPagos;
import pe.edu.utp.flashsale.payments.IPasarelaPago;

public class MotorVentasFacade implements IServicioVenta {
    private int stockDisponible = 5; // Simulación de stock crítico para la venta flash

    @Override
    public void ejecutarCompra(String idUsuario, String idProducto, String metodoPago, double precio) {
        System.out.println("\n--- [Facade] Iniciando transacción de venta flash ---");

        // 1. Verificación rápida de inventario
        if (stockDisponible <= 0) {
            System.out.println("❌ Error: Stock agotado para el producto " + idProducto);
            return;
        }
        stockDisponible--;

        try {
            // 2. Patrón Builder: Armar la orden de compra
            OrdenCompra orden = new OrdenCompra.Builder(idUsuario, idProducto)
                    .setMontoTotal(precio)
                    .setEnvioExpress(true)
                    .build();
            System.out.println("✅ Orden generada: " + orden.toString());

            // 3. Patrón Factory Method / Adapter: Procesar el cobro
            IPasarelaPago procesador = FabricaPagos.obtenerPasarela(metodoPago);
            boolean pagoExitoso = procesador.procesarPago(orden.getMontoTotal());

            // 4. Patrón Singleton: Persistencia en PostgreSQL
            if (pagoExitoso) {
                DatabaseConnection db = DatabaseConnection.getInstancia();
                // Nota arquitectónica: Aquí se asegura la integridad referencial (Foreign Keys)
                // insertando id_usuario que hace referencia a la tabla 'usuarios(id)'.
                System.out.println("✅ [Facade] INSERT INTO ordenes (id_usuario, id_producto, monto) VALUES ('" 
                                   + orden.getIdUsuario() + "', '" + idProducto + "', " + orden.getMontoTotal() + ");");
                System.out.println("🎉 Compra finalizada con éxito.");
            }
        } catch (Exception e) {
            System.err.println("❌ Fallo en la transacción: " + e.getMessage());
            stockDisponible++; // Rollback de inventario
        }
    }
}
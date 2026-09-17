package pe.edu.utp.flashsale.payments;

/**
 * Patrón FACTORY METHOD: Centraliza y desacopla la creación de objetos de pago.
 */
public class FabricaPagos {
    public static IPasarelaPago obtenerPasarela(String tipoPago) {
        switch (tipoPago.toUpperCase()) {
            case "TARJETA":
                return new PagoTarjeta();
            case "PAYPAL":
                return new PagoPaypal();
            case "TRANSFERENCIA":
                return new AdaptadorBanco(); // Inyección del Adapter
            default:
                throw new IllegalArgumentException("Método de pago no soportado.");
        }
    }
}
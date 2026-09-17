package pe.edu.utp.flashsale.payments;
public class PagoPaypal implements IPasarelaPago {
    @Override
    public boolean procesarPago(double monto) {
        System.out.println("[Pago] Procesando $" + monto + " mediante API de PayPal.");
        return true;
    }
}

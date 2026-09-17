package pe.edu.utp.flashsale.payments;

public class PagoTarjeta implements IPasarelaPago {
    @Override
    public boolean procesarPago(double monto) {
        System.out.println("[Pago] Procesando $" + monto + " mediante Tarjeta de Crédito/Débito.");
        return true;
    }
}
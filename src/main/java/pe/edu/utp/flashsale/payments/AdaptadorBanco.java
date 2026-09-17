package pe.edu.utp.flashsale.payments;

/**
 * Patrón ADAPTER: Actúa como puente entre la interfaz moderna (IPasarelaPago)
 * y el sistema bancario obsoleto (SistemaBancarioLegacy).
 */
public class AdaptadorBanco implements IPasarelaPago {
    private SistemaBancarioLegacy bancoAntiguo;

    public AdaptadorBanco() {
        this.bancoAntiguo = new SistemaBancarioLegacy();
    }

    @Override
    public boolean procesarPago(double monto) {
        // El adaptador traduce la petición doble a XML
        String payloadXML = "<transaccion><tipo>transferencia</tipo><monto>" + monto + "</monto></transaccion>";
        System.out.println("[Adapter] Convirtiendo objeto Java a formato XML para el banco...");
        return bancoAntiguo.realizarTransferenciaXML(payloadXML);
    }
}

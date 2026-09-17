package pe.edu.utp.flashsale.payments;

/**
 * Simula un sistema bancario antiguo que requiere datos en formato texto/XML.
 */
public class SistemaBancarioLegacy {
    public boolean realizarTransferenciaXML(String xmlTransaccion) {
        System.out.println("[Banco Legacy] Recibiendo trama XML: " + xmlTransaccion);
        return true; // Simulación de éxito
    }
}